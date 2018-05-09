package com.onuriltan.twitteranalyzerserver.base;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TwitterRateLimitHandler {

    private Date whenRateLimitOccured;


    public Date getWhenRateLimitOccured() {
        return whenRateLimitOccured;
    }

    public void setWhenRateLimitOccured(Date whenRateLimitOccured) {
        this.whenRateLimitOccured = whenRateLimitOccured;
    }
}
