package com.fx.trade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

import java.util.Collections;

@Configuration
public class SpringFoxConfig {

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "FXTrade validation coding task",
                "Task description:\n" +
                        "There is a demand to extend the STP flow with trade validation service for the FOREX transactions – FX Spot, Forward, Options. In addition there should be a small client provided Validation results shall be displayed in the console, logs or displayed in the GUI with the information about the failure.\n\n" +
                        "Technical requirements:\n" +
                        "1. The service shall expose a REST interface consuming trades in JSON format and returning validation result to the client\n" +
                        "2. Service shall be flexible to extend the validation logic in terms of:\n" +
                        "- new business validation rules\n" +
                        "- new products to be supported\n" +
                        "3. Service should support graceful shutdown\n\n" +
                        "Business requirements:\n" +
                        "1. The following basic validation rules shall be implemented:\n" +
                        "All types of trades:\n" +
                        "- value date cannot be before trade date\n" +
                        "- value date cannot fall on weekend or non-working day for currency\n" +
                        "- if the counterparty is one of the supported ones\n" +
                        "- validate currencies if they are valid ISO codes (ISO 4217)\n" +
                        "Spot, Forward transactions:\n" +
                        "- validate the value date against the product type\n" +
                        "Options specific:\n" +
                        "- the style can be either American or European\n" +
                        "- American option style will have in addition the excerciseStartDate, which has to be after the trade date but before the expiry date\n" +
                        "- expiry date and premium date shall be before delivery date\n\n" +
                        "2. The validation response should include information about errors detected in the trade (in case multiple are detected, all of them should be returned) and in case of bulk validation additional linkage between the error and the actual trade should be returned",
                "API TOS",
                "Terms of service",
                new Contact("Siarhei Turski", "www.epam.com", "siarhei_turski@epam.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * SwaggerUI information
     */

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }

}
