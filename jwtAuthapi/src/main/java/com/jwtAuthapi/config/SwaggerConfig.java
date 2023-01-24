package com.jwtAuthapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {
	


	private ApiInfo getInfo() {
		
		
		return new ApiInfo("Employee Managment database API's with authentication", "This is a back-end employee database management project where each api is authenticated using spring security.", "1.0", null, new Contact("Rajkumar Dhumal", "https://github.com/rajkumarDhumal","rajkumardhumal02@gmail.com"), null, null, null);
	}
	
	 public static final String AUTHORIZATION_HEADER = "Authorization";

	    private ApiKey apiKey(){
	        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	    }

	    private ApiInfo apiInfo(){
	        return new ApiInfo(
	                "Spring Boot REST APIs with Authentication",
	                "This is a Spring Boot REST APIs project where each api is authenticated using spring security.",
	                "1.0",
	                "Terms of service",
	                new Contact("Rajkumar Dhumal", "https://github.com/rajkumarDhumal","rajkumardhumal02@gmail.com"),
	                "License of API",
	                "API license URL",
	                Collections.emptyList()
	        );
	    }
	    
	    @Bean
	    public Docket api(){
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .securityContexts(securityContexts())
	                .securitySchemes(Arrays.asList(apiKey()))
	                .select()
	                .apis(RequestHandlerSelectors.any())
	                .paths(PathSelectors.any())
	                .build();
	    }

	    
	    private List<SecurityContext> securityContexts() {
			return Arrays.asList(SecurityContext.builder().securityReferences(SecurityReference()).build());
			
		}
	    
	    private List<SecurityReference> SecurityReference(){
	    	
	    	AuthorizationScope scope=new AuthorizationScope("global", "accessEverything");
			return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scope}));
	    	
	    }
	
}
