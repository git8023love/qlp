package com.qlp.cloud.provider.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qlp.cloud.provider.admin.api.entity.SysLog;
import com.qlp.cloud.provider.admin.mapper.SysLogMapper;
import com.qlp.cloud.provider.admin.service.SysLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

}
