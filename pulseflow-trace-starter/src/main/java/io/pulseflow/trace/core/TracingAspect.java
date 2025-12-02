package io.pulseflow.trace.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TracingAspect {

	private final TraceReporter traceReporter;
	private final String serviceName;

	public TracingAspect(TraceReporter traceReporter, String serviceName) {
		this.traceReporter = traceReporter;
		this.serviceName = serviceName;
	}

	@Around("@annotation(io.pulseflow.trace.annotation.Traced)")
	public Object trace(ProceedingJoinPoint pjp) throws Throwable {

	    long start = System.currentTimeMillis();
	    String traceId = TraceContext.enter();

	    try {
	        return pjp.proceed();
	    } finally {
	        long duration = System.currentTimeMillis() - start;

	        traceReporter.report(new TraceEvent(
	                traceId,
	                serviceName,
	                pjp.getSignature().toShortString(),
	                duration
	        ));

	        TraceContext.exit();
	    }
	}

}
