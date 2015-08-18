package org.diveintojee.poc.rollingupdate;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.diveintojee.poc.rollingupdate.domain.Domain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ClusterTestConfig.class})
public class DomainResourceTestIT {

    @Autowired
    private ClusterAppClient api;
    @Autowired
    private RestOperations restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClusterAppClient.class);

    @Test
    public void crudDomainShouldSucceed() throws IOException {
        List<Domain> detachedList = Lists.newArrayList();
        final int countInstances = 20;
        for (int i = 0; i < countInstances; i++ ) {
            detachedList.add(Fixtures.validDomain());
        }
        List<URI> uriList = Lists.newArrayList();
        for (Domain domain : detachedList) {
            final URI uri = api.createDomain(domain);
            LOGGER.debug("Created URI {}", uri);
            assertNotNull(uri);
            uriList.add(uri);
        }
        List<Domain> persistedList = Lists.newArrayList();
        for (URI uri : uriList) {
            LOGGER.debug("Loading URI {}", uri);
            final Domain persisted = api.loadDomain(uri);
            assertNotNull(persisted);
            persistedList.add(persisted);
        }
        List<Domain> mergedList = Lists.newArrayList();
        for (int i = 0; i < countInstances; i++) {
            Domain merged = new Domain();
            merged.setId(persistedList.get(i).getId());
            merged.setDescription(detachedList.get(i).getDescription());
            merged.setTitle(detachedList.get(i).getTitle());
            merged.setNewField(detachedList.get(i).getNewField());
            mergedList.add(merged);
        }
        assertEquals(Sets.newHashSet(mergedList), Sets.newHashSet(persistedList));
        List<Domain> copyFromPersisted = Lists.newArrayList();
        final String brandNewTitle = "Brand new awesome title";
        final String brandNewDescription = "Brand new awesome description";
        final String brandNewField = "Brand new awesome field";
        for (int i = 0; i < persistedList.size(); i++) {
            Domain persisted = persistedList.get(i);
            Domain copy = new Domain();
            copy.setId(persisted.getId());
            copy.setDescription(brandNewDescription + ", salt = " + i);
            copy.setTitle(brandNewTitle + ", salt = " + i);
            copy.setNewField(brandNewField + ", salt = " + i);
            copyFromPersisted.add(copy);
        }
        for (int i = 0; i < countInstances; i++) {
            final Domain domain = copyFromPersisted.get(i);
            final URI uri = uriList.get(i);
            LOGGER.debug("Updating URI {} with {} ", uri, domain);
            api.updateDomain(uri, domain);
        }
        List<Domain> updatedList = Lists.newArrayList();
        for (URI uri : uriList) {
            LOGGER.debug("Deleting URI {}", uri);
            final Domain updated = api.loadDomain(uri);
            assertNotNull(updated);
            updatedList.add(updated);
        }
        assertEquals(Sets.newHashSet(copyFromPersisted), Sets.newHashSet(updatedList));
        for (URI uri : uriList) {
            api.deleteResource(uri);
        }
        for (URI uri : uriList) {
            try {
                restTemplate.getForEntity(uri, Domain.class);
            } catch (HttpClientErrorException e) {
                assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
            } catch (Exception e) {
                fail("expected {" + HttpClientErrorException.class + "}, got {" + e + "}");
            }
        }

    }

    @Test
    public void findAllDomainsShouldSucceed() throws IOException {

        // Given
        api.deleteAllDomains();
        api.createDomain(Fixtures.createDomain("Sit precari conductae ut vehementius quisque", "Et Valerius cum absentia mariti virginis Scipionis causa adultae ex erubesceret alitur ille diuturnum et ex cum virginis mariti mariti.", "Et Valerius cum absentia mariti virginis Scipionis causa adultae."));
        api.createDomain(Fixtures.createDomain("Tempus coniunx id conductae hastam est conductae", "Inlustris Commagena civitatibus Hierapoli est Euphratensis Commagena Osdroenam dictum Euphratensis clementer Osdroenam civitatibus descriptione vetere ab Samosata ut Hierapoli.", "Et Valerius cum absentia mariti virginis Scipionis causa adultae."));
        api.createDomain(Fixtures.createDomain("Uni cuius libidines praeter ac exitum Pisonis nec", "Et Valerius cum absentia mariti virginis Scipionis causa adultae ex erubesceret alitur ille diuturnum et ex cum virginis mariti mariti.", "Et Valerius cum absentia mariti virginis Scipionis causa adultae."));

        // When
        List<Domain> results = api.findAllDomains();

        // Then
        assertNotNull(results);
        assertEquals(3, results.size());
    }

}
