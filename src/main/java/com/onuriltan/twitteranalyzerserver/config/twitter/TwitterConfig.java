package com.onuriltan.twitteranalyzerserver.config.twitter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import javax.annotation.PostConstruct;

@Configuration
@ConfigurationProperties(prefix="twitter")
public class TwitterConfig {

    private String apiKey;
    private String apiSecret;
    private String accessToken;
    private String accessTokenSecret;
    private twitter4j.conf.Configuration configuration;

    @PostConstruct
    public twitter4j.conf.Configuration buildTwitterConfig(){
        if (apiKey != null && apiSecret != null && accessToken != null && accessTokenSecret != null) {
            ConfigurationBuilder builder = new ConfigurationBuilder();
            builder.setDebugEnabled(true)
                    .setOAuthConsumerKey(apiKey)
                    .setOAuthConsumerSecret(apiSecret)
                    .setOAuthAccessToken(accessToken)
                    .setOAuthAccessTokenSecret(accessTokenSecret);
            builder.setJSONStoreEnabled(true);
            configuration = builder.build();
        }
        return configuration;
    }


    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }

    public void setAccessTokenSecret(String accessTokenSecret) {
        this.accessTokenSecret = accessTokenSecret;
    }

    public twitter4j.conf.Configuration getConfiguration() {
        return configuration;
    }


}
