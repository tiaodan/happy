package com.haha.business.audiobook.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haha.business.audiobook.dao.AudiobookMapper;
import com.haha.business.audiobook.domain.Audiobook;
import com.haha.business.audiobook.service.AudiobookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AudiobookService")
@Transactional(rollbackFor = Exception.class)
public class AudiobookServiceImpl extends ServiceImpl<AudiobookMapper, Audiobook> implements AudiobookService {

}
