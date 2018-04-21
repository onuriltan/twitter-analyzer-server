package com.onuriltan.twitteranalyzerserver.config.spring;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onuriltan.twitteranalyzerserver.config.AllowedOriginsConfig;
import com.onuriltan.twitteranalyzerserver.websocket.model.Tweet;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.filter.CommonsRequestLoggingFilter;


import javax.inject.Inject;
import java.util.Stack;

@Configuration
public class SpringFrameworkBeans {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here

        return builder.build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);

        return mapper;
    }

}
