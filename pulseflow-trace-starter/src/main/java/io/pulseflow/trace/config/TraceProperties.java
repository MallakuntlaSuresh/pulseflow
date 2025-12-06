package io.pulseflow.trace.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
@ConfigurationProperties(prefix = "pulseflow")
public class TraceProperties {

    private String serviceName;
    private String collectorUrl;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCollectorUrl() {
        return collectorUrl;
    }

    public void setCollectorUrl(String collectorUrl) {
        this.collectorUrl = collectorUrl;
    }
}
