package com.zlycare.web.boss_24.componet.spring;

import com.alibaba.fastjson.serializer.ValueFilter;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Description:
 *
 * @author yz.wu
 */
public class StringValueFilter implements ValueFilter {

    @Override
    public Object process(Object object, String name, Object value) {
        if (value != null && value.getClass() == String.class){
            String s = StringEscapeUtils.escapeHtml4(value.toString());
            return StringUtils.replace(s, "&amp;", "&");
        }

        return value;
    }
}
