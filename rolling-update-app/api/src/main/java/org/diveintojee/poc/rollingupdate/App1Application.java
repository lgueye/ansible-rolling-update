package org.diveintojee.poc.rollingupdate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@ComponentScan(basePackages = "org.diveintojee.poc.digitaloceancluster")
@EnableJpaRepositories(basePackages = "org.diveintojee.poc.digitaloceancluster.app1.persistence.data")
@EnableElasticsearchRepositories(basePackages = "org.diveintojee.poc.digitaloceancluster.app1.persistence.index")
public class App1Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(App1Application.class, args);
	}

	@Override
	protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(App1Application.class);
	}
}
