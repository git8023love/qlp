package com.qlp.cloud.provider.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qlp.cloud.provider.admin.api.entity.SysUserRole;
import com.qlp.cloud.provider.admin.mapper.SysUserRoleMapper;
import com.qlp.cloud.provider.admin.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户角色表 服务实现类
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    /**
     * 根据用户Id删除该用户的角色关系
     *
     * @param userId 用户ID
     * @return boolean
     */
    @Override
    public Boolean removeRoleByUserId(Integer userId) {
        return baseMapper.deleteByUserId(userId);
    }
}
