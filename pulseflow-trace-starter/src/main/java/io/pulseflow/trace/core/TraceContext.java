package io.pulseflow.trace.core;

import java.util.UUID;

import org.slf4j.MDC;

public final class TraceContext {

	private static final ThreadLocal<String> TRACE_ID = new ThreadLocal<>();
	private static final ThreadLocal<Integer> DEPTH = ThreadLocal.withInitial(() -> 0);

	private static final String MDC_TRACE_KEY = "traceId";

	private TraceContext() {
	}

	public static String enter() {
		int depth = DEPTH.get();
		DEPTH.set(depth + 1);

		if (depth == 0) {
			// First entry in this thread
			String traceId = TRACE_ID.get();

			if (traceId == null) {
				// Try to reuse traceId from MDC (e.g., propagated from parent thread)
				String fromMdc = MDC.get(MDC_TRACE_KEY);
				if (fromMdc != null && !fromMdc.isEmpty()) {
					traceId = fromMdc;
				} else {
					traceId = UUID.randomUUID().toString();
				}
			}

			TRACE_ID.set(traceId);
			MDC.put(MDC_TRACE_KEY, traceId);
			return traceId;
		}

		return TRACE_ID.get();
	}

	public static void exit() {
		int depth = DEPTH.get() - 1;
		DEPTH.set(depth);

		if (depth == 0) {
			TRACE_ID.remove();
			MDC.remove(MDC_TRACE_KEY);
			DEPTH.remove();
		}
	}

	/** Used by TracingAspect */
	public static String getTraceId() {
		return enter(); // delegate to existing logic
	}

	/** Used by TracingAspect */
	public static void clear() {
		exit(); // delegate to existing logic
	}
}
