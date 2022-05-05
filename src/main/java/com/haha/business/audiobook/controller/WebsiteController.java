package com.haha.business.audiobook.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haha.business.audiobook.domain.Website;
import com.haha.business.audiobook.service.WebsiteService;
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
@RequestMapping({"/api/website", "/website"})
public class WebsiteController extends BaseController {
    // 初始化
    @Autowired
    WebsiteService websiteService;

    /**
     * 增
     * @param website
     * @return
     * @throws SysInnerException
     */
    @PostMapping
    public Map<String, Object> addWebsite(@RequestBody Website website) throws SysInnerException {
        // 1. 处理参数
        // 判断添加数据是否存在
        String url = website.getUrl();
        int count = websiteService.count(new LambdaQueryWrapper<Website>().eq(Website::getUrl, website.getUrl()));
        if (count > 0) {
            throw new SysInnerException(ReturnMessage.DATA_EXISTED.getMessage());
        }

        // 2. 调用service
        websiteService.saveOrUpdate(website);

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
    public Map<String, Object> batchDeleteWebsite(@NotBlank @PathVariable String ids) throws SysInnerException {
        // 1. 处理参数
        String[] idsArr = ids.split(StringPool.COMMA);

        // 2. 调用service
        websiteService.removeByIds(Arrays.asList(idsArr));

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.DELETE_OK);
    }


    /**
     * 改
     * @param website
     * @return
     */
    @PutMapping
    public Map<String, Object> updateWebsite(@RequestBody Website website) throws SysInnerException {
        // 1. 处理参数

        // 2. 调用service
        websiteService.updateById(website);

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.UPDATE_OK);
    }


    /**
     * 查 - all
     * @return
     * @throws SysInnerException
     */
    @GetMapping("/all")
    public Map<String, Object> selectWebsiteAll() throws SysInnerException {
        // 1. 处理参数

        // 2. 调用service
        List<Website> allList =  websiteService.list();
        IPage<Website> websiteIpage = new Page<>();
        websiteIpage.setRecords(allList);
        websiteIpage.setTotal(allList.size());

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.SELECT_OK).data(getDataBody(websiteIpage));
    }

    // 查
    @GetMapping
    public Map<String, Object> selectWebsite(QueryRequest queryRequest) throws SysInnerException {
        // 1. 处理参数
        // 2. 调用service
        IPage<Website> websiteIpage = websiteService.page(new Page<>(queryRequest.getCurrent(), queryRequest.getSize()));;
        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.SELECT_OK).data(getDataBody(websiteIpage));
    }

}
