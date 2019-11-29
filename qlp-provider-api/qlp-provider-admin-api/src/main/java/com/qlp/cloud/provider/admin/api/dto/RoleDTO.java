package com.qlp.cloud.provider.admin.api.dto;

import com.qlp.cloud.provider.admin.api.entity.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色Dto
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends SysRole {
    /**
     * 角色部门Id
     */
    private Integer roleDeptId;

    /**
     * 部门名称
     */
    private String deptName;
}
