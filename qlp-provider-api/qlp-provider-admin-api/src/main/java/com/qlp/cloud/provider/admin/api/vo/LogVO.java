package com.qlp.cloud.provider.admin.api.vo;

import com.qlp.cloud.provider.admin.api.entity.SysLog;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@Data
public class LogVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private SysLog sysLog;
    private String username;
}
