package com.onuriltan.twitteranalyzerserver.config.spring.mvc;

import com.onuriltan.twitteranalyzerserver.config.AllowedOriginsConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.inject.Inject;

   /*@Configuration
public class MvcConfig implements WebMvcConfigurer {

 @Inject
    AllowedOriginsConfig allowedOriginsConfig;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*").allowedOrigins(allowedOriginsConfig.getUrl());
    }
}*/

