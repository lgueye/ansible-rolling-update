package org.diveintojee.poc.rollingupdate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * @author louis.gueye@gmail.com
 */
@Configuration
@ComponentScan
@PropertySource(value = "classpath:rolling-update-app-tests.properties")
public class RollingUpdateTestConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    RestOperations restOperation() {
        return new RestTemplate();
    }
}
