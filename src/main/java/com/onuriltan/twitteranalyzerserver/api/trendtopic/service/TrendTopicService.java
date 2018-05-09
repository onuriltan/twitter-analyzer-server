package com.onuriltan.twitteranalyzerserver.api.trendtopic.service;

import com.onuriltan.twitteranalyzerserver.api.trendtopic.model.TrendTopicResponse;
import org.springframework.stereotype.Service;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TrendTopicService {

    @Inject
    Twitter twitter;

    public TrendTopicResponse getTrendtopics(int areaCode) {
        Trends trends = null;

        TrendTopicResponse trendTopicResponse = new TrendTopicResponse();

        try {
            trends = twitter.getPlaceTrends(areaCode);
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        if (trends.getRateLimitStatus().getLimit() - trends.getRateLimitStatus().getRemaining() <= 2) {
            trendTopicResponse.setStatusCode(401);
            return trendTopicResponse;

        }

        if (trends != null) {
            List<Trend> list = Arrays.asList(trends.getTrends());
            List<String> trendList = new ArrayList<>();
            list.forEach(trend -> trendList.add(trend.getName()));
            trendTopicResponse.setTrendTopics(trendList);
            return trendTopicResponse;
        }

        return null;
    }
}
