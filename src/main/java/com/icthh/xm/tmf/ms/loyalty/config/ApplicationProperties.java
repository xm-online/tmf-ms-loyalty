package com.icthh.xm.tmf.ms.loyalty.config;

import com.icthh.xm.commons.lep.TenantScriptStorage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;

/**
 * Properties specific to Loyalty.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private boolean kafkaEnabled;
    private String kafkaSystemTopic;
    private String kafkaSystemQueue;

    private final Retry retry = new Retry();
    private final Lep lep = new Lep();
    private List<String> tenantIgnoredPathList = Collections.emptyList();

    private boolean timelinesEnabled;

    @Getter
    @Setter
    public static class Lep {
        private TenantScriptStorage tenantScriptStorage;
        private String lepResourcePathPattern;
    }

    @Getter
    @Setter
    private static class Retry {
        private int maxAttempts;
        private long delay;
        private int multiplier;
    }
}
