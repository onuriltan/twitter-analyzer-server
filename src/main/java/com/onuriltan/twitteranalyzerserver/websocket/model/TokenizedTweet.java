package com.onuriltan.twitteranalyzerserver.websocket.model;

public class TokenizedTweet {

    private String word;
    private String namedEntity;


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getNamedEntity() {
        return namedEntity;
    }

    public void setNamedEntity(String namedEntity) {
        this.namedEntity = namedEntity;
    }
}
