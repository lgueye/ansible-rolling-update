package org.diveintojee.poc.rollingupdate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RollingUpdateTestConfig.class})
public class DomainResourceTestIT {

    @Autowired
    private RollingUpdateAppClient api;

    @Test
    public void sumShouldSucceed() throws IOException {
        Double actual  = api.sum(1d, 2d);
        assertEquals(Double.valueOf(3), actual);
    }
}
