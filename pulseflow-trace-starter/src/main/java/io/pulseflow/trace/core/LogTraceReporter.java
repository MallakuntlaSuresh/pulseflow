package io.pulseflow.trace.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTraceReporter implements TraceReporter {

    private static final Logger log =
            LoggerFactory.getLogger(LogTraceReporter.class);

    @Override
    public void report(TraceEvent event) {
        log.info(
            "service={} operation={} durationMs={}",
            event.getService(),
            event.getOperation(),
            event.getDurationMs()
        );
    }
}
