package com.haha.business.audiobook.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haha.business.audiobook.dao.WebsiteMapper;
import com.haha.business.audiobook.domain.Website;
import com.haha.business.audiobook.service.WebsiteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("WebsiteService")
@Transactional(rollbackFor = Exception.class)
public class WebsiteServiceImpl extends ServiceImpl<WebsiteMapper, Website> implements WebsiteService{

}
