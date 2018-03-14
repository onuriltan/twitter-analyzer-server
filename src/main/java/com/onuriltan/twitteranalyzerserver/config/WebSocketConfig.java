package com.onuriltan.twitteranalyzerserver.config;

import com.onuriltan.twitteranalyzerserver.socket.TweetSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Bean
    public WebSocketHandler tweetSocketHandler() {
        return new TweetSocketHandler();
    }
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(tweetSocketHandler(), "/twitter/getStream");
    }
}
