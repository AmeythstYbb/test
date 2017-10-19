package com.zlycare.web.boss_24.beans.exception;


import com.zlycare.web.boss_24.beans.constants.error.ServiceBusinessCode;

/**
 * Descriptions : 非法文件名(从user模块挪过来的)
 * Author : kaihua
 * Create : 2016/2/28 16:39
 * Update : 2016/2/28 16:39
 */
public class IllegalFileNameException extends ServiceException {

    public IllegalFileNameException() {
        super(ServiceBusinessCode.ILLEGAL_FILE_NEAM);
    }
}
