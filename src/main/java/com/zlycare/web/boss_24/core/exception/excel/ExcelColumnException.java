package com.zlycare.web.boss_24.core.exception.excel;


import com.zlycare.web.boss_24.beans.constants.error.ServiceBusinessCode;

/**
 * @author lufanglong
 * @date 2015-09-21 10:11
 * @Description 找不到用户异常
 */
public class ExcelColumnException extends ExcelException {

    public ExcelColumnException() {
        super();
    }

    public ExcelColumnException(String message) {
        super(message);
    }

    public ExcelColumnException(ServiceBusinessCode serviceBusinessCode, String message) {
        super(serviceBusinessCode, message);
    }

    public ExcelColumnException(ServiceBusinessCode serviceBusinessCode, String message, Throwable cause) {
        super(serviceBusinessCode, message, cause);
    }

    public ExcelColumnException(ServiceBusinessCode serviceBusinessCode, Throwable cause) {
        super(serviceBusinessCode, cause);
    }

    public ExcelColumnException(ServiceBusinessCode serviceBusinessCode, String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
        super(serviceBusinessCode, message, cause, enableSuppression, writableStackTrace);
    }
}
