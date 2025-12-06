package io.pulseflow.trace.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import io.pulseflow.protocol.trace.TraceEvent;

@Aspect
public class TracingAspect {

	private final TraceReporter traceReporter;
	private final TraceSender traceSender;
	private final String serviceName;

	public TracingAspect(TraceReporter traceReporter, TraceSender traceSender, String serviceName) {
		this.traceReporter = traceReporter;
		this.traceSender = traceSender;
		this.serviceName = serviceName;
	}

	@Around("@annotation(io.pulseflow.trace.annotation.Traced)")
	public Object trace(ProceedingJoinPoint pjp) throws Throwable {

		long start = System.currentTimeMillis();
		String traceId = TraceContext.getTraceId();

		try {
			return pjp.proceed();
		} finally {
			long duration = System.currentTimeMillis() - start;

			TraceEvent event = new TraceEvent(traceId, serviceName, pjp.getSignature().toShortString(), duration,
					System.currentTimeMillis());

			traceReporter.report(event);

			traceSender.send(event);

			TraceContext.clear();
		}
	}
}
