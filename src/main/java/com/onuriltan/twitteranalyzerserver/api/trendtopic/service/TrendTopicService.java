package com.onuriltan.twitteranalyzerserver.api.trendtopic.service;

import com.onuriltan.twitteranalyzerserver.api.trendtopic.model.TrendTopicModel;
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

    public TrendTopicModel getTrendtopics(){
        Trends trends = null;
        try {
            trends = twitter.getPlaceTrends(1);
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        if (trends != null) {
            List<Trend> list= Arrays.asList(trends.getTrends());
            List<String> trendList = new ArrayList<>();
            list.forEach(trend ->trendList.add(trend.getName()));
            TrendTopicModel trendTopicModel = new TrendTopicModel();
            trendTopicModel.setTrendTopics(trendList);
            return trendTopicModel;
        }

        return null;
    }
}
