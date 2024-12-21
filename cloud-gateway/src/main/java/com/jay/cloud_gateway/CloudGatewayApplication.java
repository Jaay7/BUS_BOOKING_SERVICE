package com.jay.cloud_gateway;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@EnableDiscoveryClient
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Gateway", version = "1.0", description = "Documentation API Gateway v1.0"))
public class CloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route(r -> r.path("/USER-SERVICE/v3/api-docs").and().method(HttpMethod.GET).uri("lb://USER-SERVICE"))
				.route(r -> r.path("/PASSENGER-SERVICE/v3/api-docs").and().method(HttpMethod.GET).uri("lb://USER-SERVICE"))
				.route(r -> r.path("/STAFF-SERVICE/v3/api-docs").and().method(HttpMethod.GET).uri("lb://STAFF-SERVICE"))
				.route(r -> r.path("/BUS-SERVICE/v3/api-docs").and().method(HttpMethod.GET).uri("lb://BUS-SERVICE"))
				.build();
	}
}
