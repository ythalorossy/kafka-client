package com.ythalorossy.kafkaclient;

public class YCustomMessage {

    private String header;
    private String message;
    private String comments;

    public YCustomMessage() {
    }

    public YCustomMessage(String header, String message, String comments) {
        this.header = header;
        this.message = message;
        this.comments = comments;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "CustomMessage [header=" + header + ", message=" + message + ", comments=" + comments + "]";
    }

}
