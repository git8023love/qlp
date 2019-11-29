package com.qlp.cloud.provider.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qlp.cloud.provider.admin.api.entity.SysRole;

import java.util.List;

/**
 * Mapper 接口
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 通过用户ID，查询角色信息
     *
     * @param userId
     * @return
     */
    List<SysRole> listRolesByUserId(Integer userId);
}
