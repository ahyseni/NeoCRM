package com.mrkt.admin;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiMasterdata() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Masterdata")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mrkt.admin.web.api.v1.masterdata"))
                .paths(apiV1Paths())
                .build();
    }

    @Bean
    public Docket apiAccount() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Account")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mrkt.admin.web.api.v1.account"))
                .paths(apiV1Paths())
                .build();
    }

    @Bean
    public Docket apiOrder() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Order")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mrkt.admin.web.api.v1.order"))
                .paths(apiV1Paths())
                .build();
    }

    @Bean
    public Docket apiIssue() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Issue")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mrkt.admin.web.api.v1.issue"))
                .paths(apiV1Paths())
                .build();
    }

    @Bean
    public Docket apiEmployee() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Employee")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mrkt.admin.web.api.v1.employee"))
                .paths(apiV1Paths())
                .build();
    }

    @Bean
    public Docket apiClient() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Client")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mrkt.admin.web.api.v1.client"))
                .paths(apiV1Paths())
                .build();
    }

    @Bean
    public Docket apiAvertise() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Adds")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mrkt.admin.web.api.v1.adds"))
                .paths(apiV1Paths())
                .build();
    }

    private Predicate apiV1Paths() {
        return  or(regex("/v1.*"));
    }

}
