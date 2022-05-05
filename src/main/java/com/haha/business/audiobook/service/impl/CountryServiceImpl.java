package com.haha.business.audiobook.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haha.business.audiobook.dao.CountryMapper;
import com.haha.business.audiobook.domain.Country;
import com.haha.business.audiobook.service.CountryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("CountryService")
@Transactional(rollbackFor = Exception.class)
public class CountryServiceImpl extends ServiceImpl<CountryMapper, Country> implements CountryService {

}
