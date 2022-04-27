package com.haha.business.audiobook.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haha.business.audiobook.domain.Type;

public interface TypeService extends IService<Type> {
    IPage<Type> getTypeAll();
}
