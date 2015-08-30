package org.diveintojee.poc.rollingupdate.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.diveintojee.poc.rollingupdate.domain.Domain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author louis.gueye@gmail.com
 */
@Component
@Validated
public class DomainService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DomainService.class);

    private Map<Long, Domain> repository = Maps.newHashMap();

    private AtomicLong sequence = new AtomicLong();

    public Long save(@NotNull @Valid final Domain domain) {
        final Long id = sequence.addAndGet(1);
        domain.setId(id);
        Domain persisted = repository.put(id, domain);
        LOGGER.info("Saved domain in db : {}", persisted);
        return id;
    }

    public List<Domain> findAll() {
        return Lists.newArrayList(repository.values());
    }

    public Domain getOne(Long id) {
        return repository.get(id);
    }

    public void update(Long id, @NotNull @Valid final Domain domain) {
        repository.put(id, domain);
        LOGGER.info("Updated domain in db : {}", domain);
    }

    public void delete(Long id) {
        final Domain domain = repository.get(id);
        repository.remove(domain);
        LOGGER.info("Deleted domain from db : {}", id);
    }

    public void delete() {
        repository.clear();
        LOGGER.info("Deleted all domains from db");
    }
}
