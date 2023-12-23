package com.ythalorossy.kafkaclient.configurations;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.kafka")
public class KafkaConfigurationProperties {

    private KafkaProperties.Producer producer;
    private KafkaProperties.Consumer consumer;

    public KafkaProperties.Producer getProducer() {
        return producer;
    }

    public void setProducer(KafkaProperties.Producer producer) {
        this.producer = producer;
    }

    public KafkaProperties.Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(KafkaProperties.Consumer consumer) {
        this.consumer = consumer;
    }

}