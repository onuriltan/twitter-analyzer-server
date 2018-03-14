package com.onuriltan.twitteranalyzerserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Configuration
public class TwitterStreamConfig {

    @Autowired
    TwitterConfig twitterConfig;
    @Autowired
    TwitterListenerConfig twitterListenerConfig;

    @Bean
    TwitterStream twitterStream(){
        TwitterStreamFactory twitterStreamFactory = new TwitterStreamFactory(twitterConfig.getConfigurationBuilder().build());
        TwitterStream twitterStream = twitterStreamFactory.getInstance();
        twitterStream.addListener(twitterListenerConfig);
        return twitterStream;

    }

}
