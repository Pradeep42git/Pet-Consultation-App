package com.onlinepetconsultation.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public OpenAPI usersMicroserviceOpenAPI() {

		Server localhost = new Server();
		localhost.setUrl("http://localhost:8080");
		localhost.setDescription("Development environment");

		Contact contact = new Contact();
		contact.setEmail("info@petconsultationapp.in");
		contact.setName("petconsultationapp");
		contact.setUrl("https://foodapp.in");

		License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

		Info info = new Info().title("petconsultationapp RESTful Web Service documentation").version("1.0").contact(contact)
				.description("This API exposes endpoints to manage petconsultationapp.")
				.termsOfService("https://foodapp/terms").license(mitLicense);

		return new OpenAPI().info(info).servers(List.of(localhost));
	}

	
}
