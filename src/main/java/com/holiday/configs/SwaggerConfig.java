package com.holiday.configs;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot"))).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Public Holiday API")
                .description("Public Holiday API description: \n" +
                        "a) isHolidayForCountry: Is the given date a holiday in the given country \n" +
                        "b) numberOfHolidays: Total amount of holidays in the given year in the given country")
                .version("1.0").build();
    }

}
