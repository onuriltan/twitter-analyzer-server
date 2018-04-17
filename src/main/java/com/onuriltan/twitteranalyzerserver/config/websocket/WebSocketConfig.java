package com.onuriltan.twitteranalyzerserver.config.websocket;

import com.onuriltan.twitteranalyzerserver.config.AllowedOriginsConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

import javax.inject.Inject;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Inject
    AllowedOriginsConfig allowedOriginsConfig;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/twitterStream").setAllowedOrigins(allowedOriginsConfig.getUrl()).withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

}



