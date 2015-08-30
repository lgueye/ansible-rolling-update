package org.diveintojee.poc.rollingupdate.api;

import org.diveintojee.poc.rollingupdate.domain.Domain;
import org.diveintojee.poc.rollingupdate.service.DomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author louis.gueye@gmail.com
 */
@RestController
@RequestMapping("/api/domains")
public class DomainsResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DomainsResource.class);

    @Autowired
    private DomainService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody @Valid Domain domain) {
        LOGGER.info("Attempt to create domain: {}", domain);
        // Save domain and get new id
        Long id = this.service.save(domain);
        LOGGER.info("Saved domain with id: {}", id);

        // Build URI
        final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/domains/{id}").build().expand(id).toUri();
        LOGGER.info("New resource can be found at : {}", location.toString());

        // Add uri location
        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        // Add header to response
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Domain> findAll() {
        LOGGER.info("Searching all domains");
        return this.service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Domain get(@PathVariable("id") Long id) {
        Domain domain = this.service.getOne(id);
        if (domain == null) {
            final String message = "Domain with id {" + id + "} was not found";
            LOGGER.info(message);
            throw new ResourceNotFoundException(message);
        }
        LOGGER.info("Found domain with id: {}", id);
        return domain;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long id, @RequestBody @Valid Domain domain) {
        service.update(id, domain);
        LOGGER.info("Updated domain with id: {}", id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        this.service.delete(id);
        LOGGER.info("Deleted domain with id: {}", id);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Domain not found")
    public void notFound() {
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete() {
        this.service.delete();
        LOGGER.info("Deleted all domains");
    }
}
