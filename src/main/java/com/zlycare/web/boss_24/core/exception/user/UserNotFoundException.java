package com.zlycare.web.boss_24.core.exception.user;


import com.zlycare.web.boss_24.beans.constants.error.ServiceBusinessCode;

/**
 * @author lufanglong
 * @date 2015-09-21 10:11
 * @Description 找不到用户异常
 */
public class UserNotFoundException extends UserException {

    public UserNotFoundException(int userId) {
        this.userId = userId;
    }

    public UserNotFoundException() {
        super(ServiceBusinessCode.USER_NOT_FOUND_ERROR);
    }

    public UserNotFoundException(ServiceBusinessCode serviceBusinessCode, String message) {
        super(serviceBusinessCode, message);
    }

    public UserNotFoundException(ServiceBusinessCode serviceBusinessCode, String message, Throwable cause) {
        super(serviceBusinessCode, message, cause);
    }

    public UserNotFoundException(ServiceBusinessCode serviceBusinessCode, Throwable cause) {
        super(serviceBusinessCode, cause);
    }

    public UserNotFoundException(ServiceBusinessCode serviceBusinessCode, String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(serviceBusinessCode, message, cause, enableSuppression, writableStackTrace);
    }
}
