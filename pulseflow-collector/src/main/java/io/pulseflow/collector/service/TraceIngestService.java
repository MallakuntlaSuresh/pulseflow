package io.pulseflow.collector.service;

import io.pulseflow.protocol.trace.TraceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TraceIngestService {

    private static final Logger log =
            LoggerFactory.getLogger("PULSEFLOW-COLLECTOR");

    public void process(TraceEvent event) {
        if (event.getTraceId() == null || event.getService() == null) {
            throw new IllegalArgumentException("Invalid TraceEvent");
        }

        log.info(
            "traceId={} service={} operation={} durationMs={}",
            event.getTraceId(),
            event.getService(),
            event.getOperation(),
            event.getDurationMs()
        );

        // v2 → forward to Kafka / OTLP exporter
    }
}
