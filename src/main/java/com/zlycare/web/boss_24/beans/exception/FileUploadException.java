package com.zlycare.web.boss_24.beans.exception;


import com.zlycare.web.boss_24.beans.constants.error.ServiceBusinessCode;

/**
 * Descriptions : 文件上传异常(从user模块挪过来的)
 * Author : kaihua
 * Create : 2016/2/28 16:50
 * Update : 2016/2/28 16:50
 */
public class FileUploadException extends ServiceException {
    public FileUploadException() {
        super(ServiceBusinessCode.FILE_UPLOAD_ERROR);
    }
}
