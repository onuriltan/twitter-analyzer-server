package com.onuriltan.twitteranalyzerserver.api.twitterstream.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StreamRequest {

    @NotNull(message = "Command cant be null")
    @NotEmpty(message = "Command cant be empty")
    private String command;

    @NotNull(message = "Command cant be null")
    @NotEmpty(message = "Command cant be empty")
    @Size(min=4, message = "Keyword should be at least 4 characterss")
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
