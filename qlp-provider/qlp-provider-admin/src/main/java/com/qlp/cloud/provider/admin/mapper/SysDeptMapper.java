package com.qlp.cloud.provider.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qlp.cloud.provider.admin.api.entity.SysDept;

import java.util.List;

/**
 * 部门管理 Mapper 接口
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 关联dept——relation
     *
     * @return 数据列表
     */
    List<SysDept> listDepts();
}
