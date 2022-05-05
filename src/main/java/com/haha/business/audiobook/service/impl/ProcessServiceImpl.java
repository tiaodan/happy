package com.haha.business.audiobook.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haha.business.audiobook.dao.ProcessMapper;
import com.haha.business.audiobook.domain.Process;
import com.haha.business.audiobook.service.ProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ProcessService")
@Transactional(rollbackFor = Exception.class)
public class ProcessServiceImpl extends ServiceImpl<ProcessMapper, Process> implements ProcessService {
}
