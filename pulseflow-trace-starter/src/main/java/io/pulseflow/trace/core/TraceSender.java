package io.pulseflow.trace.core;

import io.pulseflow.protocol.trace.TraceEvent;

public interface TraceSender {
    void send(TraceEvent event);
}
