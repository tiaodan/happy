package com.haha.business.audiobook.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haha.business.audiobook.dao.ChapterMapper;
import com.haha.business.audiobook.domain.Chapter;
import com.haha.business.audiobook.service.ChapterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ChapterService")
@Transactional(rollbackFor = Exception.class)
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {
}
