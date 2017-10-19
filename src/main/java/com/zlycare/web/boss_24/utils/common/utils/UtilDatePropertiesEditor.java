package com.zlycare.web.boss_24.utils.common.utils;

import org.apache.commons.lang3.math.NumberUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 * 
 * @author yz.wu
 */
public class UtilDatePropertiesEditor extends PropertyEditorSupport {

	private String format = "yyyy-MM-dd hh:mm:ss";

	public void setAsText(String text) throws IllegalArgumentException {
		try {
            Date dateVal = null;
            if(NumberUtils.isNumber(text)) {
                dateVal = new Date(Long.parseLong(text));
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                dateVal = sdf.parse(text);
            }
            this.setValue(dateVal);
        } catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
