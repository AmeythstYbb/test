package com.zlycare.web.boss_24.componet.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

/**
 * Description:
 * @author yz.wu
 */
public class RequestAttributeArgumentResolver implements WebArgumentResolver {
	
	private static final Logger log = LoggerFactory.getLogger(RequestAttributeArgumentResolver.class);

    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest)
            throws Exception {
        RequestAttribute annotation = methodParameter.getParameterAnnotation(RequestAttribute.class);
        if (annotation == null) {
            return WebArgumentResolver.UNRESOLVED;
        }
        String attributeName = getAttributeName(annotation, methodParameter);
        if (log.isDebugEnabled()) {
    		log.debug("attributeName="+attributeName);
    	}
        Object object = webRequest.getAttribute(attributeName, RequestAttributes.SCOPE_REQUEST);
        if (object == null && annotation.required()) {
            throw new RuntimeException("required request attribute[" + attributeName
                    + "] not found");
        }
        return object;
    }

    private String getAttributeName(RequestAttribute annotation, MethodParameter methodParameter) {
        if (!"".equals(annotation.value().trim())) {
            return annotation.value();
        }
        return methodParameter.getParameterName();
    }
}
