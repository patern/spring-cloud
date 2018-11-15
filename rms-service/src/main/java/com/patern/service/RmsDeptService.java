package com.patern.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.patern.db.mapper.rms.RmsDeptMapper;
import com.patern.entity.rms.po.RmsDept;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @since 2017-09-07
 */
@Service
public class RmsDeptService extends ServiceImpl<RmsDeptMapper, RmsDept> {
    private static final Log log = LogFactory.get();

    @Autowired
    private RmsDeptMapper rmsDeptMapper;
}
