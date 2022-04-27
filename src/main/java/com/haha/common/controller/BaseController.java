package com.haha.common.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.HashMap;
import java.util.Map;

public class BaseController {
    // 处理iPage 转成 Map<String, Object>, 供子类调用，返回json的key="data"的value
    // 权限 protected 只能本包和子类调用
    protected Map<String, Object> getDataBody(IPage<?> iPage){
        Map<String, Object> dataBody = new HashMap<>();
        dataBody.put("rows", iPage.getRecords());  // 返回集合List，在Json用"row":[{"id":1},{"id":2}] ？表示
        dataBody.put("total", iPage.getTotal());  // 返回Long
        return dataBody;
    }
}
