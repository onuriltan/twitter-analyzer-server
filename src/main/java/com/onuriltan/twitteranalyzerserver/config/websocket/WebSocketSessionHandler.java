package com.onuriltan.twitteranalyzerserver.config.websocket;

import com.onuriltan.twitteranalyzerserver.base.BaseTwitterStream;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;

@Component
@ApplicationScope
public class WebSocketSessionHandler {

    private HashMap<String, BaseTwitterStream> sessions = new HashMap<>();
    private HashMap<String, WebSocketSession> webSocketSessions = new HashMap<>();


    public HashMap<String, BaseTwitterStream> getSessions() {
        return sessions;
    }

    public HashMap<String, WebSocketSession> getWebSocketSessions() {
        return webSocketSessions;
    }


    public void register(WebSocketSession session) {
        webSocketSessions.put(session.getId(), session);
    }
}
