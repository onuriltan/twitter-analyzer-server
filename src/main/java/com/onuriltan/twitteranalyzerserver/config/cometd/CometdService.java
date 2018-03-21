package com.onuriltan.twitteranalyzerserver.config.cometd;

import org.cometd.annotation.Configure;
import org.cometd.annotation.Session;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ConfigurableServerChannel;
import org.cometd.bayeux.server.ServerChannel;
import org.cometd.bayeux.server.ServerSession;
import org.cometd.server.authorizer.GrantAuthorizer;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
@org.cometd.annotation.Service("twitter")
public class CometdService {

    @Inject
    BayeuxServer bayeuxServer;
    @Session
    ServerSession serverSession;

    @Configure({"/twitter/getStream"})
    protected void configureTwitterSamples(ConfigurableServerChannel channel) {
        channel.addAuthorizer(GrantAuthorizer.GRANT_SUBSCRIBE);
    }

    public void publishMessage(String msg) {
        ServerChannel channel = bayeuxServer.getChannel("/twitter/getStream");
        channel.publish(serverSession, msg);
    }
}