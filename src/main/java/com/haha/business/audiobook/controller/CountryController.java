package com.haha.business.audiobook.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haha.business.audiobook.domain.Country;
import com.haha.business.audiobook.service.CountryService;
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
@RequestMapping({"/api/country", "/country"})
public class CountryController extends BaseController {
    // 初始化
    @Autowired
    CountryService countryService;


    /**
     * 增
     * @param country
     * @return
     * @throws SysInnerException
     */
    @PostMapping
    public Map<String, Object> addCountry(@RequestBody Country country) throws SysInnerException {
        // 1. 处理参数
        // 判断数据是否存在
        int count = countryService.count(new LambdaQueryWrapper<Country>().eq(Country::getName, country.getName()));
        if ( count > 0) {
            throw new SysInnerException(ReturnMessage.DATA_EXISTED.getMessage());
        }

        // 2. 调用service
        countryService.saveOrUpdate(country);

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
    public Map<String, Object> deleteCountry(@NotBlank @PathVariable String ids) throws SysInnerException{
        // 1. 处理参数
        // 2. 调用service
        String[] idsArr = ids.split(StringPool.COMMA);
        countryService.removeByIds(Arrays.asList(idsArr));

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.DELETE_OK);
    }


    /**
     * 改
     * @param country
     * @return
     * @throws SysInnerException
     */
    @PutMapping
    public Map<String, Object> updateCountry(@RequestBody Country country) throws SysInnerException {
        // 1. 处理参数
        // 2. 调用service
        countryService.updateById(country);

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.UPDATE_OK);
    }


    /**
     * 查 - all
     * @return
     * @throws SysInnerException
     */
    @GetMapping("/all")
    public Map<String, Object> selectCountry() throws SysInnerException {
        // 1. 处理参数
        // 2. 调用service
        List<Country> allList =  countryService.list();
        IPage<Country> countryIpage = new Page<>();
        countryIpage.setRecords(allList);
        countryIpage.setTotal(allList.size());

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.SELECT_OK).data(getDataBody(countryIpage));
    }


    /**
     * 查
     * @param queryRequest
     * @return
     * @throws SysInnerException
     */
    @GetMapping
    public Map<String, Object> selectCountry(QueryRequest queryRequest) throws SysInnerException{
        // 1. 处理参数
        // 2. 调用service
        IPage<Country> countryIpage = countryService.page(new Page<>(queryRequest.getCurrent(), queryRequest.getSize()));

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.SELECT_OK).data(getDataBody(countryIpage));
    }
}
