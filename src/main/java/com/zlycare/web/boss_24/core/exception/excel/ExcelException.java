package com.zlycare.web.boss_24.core.exception.excel;


import com.zlycare.web.boss_24.beans.constants.error.ServiceBusinessCode;
import com.zlycare.web.boss_24.beans.exception.ServiceException;

/**
 * @author lufanglong
 * @date 2015-09-21 10:11
 * @Description 用户异常
 */
public class ExcelException extends ServiceException {

    public ExcelException() {
        super();
    }

    public ExcelException(String message) {
        super(message);
    }

    public ExcelException(ServiceBusinessCode serviceBusinessCode) {
        super(serviceBusinessCode);
    }


    public ExcelException(ServiceBusinessCode serviceBusinessCode, String message) {
        super(serviceBusinessCode, message);
    }

    public ExcelException(ServiceBusinessCode serviceBusinessCode, Throwable cause) {
        super(serviceBusinessCode, cause);
    }

    public ExcelException(ServiceBusinessCode serviceBusinessCode, String message, Throwable cause) {
        super(serviceBusinessCode, message, cause);
    }

    public ExcelException(ServiceBusinessCode serviceBusinessCode, String message, Throwable cause,
                          boolean enableSuppression, boolean writableStackTrace) {
        super(serviceBusinessCode, message, cause, enableSuppression, writableStackTrace);
    }

}
