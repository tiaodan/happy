package com.haha.business.audiobook.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haha.business.audiobook.domain.Process;
import com.haha.business.audiobook.service.ProcessService;
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

import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping({"/api/process", "/process"})
public class ProcessController extends BaseController {
    // 初始化
    @Autowired
    ProcessService processService;


    /**
     * 增
     * @param process
     * @return
     */
    @PostMapping
    public Map<String, Object> addProcess(@RequestBody Process process) throws SysInnerException {
        // 1. 处理参数
        // 判断数据是否存在
        int count = processService.count(new LambdaQueryWrapper<Process>().eq(Process::getName, process.getName()));
        if (count > 0) {
            throw new SysInnerException(ReturnMessage.DATA_EXISTED.getMessage());
        }

        // 2. 调用service
        processService.saveOrUpdate(process);
        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.ADD_OK);
    }


    /**
     * 删
     * @param ids
     * @return
     * @throws SysInnerException
     */
    @DeleteMapping("/{ids}")
    public Map<String, Object> deleteProcess(@NotBlank @PathVariable String ids) throws SysInnerException {
        // 1. 处理参数
        String[] idsArr = ids.split(StringPool.COMMA);
        // 2. 调用service
        processService.removeByIds(Arrays.asList(idsArr));

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.DELETE_OK);
    }


    /**
     * 改
     * @param process
     * @return
     */
    @PutMapping
    public Map<String, Object> updateProcess(@RequestBody Process process) {
        // 1. 处理参数

        // 2. 调用service
        processService.updateById(process);
        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.UPDATE_OK);
    }


    /**
     * 查-all
     * @return
     */
    @GetMapping("/all")
    public Map<String, Object> selectProcessAll() {
        // 1. 处理参数
        // 2. 调用service
        List<Process> allList =  processService.list();
        IPage<Process> procesIpage = new Page<>();
        procesIpage.setRecords(allList);
        procesIpage.setTotal(allList.size());

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.SELECT_OK).data(getDataBody(procesIpage));
    }


    // 查
    @GetMapping
    public Map<String, Object> selectProcess(QueryRequest queryRequest) {
        // 1. 处理参数
        // 2. 调用service
        IPage<Process> processIpage = processService.page(new Page<>(queryRequest.getCurrent(), queryRequest.getSize()));

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.SELECT_OK).data(getDataBody(processIpage));
    }
}
