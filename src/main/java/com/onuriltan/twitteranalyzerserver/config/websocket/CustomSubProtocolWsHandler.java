package com.onuriltan.twitteranalyzerserver.config.websocket;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.SubProtocolWebSocketHandler;

import javax.inject.Inject;

public class CustomSubProtocolWsHandler extends SubProtocolWebSocketHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomSubProtocolWsHandler.class);

    @Inject
    private WebSocketSessionHandler sessionHandler;

    public CustomSubProtocolWsHandler(MessageChannel clientInboundChannel, SubscribableChannel clientOutboundChannel) {
        super(clientInboundChannel, clientOutboundChannel);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOGGER.info("Session with id "+session.getId()+ " is established.");
        sessionHandler.register(session);
        super.afterConnectionEstablished(session);
    }
}