package com.patern.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patern.constant.ApiConstant;
import com.patern.service.RmsDeptService;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @since 2017-09-07
 */
@RestController
@RequestMapping(ApiConstant.RMS_DEPT)
public class RmsDeptController {
    private static final Log log = LogFactory.get();

    @Autowired
    private RmsDeptService rmsDeptService;
}
