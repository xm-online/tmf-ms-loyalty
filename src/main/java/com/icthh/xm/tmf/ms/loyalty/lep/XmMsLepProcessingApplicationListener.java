package com.icthh.xm.tmf.ms.loyalty.lep;


import com.icthh.xm.commons.config.client.service.TenantConfigService;
import com.icthh.xm.commons.lep.commons.CommonsExecutor;
import com.icthh.xm.commons.lep.commons.CommonsService;
import com.icthh.xm.commons.lep.spring.SpringLepProcessingApplicationListener;
import com.icthh.xm.commons.permission.service.PermissionCheckService;
import com.icthh.xm.lep.api.ScopedContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.icthh.xm.tmf.ms.loyalty.lep.LepXmAccountMsConstants.*;

/**
 * The {@link XmMsLepProcessingApplicationListener} class.
 */
@Component
public class XmMsLepProcessingApplicationListener extends SpringLepProcessingApplicationListener {


    private final TenantConfigService tenantConfigService;

    private final RestTemplate restTemplate;

    private final JdbcTemplate jdbcTemplate;

    private final CommonsService commonsService;
    private final PermissionCheckService permissionCheckService;

    public XmMsLepProcessingApplicationListener(TenantConfigService tenantConfigService,
                                                        @Qualifier("loadBalancedRestTemplate") RestTemplate restTemplate,
                                                        CommonsService commonsService, PermissionCheckService permissionCheckService,
                                                        JdbcTemplate jdbcTemplate) {
        this.tenantConfigService = tenantConfigService;
        this.restTemplate = restTemplate;
        this.commonsService = commonsService;
        this.permissionCheckService = permissionCheckService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    protected void bindExecutionContext(ScopedContext executionContext) {
        // services
        Map<String, Object> services = new HashMap<>();
        services.put(BINDING_SUB_KEY_SERVICE_TENANT_CONFIG_SERVICE, tenantConfigService);
        services.put(BINDING_SUB_KEY_PERMISSION_SERVICE, permissionCheckService);

        executionContext.setValue(BINDING_KEY_COMMONS, new CommonsExecutor(commonsService));
        executionContext.setValue(BINDING_KEY_SERVICES, services);

        // templates
        Map<String, Object> templates = new HashMap<>();
        templates.put(BINDING_SUB_KEY_TEMPLATE_REST, restTemplate);
        templates.put(BINDING_KEY_JDBC, jdbcTemplate);
        executionContext.setValue(BINDING_KEY_TEMPLATES, templates);
    }
}
