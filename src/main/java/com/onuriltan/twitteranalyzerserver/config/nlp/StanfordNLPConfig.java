package com.onuriltan.twitteranalyzerserver.config.nlp;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class StanfordNLPConfig {

    @Bean
    public StanfordCoreNLP stanfordCoreNLP(){
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        return  pipeline;
    }
}
