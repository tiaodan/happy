package com.haha.business.audiobook.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haha.business.audiobook.domain.Type;
import com.haha.business.audiobook.service.TypeService;
import com.haha.common.controller.BaseController;
import com.haha.common.domain.JsonResponse;
import com.haha.common.domain.QueryRequest;
import com.haha.common.domain.ReturnCode;
import com.haha.common.domain.ReturnMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping({"/api/type", "/type"})
public class TypeController extends BaseController {
    // 初始化
    @Autowired
    TypeService typeService;

    // 增

    // 删

    // 改

    // 查 所有
    @GetMapping("/all")
    public Map<String, Object> selectTypeAll(){
        // 1. 处理参数

        // 2. 调用service
        typeService.getTypeAll();

        // 3. 返回
        return null;
    }

    /**
     *
     * @param queryRequest 带分页信息
     * @return
     */
    @GetMapping
    public Map<String, Object> selectTypeByCondition(QueryRequest queryRequest){
        // 1. 处理参数
        log.info("current=" + queryRequest.getCurrent());
        log.info("size" +  queryRequest.getSize());
        // 2. 调用service
        IPage<Type> iPageArgs = new Page<>(queryRequest.getCurrent(), queryRequest.getSize());
//        IPage<Type> typeIPage = typeService.page(iPageArgs);
        IPage<Type> typeIPage = typeService.page(new Page<>(1, 2));

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.OK).data(getDataBody(typeIPage));
    }
}
