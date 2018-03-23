package com.onuriltan.twitteranalyzerserver.config.twitter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.inject.Inject;

@Configuration
public class TwitterBeans {

    @Inject
    TwitterConfig twitterConfig;

    @Bean
    public TwitterStream twitterStream(){
        TwitterStreamFactory twitterStreamFactory = new TwitterStreamFactory(twitterConfig.getConfigurationBuilder().build());
        twitter4j.TwitterStream twitterStream = twitterStreamFactory.getInstance();
        return twitterStream;

    }

}
