package com.onuriltan.twitteranalyzerserver.config.twitter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.inject.Inject;

@Configuration
public class TwitterBeans {

    @Inject
    TwitterConfig twitterConfig;

    @Bean
    public twitter4j.conf.Configuration configuration(){
       return twitterConfig.getConfigurationBuilder().build();
    }

    @Bean
    public TwitterStream twitterStream(){
        TwitterStreamFactory twitterStreamFactory = new TwitterStreamFactory(configuration());
        twitter4j.TwitterStream twitterStream = twitterStreamFactory.getInstance();
        return twitterStream;

    }
    @Bean
    public Twitter twitter(){
        TwitterFactory twitterFactory = new TwitterFactory(configuration());
        Twitter twitter = twitterFactory.getInstance();
        return twitter;

    }

}
