package org.diveintojee.poc.rollingupdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Component
public class RollingUpdateAppClient {

    @Autowired
    RestOperations restTemplate;
    @Value("${app.host}")
    private String host;
    @Value("${app.port}")
    private int port;

    private String getResourceLocation() {
        return "http://" + host + ":" + port + "/api/maths";
    }

    public Double sum(Double a, Double b) {
        ResponseEntity<Double> responseEntity = restTemplate.getForEntity(getResourceLocation() + "/sum/" + a + "/" + b, Double.class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        return responseEntity.getBody();

    }

    public Double power(double a, double b) {
        ResponseEntity<Double> responseEntity = restTemplate.getForEntity(getResourceLocation() + "/power/" + a + "/" + b, Double.class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        return responseEntity.getBody();
    }
}
