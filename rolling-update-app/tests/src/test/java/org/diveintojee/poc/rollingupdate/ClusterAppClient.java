package org.diveintojee.poc.rollingupdate;

import com.google.common.collect.Maps;
import org.diveintojee.poc.rollingupdate.domain.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Component
public class ClusterAppClient {

    @Autowired
    RestOperations restTemplate;
    @Value("${app1.host}")
    private String host;
    @Value("${app1.port}")
    private int port;

    private String getResourceLocation() {
        return "http://" + host + ":" + port + "/api/domains";
    }

    public URI createDomain(Domain domain) throws IOException {
        return restTemplate.postForLocation(getResourceLocation(), domain);
    }

    public Domain loadDomain(URI uri) {
        ResponseEntity<Domain> responseEntity = restTemplate.getForEntity(uri, Domain.class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    public void updateDomain(URI uri, Domain persisted) {
        restTemplate.put(uri, persisted);
    }

    public void deleteResource(URI uri) {
        restTemplate.delete(uri);
    }

    public void deleteAllDomains() {
        restTemplate.delete(getResourceLocation());
    }

    public List<Domain> findAllDomains() {
        Map<String, ?> uriVariables = Maps.newHashMap();
        HttpEntity entity = new HttpEntity(null);
        ResponseEntity<List<Domain>> response = restTemplate
                .exchange(getResourceLocation(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Domain>>() {
                }, uriVariables);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        return response.getBody();
    }

}
