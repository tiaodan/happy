package com.haha.common.exception;

/**
 * 系统内部异常
 */
public class SysInnerException extends Exception {
    // 构造函数
    public SysInnerException(String message) {
        super(message);  // 设置父类内容
    }
}
