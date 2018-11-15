package com.patern.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patern.service.RmsRoleMenuService;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

/**
 * <p>
 * 角色权限表 前端控制器
 * </p>
 *
 * @since 2017-09-07
 */
@RestController
@RequestMapping("/rmsRoleMenu")
public class RmsRoleMenuController {
    private static final Log log = LogFactory.get();

    @Autowired
    private RmsRoleMenuService rmsRoleMenuService;
}
