package com.haha.common.domain;

public enum ReturnMessage {
    // 静态变量
    OK("success"),
    ERROR("fail");

    // 变量
    private String message;

    // 构造函数
    ReturnMessage(String message){
        this.message = message;
    }

    // 获取值方法,通过国际化方式
    public String getMessage(){
        return message;
    }

}
