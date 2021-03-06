package rso.datacatalogue;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableFeignClients
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableSwagger2
@EnableDiscoveryClient
@EnableAsync
@EnableCircuitBreaker
@EnableHystrixDashboard
public class DataCatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataCatalogueApplication.class, args);
	}

	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshEvent(ContextRefreshedEvent contextRefreshedEvent) {
		ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
		MDC.put("applicationName", applicationContext.getId());
	}

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(4);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("DataCatalogueApi");
		executor.initialize();
		return executor;
	}

	@Bean
	public Docket swaggerConfig() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("rso.datacatalogue"))
				.paths(PathSelectors.any())
				.build()
				.securitySchemes(List.of(apiKey()))
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Data catalogue API",
				"This is a private API for League of Legends predictor application",
				"1.0",
				"Students license",
				new Contact("Jakob Maležič", "https://github.com/Blarc", "jm6421@student.uni-lj.si"),
				"API License",
				"https://google.com",
				Collections.emptyList()
		);
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}


}
