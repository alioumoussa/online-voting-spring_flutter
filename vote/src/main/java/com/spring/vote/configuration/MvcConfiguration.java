package com.spring.vote.configuration;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

public class MvcConfiguration extends WebMvcConfigurationSupport {
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
    //     @Override
    // public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {
    //     viewControllerRegistry.addViewController("/").setViewName("redirect:/pages/dashboard");
    // }

}
