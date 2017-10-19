package com.zlycare.web.boss_24.utils.common.utils;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.ValueFilter;
import org.joda.time.DateTime;

import java.util.Date;


public class JsonUtils {
    /**
     * 对象序列化成json对象，且null属性转换成""
     *
     * @param object
     * @return
     */
    public static String toJSONString(Object object, final String dateFmt) {
        SerializeWriter writer = new SerializeWriter();
        String out;
        JSONSerializer serializer = new JSONSerializer(writer);
        serializer.getValueFilters().add(new ValueFilter() {
            public Object process(Object obj, String s, Object value) {
                if (null != value) {
                    if (value instanceof Date) {
                        DateTime dateTime = new DateTime(value);
                        // return String.format("%1$tF %1tT", value);
                        return dateTime.toString(dateFmt);
                    }
                    return value;
                } else {
                    return "";
                }
            }
        });
        serializer.write(object);
        out = writer.toString();
        writer.close();
        return out;
    }

}
