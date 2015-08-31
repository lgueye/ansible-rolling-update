package org.diveintojee.poc.rollingupdate.service;

import org.springframework.stereotype.Component;

/**
 * @author louis.gueye@gmail.com
 */
@Component
public class MathsService {

    public Double sum(Double a, Double b) {
        return a + b;
    }
}
