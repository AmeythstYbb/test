package com.zlycare.web.boss_24.core.exception.user;


import com.zlycare.web.boss_24.beans.constants.error.ServiceBusinessCode;
import com.zlycare.web.boss_24.beans.exception.ServiceException;

/**
 * @author lufanglong
 * @date 2015-09-21 10:11
 * @Description 用户异常
 */
public class UserException extends ServiceException {
    /**
     * 用户ID
     */
    protected int userId;

    public UserException() {
        super(ServiceBusinessCode.USER_ERROR);
    }

    public UserException(int userId) {
        super(ServiceBusinessCode.USER_ERROR);
        this.userId = userId;
    }

    public UserException(String message) {
        super(ServiceBusinessCode.USER_ERROR, message);
    }

    public UserException(ServiceBusinessCode serviceBusinessCode) {
        super(serviceBusinessCode);
    }

    public UserException(ServiceBusinessCode serviceBusinessCode, int userId) {
        super(serviceBusinessCode);
        this.userId = userId;
    }

    public UserException(ServiceBusinessCode serviceBusinessCode, String message) {
        super(serviceBusinessCode, message);
    }

    public UserException(ServiceBusinessCode serviceBusinessCode, Throwable cause) {
        super(serviceBusinessCode, cause);
    }

    public UserException(ServiceBusinessCode serviceBusinessCode, String message, Throwable cause) {
        super(serviceBusinessCode, message, cause);
    }

    public UserException(ServiceBusinessCode serviceBusinessCode, String message, Throwable cause,
                         boolean enableSuppression, boolean writableStackTrace) {
        super(serviceBusinessCode, message, cause, enableSuppression, writableStackTrace);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
