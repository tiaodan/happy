package com.haha.business.audiobook.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haha.business.audiobook.dao.TypeMapper;
import com.haha.business.audiobook.domain.Type;
import com.haha.business.audiobook.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("TypeService")
@Transactional(rollbackFor = Exception.class)
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

    @Override
    public IPage<Type> getTypeAll() {
        log.info(list().toString());
        return null;
    }
}
