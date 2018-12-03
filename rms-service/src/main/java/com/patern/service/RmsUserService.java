package com.patern.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.patern.cache.CacheService;
import com.patern.db.mapper.rms.RmsUserMapper;
import com.patern.entity.rms.po.RmsUser;
import com.patern.entity.rms.vo.RmsUserVo;
import com.patern.enums.RmsUserStatusEnum;
import com.patern.exception.RmsException;
import com.patern.exception.RmsExceptionEnum;
import com.patern.utils.BlankUtil;
import com.patern.utils.TimeUtil;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @since 2017-09-07
 */
@Service
public class RmsUserService extends ServiceImpl<RmsUserMapper, RmsUser> {
    private static final Log log = LogFactory.get();

    @Autowired
    private RmsUserMapper rmsUserMapper;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private SqlSessionFactory factory;

    /**
     * 查询用户信息
     */
    public Page<RmsUserVo> selectUserList(Integer currentPage, Integer showCount) {
        Page<RmsUserVo> page = new Page<>(currentPage, showCount);
        List<RmsUserVo> rmsUserList = rmsUserMapper.selectUserList(page);
//        for (RmsUserVo rmsUserVo : rmsUserMapper.selectUserList(page)) {
//            Map<String, Object> ruv = new HashMap<String, Object>(16);
//            ruv.put("id", rmsUserVo.getId());
//            ruv.put("name", rmsUserVo.getName());
//            ruv.put("account", rmsUserVo.getAccount());
//            ruv.put("password", rmsUserVo.getPassword());
//            ruv.put("phone", rmsUserVo.getPhone());
//            ruv.put("birthday", rmsUserVo.getBirthday());
//            ruv.put("sex", RmsUserSexEnum.getByStatus(rmsUserVo.getSex()).getRemark());
//            ruv.put("status", RmsUserStatusEnum.getByStatus(rmsUserVo.getStatus()).getRemark());
//            ruv.put("createTime", TimeUtil.getDateByTimestamp(rmsUserVo.getCreateTime(), DatePattern.NORM_DATETIME_PATTERN));
//            ruv.put("createUserName", rmsUserVo.getCreateUserName());
//            ruv.put("deptName", rmsUserVo.getDeptName());
//            ruv.put("roleName", rmsUserVo.getRoleName());
//            rmsUserList.add(ruv);
//        }
        page.setRecords(rmsUserList);
        return page;

    }

    /**
     * 添加/修改用户
     */
    public void addOrUpdateUser(RmsUser rmsUser) {
        // 添加
        if (BlankUtil.isBlank(rmsUser.getId())) {
            // 判断账号是否重复
            RmsUser theUser = selectOne(new EntityWrapper().where("account = {0}", rmsUser.getAccount()));
            if (!BlankUtil.isBlank(theUser)) {
                throw new RmsException(RmsExceptionEnum.USER_ALREADY_REG);
            } else {
                rmsUser.setCreateTime(TimeUtil.getSecondTimestamp());
                rmsUser.setStatus(RmsUserStatusEnum.OPEN.getStatus());
                insert(rmsUser);
            }
        } else { //修改
            updateById(rmsUser);
        }
    }

    /**
     * mybatis的一级进行测试，只查询了一次
     * @param id
     * @return
     */
    public RmsUserVo selectUserById(Integer id) {
        // 自动提交事务
        SqlSession sqlSession = factory.openSession(true);
        RmsUserMapper rmsUserMapper = sqlSession.getMapper(RmsUserMapper.class);
        Map<String,Object> paramsMap = new HashMap<>(16);
        paramsMap.put("id",id);
        RmsUserVo rmsUser = rmsUserMapper.selectOneUser(paramsMap);
        rmsUserMapper.selectOneUser(paramsMap);
        rmsUserMapper.selectOneUser(paramsMap);
        rmsUserMapper.selectOneUser(paramsMap);
        return rmsUser;
//        return cacheService.selectUserById(id);
    }

    public RmsUserVo selectUserByAccout(String account) {
        return cacheService.selectUserByAccout(account);
    }
}














