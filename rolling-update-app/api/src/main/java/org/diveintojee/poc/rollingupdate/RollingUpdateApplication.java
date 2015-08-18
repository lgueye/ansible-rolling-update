package org.diveintojee.poc.rollingupdate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = "org.diveintojee.poc.rollingupdate")
public class RollingUpdateApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RollingUpdateApplication.class, args);
	}

	@Override
	protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(RollingUpdateApplication.class);
	}
}
