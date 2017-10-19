package com.zlycare.web.boss_24.utils.common.utils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.DateFormatDeserializer;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author lufanglong
 * @date 2016-01-07 09:51
 * @Description 自定义日期转换
 */
public class CustomerDateFormatDeserializer extends DateFormatDeserializer {
    /**
     * 自定义转换类型
     */
    private String customerFormat;

    public CustomerDateFormatDeserializer(String customerFormat) {
        super();
        this.customerFormat = customerFormat;
    }

    @Override
    protected <Date> Date cast(DefaultJSONParser parser, Type clazz, Object fieldName, Object val) {
        if (customerFormat == null || "".equals(customerFormat)) {
            return null;
        }
        if (val instanceof String) {
            String strVal = (String) val;
            if (strVal.length() == 0) {
                return null;
            }

            try {
                return (Date) new SimpleDateFormat(customerFormat).parse((String)val);
            } catch (ParseException e) {
                throw new JSONException("parse error");
            }
        }
        throw new JSONException("parse error");
    }
}
