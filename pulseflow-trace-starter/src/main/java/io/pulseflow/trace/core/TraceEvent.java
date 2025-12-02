package io.pulseflow.trace.core;

import java.time.Instant;

public class TraceEvent {

	private final String traceId;
	private final String service;
	private final String operation;
	private final long durationMs;
	private final Instant timestamp;

	public TraceEvent(String traceId, String service, String operation, long durationMs) {
		this.traceId = traceId;
		this.service = service;
		this.operation = operation;
		this.durationMs = durationMs;
		this.timestamp = Instant.now();
	}

	public String getTraceId() {
		return traceId;
	}

	public String getService() {
		return service;
	}

	public String getOperation() {
		return operation;
	}

	public long getDurationMs() {
		return durationMs;
	}

	public Instant getTimestamp() {
		return timestamp;
	}
}
