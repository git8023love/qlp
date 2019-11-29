qlp
├── qlp-auth -- 授权服务提供[3000]
└── qlp-common -- 系统公共模块 
     ├── qlp-common-core -- 公共工具类核心包
     ├── qlp-common-log -- 日志服务
     └── qlp-common-security -- 安全工具类
├── qlp-config -- 配置中心[8888]
├── qlp-eureka -- 服务注册与发现[8761]
├── qlp-gateway -- Spring Cloud Gateway网关[9999]
└── qlp-provider -- 通用用户权限管理模块
     ├── qlp-provider-admin -- 通用用户权限管理系统业务处理模块[4000]
     ├── qlp-provider-oa -- 办公协同系统业务处理模块[5004]
     └── qlp-provider-quratz -- 定时任务系统业务处理模块[5005]
└── qlp-provider-api 公共Api模块
     ├── qlp-provider-admin-api -- 通用用户权限管理系统公共api模块
     ├── qlp-provider-oa-api -- 办公协同系统公共api模块
     └── qlp-provider-quratz-api -- 定时任务系统公共api模块
└── qlp-visual  -- 图形化模块 
     ├── qlp-monitor -- Spring Boot Admin监控 [5001]
     ├── qlp-zipkin -- 链路调用监控 [5002]
     └── qlp-codegen -- 图形化代码生成[5003]
