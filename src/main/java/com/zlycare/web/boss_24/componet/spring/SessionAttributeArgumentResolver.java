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
public class SessionAttributeArgumentResolver implements WebArgumentResolver {
	
	private static final Logger log = LoggerFactory.getLogger(SessionAttributeArgumentResolver.class);

    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest)
            throws Exception {
    	
        SessionAttribute annotation = methodParameter.getParameterAnnotation(SessionAttribute.class);
        if (annotation == null) {
            return WebArgumentResolver.UNRESOLVED;
        }
        String attributeName = getAttributeName(annotation, methodParameter);
        Object object = webRequest.getAttribute(attributeName, RequestAttributes.SCOPE_SESSION);
        if (object == null && annotation.required()) {
        	if (log.isDebugEnabled()) {
        		log.debug("session attributeName:"+attributeName+" not found!");
        	}
        }
        return object;
    }

    private String getAttributeName(SessionAttribute annotation, MethodParameter methodParameter) {
        if (!"".equals(annotation.value().trim())) {
            return annotation.value();
        }
        return methodParameter.getParameterName();
    }
}
