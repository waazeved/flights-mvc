package br.com.embraer.flights.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "br.com.embraer")
@EntityScan(basePackages = "br.com.embraer")
public class BootApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

	/*@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BootApplication.class);
	}*/
}
