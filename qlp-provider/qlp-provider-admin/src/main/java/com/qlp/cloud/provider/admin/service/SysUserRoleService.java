package com.qlp.cloud.provider.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qlp.cloud.provider.admin.api.entity.SysUserRole;

/**
 * 用户角色表 服务类
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据用户Id删除该用户的角色关系
     *
     * @param userId 用户ID
     * @return boolean
     */
    Boolean removeRoleByUserId(Integer userId);
}
