package com.onuriltan.twitteranalyzerserver.websocket.model;

public class Request {

    private Command command;
    private String message;

    public enum Command {
        START,
        STOP
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
