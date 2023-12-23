package com.ythalorossy.kafkaclient.configurations;

public class Producer {

    private String[] bootstrapServers;

    public void setBootstrapServers(String[] bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public String getBootstrapServersAsString() {
        return String.join(",", this.bootstrapServers);
    }

}