# Pulseflow — Lightweight Distributed Tracing for Spring Boot

Pulseflow is a lightweight, annotation-based distributed tracing system for Spring Boot.
It captures method execution times, propagates trace IDs across threads, and optionally
exports events to a collector service.

## Modules

pulseflow/
 ├── pulseflow-trace-starter     → Auto tracing for Spring Boot apps
 ├── pulseflow-collector         → Receives and logs trace events
 └── pulseflow-protocol          → Shared TraceEvent model


## Usage

### 1. Add `@Traced` to any method


@Traced
public String createOrder() {
    return "OK";
}


### 2. Log Output Example

traceId=abc123 service=order-service operation=OrderService.createOrder() durationMs=117


### 3. Async methods keep the same traceId

Pulseflow propagates MDC context to async threads automatically.

## Collector Endpoint

The collector exposes:
POST /traces
It receives a 'TraceEvent' with:
traceId, service, operation, durationMs, timestamp

## Purpose
Pulseflow is designed for developers who want simple, method-level tracing without heavy tools like Zipkin or OpenTelemetry.

## Author
Mallakuntla Suresh

