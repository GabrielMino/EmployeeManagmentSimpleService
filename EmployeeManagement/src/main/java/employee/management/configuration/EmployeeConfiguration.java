package employee.management.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


//Json file: http://localhost:8080/v2/api-docs
//SwaggerUI file: http://localhost:8080/swagger-ui/

@Configuration
public class EmployeeConfiguration {
	
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.any())
	                .paths(PathSelectors.any())
	                .build();
    }	

}