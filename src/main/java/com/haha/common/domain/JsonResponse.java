package com.haha.common.domain;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.HashMap;

/**
 * {
 *     returnCode: 0,
 *     message: "成功",
 *     data[{key, value},
 *     {key, value},
 *     {key, value}
 *     ]
 * }
 */
public class JsonResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -9127814456406791924L;

    // 重写put方法
    @Override
    public JsonResponse put(String key, Object value){
        super.put(key, value);
        return this;
    }

    // 设置参数并返回自身
    public JsonResponse returnCode(ReturnCode returnCode){
        this.put("returnCode", returnCode.getValue());
        return this;
    }

    /**
     * 重载方法 - returnCode
     * @param returnCode
     * @return
     */
    public JsonResponse returnCode(int returnCode){
        this.put("returnCode", returnCode);
        return this;
    }

    public JsonResponse message(ReturnMessage returnMessage){
        this.put("message", returnMessage.getMessage());
        return this;
    }

    /**
     * 重载方法 - message
     * @param message
     * @return
     */
    public JsonResponse message(String message){
        this.put("message", message);
        return this;
    }

    public JsonResponse data(Object data){
        this.put("data", data);
        return this;
    }


}
