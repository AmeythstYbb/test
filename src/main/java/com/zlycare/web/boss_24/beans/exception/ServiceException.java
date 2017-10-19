package com.zlycare.web.boss_24.beans.exception;


import com.zlycare.web.boss_24.beans.constants.error.ServiceBusinessCode;

import java.util.Map;

/**
 * @author lufanglong
 * @date 2016-01-14 17:11
 * @Description service运行时异常, 默认9998
 */
public class ServiceException extends RuntimeException {

    /**
     * 服务的业务错误码
     */
    protected ServiceBusinessCode serviceBusinessCode;
    /**
     * 异常时需要返回客户端的数据
     */
    protected Map<String, Object> data;

    public ServiceException() {
        this.serviceBusinessCode = ServiceBusinessCode.SERVICE_ERROR;
    }

    public ServiceException(Map<String, Object> data) {
        this.serviceBusinessCode = ServiceBusinessCode.SERVICE_ERROR;
        this.data = data;
    }

    public ServiceException(String message) {
        super(message);
        this.serviceBusinessCode = ServiceBusinessCode.SERVICE_ERROR;
    }

    public ServiceException(ServiceBusinessCode serviceBusinessCode) {
        if (serviceBusinessCode == null) {
            this.serviceBusinessCode = ServiceBusinessCode.SERVICE_ERROR;
        }
        this.serviceBusinessCode = serviceBusinessCode;
    }

    public ServiceException(ServiceBusinessCode serviceBusinessCode, Map<String, Object> data) {
        if (serviceBusinessCode == null) {
            this.serviceBusinessCode = ServiceBusinessCode.SERVICE_ERROR;
        }
        this.serviceBusinessCode = serviceBusinessCode;
        this.data = data;
    }

    public ServiceException(ServiceBusinessCode serviceBusinessCode, String message) {
        super(message);
        if (serviceBusinessCode == null) {
            this.serviceBusinessCode = ServiceBusinessCode.SERVICE_ERROR;
        }
        this.serviceBusinessCode = serviceBusinessCode;
    }

    public ServiceException(ServiceBusinessCode serviceBusinessCode, Throwable cause) {
        super(cause);
        this.serviceBusinessCode = serviceBusinessCode;
    }

    public ServiceException(ServiceBusinessCode serviceBusinessCode, String message, Throwable cause) {
        super(message, cause);
        this.serviceBusinessCode = serviceBusinessCode;
    }

    public ServiceException(ServiceBusinessCode serviceBusinessCode, String message, Throwable cause,
                            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.serviceBusinessCode = serviceBusinessCode;
    }

    public void setServiceBusinessCode(ServiceBusinessCode serviceBusinessCode) {
        this.serviceBusinessCode = serviceBusinessCode;
    }

    public ServiceBusinessCode getServiceBusinessCode() {
        return serviceBusinessCode;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
