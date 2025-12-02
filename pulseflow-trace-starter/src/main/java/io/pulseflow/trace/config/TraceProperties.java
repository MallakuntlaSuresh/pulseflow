package io.pulseflow.trace.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "pulseflow")
public class TraceProperties {

    /**
     * Logical service name for tracing
     */
    private String serviceName = "unknown-service";

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
