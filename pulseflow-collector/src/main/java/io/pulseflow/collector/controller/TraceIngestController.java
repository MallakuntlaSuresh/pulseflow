package io.pulseflow.collector.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.pulseflow.collector.service.TraceIngestService;
import io.pulseflow.protocol.trace.TraceEvent;

@RestController
@RequestMapping("/traces")
public class TraceIngestController {

	private final TraceIngestService service;

	public TraceIngestController(TraceIngestService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Void> ingest(@RequestBody TraceEvent event) {
		service.process(event);
		return ResponseEntity.accepted().build();
	}
}
