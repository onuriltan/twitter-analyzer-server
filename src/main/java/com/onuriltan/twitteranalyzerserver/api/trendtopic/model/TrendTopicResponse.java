package com.onuriltan.twitteranalyzerserver.api.trendtopic.model;

import java.util.List;

public class TrendTopicResponse {

    private int statusCode;

    private List<String> trendTopics;

    public List<String> getTrendTopics() {
        return trendTopics;
    }

    public void setTrendTopics(List<String> trendTopics) {
        this.trendTopics = trendTopics;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
