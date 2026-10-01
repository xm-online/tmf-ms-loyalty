package com.icthh.xm.tmf.ms.loyalty.lep;

import com.icthh.xm.commons.config.client.service.TenantConfigService;
import com.icthh.xm.commons.lep.api.BaseLepContext;
import com.icthh.xm.commons.lep.api.LepContextFactory;
import com.icthh.xm.commons.permission.service.PermissionCheckService;
import com.icthh.xm.lep.api.LepMethod;
import com.icthh.xm.tmf.ms.loyalty.service.SeparateTransactionExecutor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LoyaltyLepContextFactory implements LepContextFactory {

    private final TenantConfigService tenantConfigService;
    private final RestTemplate restTemplate;
    private final PermissionCheckService permissionCheckService;
    private final JdbcTemplate jdbcTemplate;
    private final SeparateTransactionExecutor transactionExecutor;

    public LoyaltyLepContextFactory(TenantConfigService tenantConfigService,
                                    @Qualifier("loadBalancedRestTemplate") RestTemplate restTemplate,
                                    PermissionCheckService permissionCheckService,
                                    JdbcTemplate jdbcTemplate,
                                    SeparateTransactionExecutor transactionExecutor) {
        this.tenantConfigService = tenantConfigService;
        this.restTemplate = restTemplate;
        this.permissionCheckService = permissionCheckService;
        this.jdbcTemplate = jdbcTemplate;
        this.transactionExecutor = transactionExecutor;
    }

    @Override
    public BaseLepContext buildLepContext(LepMethod lepMethod) {
        LepContext lepContext = new LepContext();
        lepContext.services = new LepContext.LepServices();
        lepContext.services.tenantConfigService = tenantConfigService;
        lepContext.services.permissionService = permissionCheckService;
        lepContext.services.separateTransactionExecutor = transactionExecutor;
        lepContext.templates = new LepContext.LepTemplates();
        lepContext.templates.rest = restTemplate;
        lepContext.templates.jdbc = jdbcTemplate;
        return lepContext;
    }
}
