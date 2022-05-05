package com.haha.business.audiobook.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.haha.business.audiobook.dao.TypeMapper;
import com.haha.business.audiobook.domain.Type;
import com.haha.business.audiobook.service.TypeService;
import com.haha.common.controller.BaseController;
import com.haha.common.domain.JsonResponse;
import com.haha.common.domain.QueryRequest;
import com.haha.common.domain.ReturnCode;
import com.haha.common.domain.ReturnMessage;
import com.haha.common.exception.SysInnerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping({"/api/type", "/type"})
public class TypeController extends BaseController {
    // 初始化
    @Autowired
    TypeService typeService;

    @Autowired
    TypeMapper typeMapper;


    /**
     * 增
     * @param type
     * @return
     */
    @PostMapping
    public Map<String, Object> addType(@RequestBody Type type) throws SysInnerException {
        // 1. 处理参数
        // 判断要添加的数据是否存在
        String typeName = type.getName();
        int count = typeService.count( new LambdaQueryWrapper<Type>().eq(Type::getName, typeName) );
//        int count = typeMapper.selectCount( new LambdaQueryChainWrapper<Type>().eq(Type::getName, typeName) );
        if ( count > 0 ) {
            throw new SysInnerException(ReturnMessage.DATA_EXISTED.getMessage());
        }

        // 2. 调用 service
        typeService.saveOrUpdate(type);

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.ADD_OK);
    }


    /**
     * 批量删
     * @param ids String 通过逗号, 分隔的字符串数组
     * ? @NotBlank没作用
     * @return
     */
    @DeleteMapping("/{ids}")
    public Map<String, Object> batchDeleteType(@NotBlank @PathVariable String ids) throws SysInnerException {
        // 1. 处理参数
        String[] idsArr = ids.split(StringPool.COMMA);

        // 2. 调用 service
        typeService.removeByIds(Arrays.asList(idsArr));
        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.DELETE_OK);
    }


    // 改
    @PutMapping
    public Map<String, Object> updateType(@RequestBody Type type) throws SysInnerException {
        // 1. 处理参数
        // 2. 调用 service
        typeService.updateById(type);

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.UPDATE_OK);
    }


    // 查 所有
    @GetMapping("/all")
    public Map<String, Object> selectTypeAll() throws SysInnerException {
        // 1. 处理参数

        // 2. 调用service
        // 集合转IPage
        List<Type> typeAllList = typeService.list();
        IPage<Type> typeIPage = new Page<>();
        typeIPage.setRecords(typeAllList);  // 设置rows 内容, 在Json用"row":[{"id":1},{"id":2}] ？表示
        typeIPage.setTotal(typeAllList.size());  // 设置总数total

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.SELECT_OK).data(getDataBody(typeIPage));
    }


    /**
     *
     * @param queryRequest 带分页信息
     * @return
     */
    @GetMapping
    public Map<String, Object> selectTypeByCondition(QueryRequest queryRequest) throws SysInnerException {
        // 1. 处理参数
        // 2. 调用service
        IPage<Type> iPageArgs = new Page<>(queryRequest.getCurrent(), queryRequest.getSize());
        IPage<Type> typeIPage = typeService.page(iPageArgs);

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.OK).data(getDataBody(typeIPage));
    }
}
