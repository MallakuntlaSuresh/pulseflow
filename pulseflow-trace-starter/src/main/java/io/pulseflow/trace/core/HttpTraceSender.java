package io.pulseflow.trace.core;

import io.pulseflow.protocol.trace.TraceEvent;
import org.springframework.web.client.RestTemplate;

public class HttpTraceSender implements TraceSender {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String endpoint;

    public HttpTraceSender(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public void send(TraceEvent event) {
        try {
            restTemplate.postForEntity(endpoint, event, Void.class);
        } catch (Exception ex) {
        }
    }
}
