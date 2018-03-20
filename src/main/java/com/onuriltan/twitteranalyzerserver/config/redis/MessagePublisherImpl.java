package com.onuriltan.twitteranalyzerserver.config.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class MessagePublisherImpl implements MessagePublisher {

    @Inject
    private RedisTemplate<String, Object> redisTemplate;

    @Inject
    private ChannelTopic topic;

    public MessagePublisherImpl() {
    }

    public MessagePublisherImpl(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    public void publish(final String message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
