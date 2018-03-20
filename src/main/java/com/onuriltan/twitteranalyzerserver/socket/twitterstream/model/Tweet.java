package com.onuriltan.twitteranalyzerserver.socket.twitterstream.model;

public class Tweet {

    private String body;
    private String language;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
