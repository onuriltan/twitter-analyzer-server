package com.onuriltan.twitteranalyzerserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
@ConfigurationProperties("app")
public class TwitterConfig {

    @Value("${twitter.api_key}")
    private String consumerKey;
    @Value("${twitter.api_secret}")
    private String consumerSecret;
    @Value("${twitter.access_token}")
    private String accessToken;
    @Value("${twitter.access_token_secret}")
    private String accessTokenSecret;


    @Bean
    public ConfigurationBuilder twitterConfiguration(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        cb.setJSONStoreEnabled(true);
        return cb;
    }

}
