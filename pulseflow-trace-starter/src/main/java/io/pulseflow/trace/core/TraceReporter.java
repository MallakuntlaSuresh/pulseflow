package io.pulseflow.trace.core;

public interface  TraceReporter {
	void report(TraceEvent event);
}
