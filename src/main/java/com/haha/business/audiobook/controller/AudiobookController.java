package com.haha.business.audiobook.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haha.business.audiobook.domain.Audiobook;
import com.haha.business.audiobook.service.AudiobookService;
import com.haha.common.controller.BaseController;
import com.haha.common.domain.JsonResponse;
import com.haha.common.domain.QueryRequest;
import com.haha.common.domain.ReturnCode;
import com.haha.common.domain.ReturnMessage;
import com.haha.common.exception.SysInnerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping({"/api/audiobook", "/audiobook"})
public class AudiobookController extends BaseController {
    // 初始化
    @Autowired
    AudiobookService audiobookService;


    /**
     * 增
     * @param audiobook
     * @return
     * @throws SysInnerException
     */
    @PostMapping
    public Map<String, Object> addAudiobook(@RequestBody Audiobook audiobook) throws SysInnerException {
        // 1. 处理参数
        // 判断数据是否存在???
        // 2. 调用service
        audiobookService.saveOrUpdate(audiobook);
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
    public Map<String, Object> batchDeleteAudiobook(@NotBlank @PathVariable String ids) throws SysInnerException {
        // 1. 处理参数
        String[] idsArr = ids.split(StringPool.COMMA);

        // 2. 调用service
        audiobookService.removeByIds(Arrays.asList(idsArr));

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.DELETE_OK);
    }


    /**
     * 改
     * @param audiobook
     * @return
     * @throws SysInnerException
     */
    @PutMapping
    public Map<String, Object> updateAudiobook(@PathVariable Audiobook audiobook) throws SysInnerException {
        // 1. 处理参数
        // 2. 调用service
        audiobookService.updateById(audiobook);

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.UPDATE_OK);
    }


    /**
     * 查
     * @param queryRequest
     * @return
     * @throws SysInnerException
     */
    @GetMapping
    public Map<String, Object> selectAudiobook(QueryRequest queryRequest) throws SysInnerException {
        // 1. 处理参数
        // 2. 调用service
        IPage<Audiobook> audiobookIpage = audiobookService.page(new Page<>(queryRequest.getCurrent(), queryRequest.getSize()));
        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.SELECT_OK).data(getDataBody(audiobookIpage));
    }
}
