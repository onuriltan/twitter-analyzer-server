package com.onuriltan.twitteranalyzerserver.config.redis;

public interface MessagePublisher {

    void publish(final String message);
}
