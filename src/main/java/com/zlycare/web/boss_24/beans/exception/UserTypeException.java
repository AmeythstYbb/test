package com.zlycare.web.boss_24.beans.exception;


import com.zlycare.web.boss_24.beans.constants.error.ServiceBusinessCode;

/**
 * Descriptions :
 * Author : kaihua
 * Create : 2016/7/26 15:48
 * Update : 2016/7/26 15:48
 */
public class UserTypeException extends ServiceException {

    public UserTypeException() {
        super(ServiceBusinessCode.USER_TYPE_ERROR);
    }
}
