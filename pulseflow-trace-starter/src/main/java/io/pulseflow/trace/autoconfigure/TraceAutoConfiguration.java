package io.pulseflow.trace.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import io.pulseflow.trace.config.TraceProperties;
import io.pulseflow.trace.core.HttpTraceSender;
import io.pulseflow.trace.core.LogTraceReporter;
import io.pulseflow.trace.core.PulseflowTaskDecorator;
import io.pulseflow.trace.core.TraceReporter;
import io.pulseflow.trace.core.TraceSender;
import io.pulseflow.trace.core.TracingAspect;
import jakarta.annotation.PostConstruct;
@Configuration
@EnableAspectJAutoProxy
@ConditionalOnClass(TracingAspect.class)
@EnableConfigurationProperties(TraceProperties.class)
public class TraceAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TraceReporter traceReporter() {
        return new LogTraceReporter();
    }

    @Bean
    public TracingAspect tracingAspect(
            TraceReporter traceReporter,
            TraceSender traceSender,
            TraceProperties properties) {

        return new TracingAspect(
                traceReporter,
                traceSender,
                properties.getServiceName()
        );
    }


    @Bean
    @ConditionalOnMissingBean
    public PulseflowTaskDecorator pulseflowTaskDecorator() {
        return new PulseflowTaskDecorator();
    }
    @Bean
    @ConditionalOnMissingBean
    public TraceSender traceSender(TraceProperties properties) {
        if (properties.getCollectorUrl() != null) {
            return new HttpTraceSender(properties.getCollectorUrl());
        }
        return event -> {}; 
    }
}
