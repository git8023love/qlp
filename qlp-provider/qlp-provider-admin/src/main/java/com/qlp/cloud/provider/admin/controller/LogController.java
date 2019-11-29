package com.qlp.cloud.provider.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qlp.cloud.provider.admin.api.entity.SysLog;
import com.qlp.cloud.provider.admin.service.SysLogService;
import com.qlp.cloud.common.core.util.R;
import com.qlp.cloud.common.security.annotation.Inner;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 日志表 前端控制器
 */
@RestController
@AllArgsConstructor
@RequestMapping("/log")
public class LogController {
    private final SysLogService sysLogService;

    /**
     * 简单分页查询
     *
     * @param page   分页对象
     * @param sysLog 系统日志
     * @return
     */
    @GetMapping("/page")
    public R getLogPage(Page page, SysLog sysLog) {
        return new R<>(sysLogService.page(page, Wrappers.query(sysLog)));
    }

    /**
     * 删除日志
     *
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_log_del')")
    public R removeById(@PathVariable Long id) {
        return new R<>(sysLogService.removeById(id));
    }

    /**
     * 插入日志
     *
     * @param sysLog 日志实体
     * @return success/false
     */
    @Inner
    @PostMapping
    public R save(@Valid @RequestBody SysLog sysLog) {
        return new R<>(sysLogService.save(sysLog));
    }

}