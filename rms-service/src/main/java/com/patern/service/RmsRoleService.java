package com.patern.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.patern.db.mapper.rms.RmsRoleMapper;
import com.patern.entity.rms.po.RmsRole;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @since 2017-09-07
 */
@Service
public class RmsRoleService extends ServiceImpl<RmsRoleMapper, RmsRole> {
    private static final Log log = LogFactory.get();

    @Autowired
    private RmsRoleMapper rmsRoleMapper;
}
