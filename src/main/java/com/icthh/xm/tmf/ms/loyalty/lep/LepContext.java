package com.icthh.xm.tmf.ms.loyalty.lep;

import com.icthh.xm.commons.config.client.service.TenantConfigService;
import com.icthh.xm.commons.lep.api.BaseLepContext;
import com.icthh.xm.commons.permission.service.PermissionCheckService;
import com.icthh.xm.tmf.ms.loyalty.service.SeparateTransactionExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * Keeps the names LEP scripts used with the xm-commons 2 bindings: {@code lepContext.services.tenantConfigService},
 * {@code services.permissionService}, {@code services.separateTransactionExecutor}, {@code templates.rest},
 * {@code templates.jdbc}. {@code lepContext.commons} is set by xm-commons.
 */
public class LepContext extends BaseLepContext {

    public LepServices services;
    public LepTemplates templates;

    public static class LepServices {
        public TenantConfigService tenantConfigService;
        public PermissionCheckService permissionService;
        public SeparateTransactionExecutor separateTransactionExecutor;
    }

    public static class LepTemplates {
        public RestTemplate rest;
        public JdbcTemplate jdbc;
    }
}
