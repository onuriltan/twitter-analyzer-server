package com.onuriltan.twitteranalyzerserver.config.twitter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.inject.Inject;

@Configuration
public class TwitterStreamConfig {

    @Inject
    TwitterConfig twitterConfig;

    @Inject
    TwitterListenerConfig twitterListenerConfig;

    @Bean
    TwitterStream getTwitterStream(){
        TwitterStreamFactory twitterStreamFactory = new TwitterStreamFactory(twitterConfig.getConfigurationBuilder().build());
        TwitterStream twitterStream = twitterStreamFactory.getInstance();
        twitterStream.addListener(twitterListenerConfig);
        return twitterStream;

    }

}
