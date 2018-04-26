package com.onuriltan.twitteranalyzerserver.api.twitterstream.model;

public class StreamRequest {

    private String command;
    private String message;


    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "StreamRequest{" +
                "command='" + command + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
