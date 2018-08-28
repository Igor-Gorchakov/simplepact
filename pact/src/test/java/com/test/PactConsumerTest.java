package com.test;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PactConsumerTest {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("Provider", "localhost", 8080, this);

    @Pact(consumer = "Consumer")
    public RequestResponsePact buildConsumer(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap();
        headers.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return builder
                .given("test GET")
                    .uponReceiving("A request for JSON")
                    .path("/provider")
                    .method("GET")
                .willRespondWith()
                    .status(HttpStatus.OK.value())
                    .headers(headers)
                    .body("{\"value\":\"ok\"}")
                .toPact();
    }


    @Test
    @PactVerification()
    public void testGetRequestForJson() {
        // when
        ResponseEntity<String> response = new RestTemplate().getForEntity(mockProvider.getUrl() + "/provider", String.class);

        // then
        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertTrue(response.getHeaders().get("Content-Type").contains(MediaType.APPLICATION_JSON_VALUE));
        assertEquals(response.getBody(), "{\"value\":\"ok\"}");
    }
}
