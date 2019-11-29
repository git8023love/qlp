package com.qlp.cloud.provider.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qlp.cloud.provider.admin.api.entity.SysOauthClientDetails;
import com.qlp.cloud.provider.admin.mapper.SysOauthClientDetailsMapper;
import com.qlp.cloud.provider.admin.service.SysOauthClientDetailsService;
import com.qlp.cloud.common.core.constant.SecurityConstants;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
public class SysOauthClientDetailsServiceImpl extends ServiceImpl<SysOauthClientDetailsMapper, SysOauthClientDetails> implements SysOauthClientDetailsService {

    /**
     * 通过ID删除客户端
     *
     * @param id
     * @return
     */
    @Override
    @CacheEvict(value = SecurityConstants.CLIENT_DETAILS_KEY, key = "#id")
    public Boolean removeClientDetailsById(String id) {
        System.out.println("id =================> " + id);
        return this.removeById(id);
    }

    /**
     * 根据客户端信息
     *
     * @param clientDetails
     * @return
     */
    @Override
    @CacheEvict(value = SecurityConstants.CLIENT_DETAILS_KEY, key = "#clientDetails.clientId")
    public Boolean updateClientDetailsById(SysOauthClientDetails clientDetails) {
        System.out.println("clientDetails =================> " + clientDetails.toString());
        return this.updateById(clientDetails);
    }
}
