package org.diveintojee.poc.rollingupdate.api;

import org.diveintojee.poc.rollingupdate.service.MathsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author louis.gueye@gmail.com
 */
@RestController
@RequestMapping("/api/maths")
public class MathsResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(MathsResource.class);

    @Autowired
    private MathsService service;

    @RequestMapping(value = "/sum/{a}/{b}", method = RequestMethod.GET)
    public Double sum(@PathVariable("a") Double a, @PathVariable("b") Double b) {
        LOGGER.info("Computing sum for {} {}", a, b);
        Double result = this.service.sum(a, b);
        LOGGER.info("Result for sum({}, {}) = {}", a, b, result);
        return result;
    }

}
