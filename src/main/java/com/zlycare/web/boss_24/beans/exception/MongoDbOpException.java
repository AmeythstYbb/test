package com.zlycare.web.boss_24.beans.exception;

/**
 * Mongodb 操作异常
 *
 * @author DaiJian
 * @create 2016/6/20 14:05
 */
public class MongoDbOpException extends RuntimeException {
    public MongoDbOpException(String message) {
        super(message);
    }
}
