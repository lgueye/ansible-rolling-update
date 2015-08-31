package org.diveintojee.poc.rollingupdate;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RollingUpdateApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:8080")
public class MathsResourceTest {

    @Test
    public void sumShouldSucceed() {
        Double a = 2d;
        Double b = 5d;
        Double expected = 7d;
        when().
                get("/api/maths/sum/{a}/{b}", a, b).
                then().
                statusCode(HttpStatus.SC_OK).
                body(Matchers.equalTo(expected.toString()));
    }
}
