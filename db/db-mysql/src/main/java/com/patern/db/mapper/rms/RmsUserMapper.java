package com.patern.db.mapper.rms;

import com.patern.entity.rms.po.RmsUser;
import com.patern.entity.rms.vo.RmsUserVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
  * 用户表 Mapper 接口
 * </p>
 *
 * @since 2017-09-07
 */
@Mapper
@Component
public interface RmsUserMapper extends BaseMapper<RmsUser> {
    /**
     * 分页查询用户列表
     * @param page
     * @return
     */
    List<RmsUserVo> selectUserList(Pagination page);

    /**
     * 根据条件查询用户
     * @param paramMap
     * @return
     */
    RmsUserVo selectOneUser(Map<String,Object> paramMap);
}
