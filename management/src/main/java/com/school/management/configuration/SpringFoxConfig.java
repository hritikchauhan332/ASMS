package com.school.management.configuration;

import com.school.management.utils.Constants.SpringFoxConfigConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

import static com.school.management.utils.Constants.SpringFoxConfigConstants.*;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SpringFoxConfig  implements WebMvcConfigurer {
    @Bean
    public Docket api() {
        Set<String> responseProduceType = new HashSet<String>();
        responseProduceType.add("application/json");
        responseProduceType.add("application/xml");
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any()).build()
                .useDefaultResponseMessages(false)
                .genericModelSubstitutes(ResponseEntity.class)
                .produces(responseProduceType)
                .consumes(responseProduceType)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        @SuppressWarnings("deprecation")
        ApiInfo apiInfo = new ApiInfo(
                TITLE,
                DESCRIPTION,
                VERSION,
                TERMS_OF_SERVICE_URL,
                CONTACT_NAME,
                LICENSE,
                LICENSE_URL);
        return apiInfo;
    }

    private Object apiKey() {
        return null;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}