package com.haha.common.domain;

/**
 * 返回码 0成功 非0 失败
 */
public enum ReturnCode {
    OK(0),
    ERROR(1);

    // 变量
    private int value;

    // 构造函数
    ReturnCode(int value){
        this.value = value;
    }

    // 私有变量的get方法
    public int getValue(){
        return this.value;
    }
}
