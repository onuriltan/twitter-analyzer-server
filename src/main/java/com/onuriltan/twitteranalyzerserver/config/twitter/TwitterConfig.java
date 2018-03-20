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
    private ConfigurationBuilder configurationBuilder;

    @PostConstruct
    public ConfigurationBuilder buildTwitterConfig(){
        if (apiKey != null && apiSecret != null && accessToken != null && accessTokenSecret != null) {
            configurationBuilder = new ConfigurationBuilder();
            configurationBuilder.setDebugEnabled(true)
                    .setOAuthConsumerKey(apiKey)
                    .setOAuthConsumerSecret(apiSecret)
                    .setOAuthAccessToken(accessToken)
                    .setOAuthAccessTokenSecret(accessTokenSecret);
            configurationBuilder.setJSONStoreEnabled(true);
        }
        return configurationBuilder;
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

    public ConfigurationBuilder getConfigurationBuilder() {
        return configurationBuilder;
    }
}
