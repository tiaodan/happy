package com.haha.business.audiobook.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haha.business.audiobook.domain.Chapter;
import com.haha.business.audiobook.service.ChapterService;
import com.haha.common.controller.BaseController;
import com.haha.common.domain.JsonResponse;
import com.haha.common.domain.QueryRequest;
import com.haha.common.domain.ReturnCode;
import com.haha.common.domain.ReturnMessage;
import com.haha.common.exception.SysInnerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/api/chapter", "/chapter"})
public class ChapterController extends BaseController {
    // 初始化
    @Autowired
    ChapterService chapterService;


    /**
     * 增
     * @param chapter
     * @return
     * @throws SysInnerException
     */
    @PostMapping
    public Map<String, Object> addChapter(@RequestBody Chapter chapter) throws SysInnerException {
        // 1. 处理参数
        // 判断数据是否存在,判断索引 audiobookid+chapter_num
//        LambdaQueryWrapper<Chapter> chapterLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        chapterLambdaQueryWrapper.eq(Chapter::getChapterNum, chapter.getChapterNum());
//        chapterLambdaQueryWrapper.eq(Chapter::getAudiobookid, chapter.getAudiobookid());
//        int count = chapterService.count(chapterLambdaQueryWrapper);
//        if (count > 0) {
//            throw new SysInnerException(ReturnMessage.DATA_EXISTED.getMessage());
//        }
        // 2. 调用service
        chapterService.saveOrUpdate(chapter);

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.ADD_OK);
    }


    /**
     * 删
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public Map<String, Object> batchDeleteChapter(@NotBlank @PathVariable String ids) throws SysInnerException {
        // 1. 处理参数
        String[] idsArr = ids.split(StringPool.COMMA);
        // 2. 调用service
        chapterService.removeByIds(Arrays.asList(idsArr));

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.DELETE_OK);
    }


    /**
     * 改
     * @param chapter
     * @return
     * @throws SysInnerException
     */
    @PutMapping
    public Map<String, Object> updateChapter(@RequestBody Chapter chapter) throws SysInnerException {
        // 1. 处理参数
        // 2. 调用service
        chapterService.updateById(chapter);

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.UPDATE_OK);
    }

    // 查 - all

    /**
     * 查 - all, 这个方法返回数据很多,避免影响性能,默认只查10条数据
     * @return
     * @throws SysInnerException
     */
    /*@GetMapping("/all")
    public Map<String, Object> selectChapterAll() throws SysInnerException {
        // 1. 处理参数
        // 2. 调用service
        List<Chapter> allList =  chapterService.list();
        IPage<Chapter> chapterIpage = new Page<>();
        chapterIpage.setRecords(allList);
        chapterIpage.setTotal(allList.size());

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.SELECT_OK).data(getDataBody(chapterIpage));
    }*/


    // 查
    @GetMapping
    public Map<String, Object> selectChapter(QueryRequest queryRequest) throws SysInnerException {
        // 1. 处理参数
        // 2. 调用service
        IPage<Chapter> chapterIpage = chapterService.page(new Page<Chapter>(queryRequest.getCurrent(), queryRequest.getSize()));

        // 3. 返回
        return new JsonResponse().returnCode(ReturnCode.OK).message(ReturnMessage.SELECT_OK).data(getDataBody(chapterIpage));
    }
}
