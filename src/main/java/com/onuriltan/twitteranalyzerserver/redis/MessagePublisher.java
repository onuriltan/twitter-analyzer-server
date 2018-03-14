package com.onuriltan.twitteranalyzerserver.redis;

public interface MessagePublisher {

    void publish(final String message);

}
