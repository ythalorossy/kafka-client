package com.ythalorossy.kafkaclient.serializations;

import java.util.Objects;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.util.SerializationUtils;

import com.ythalorossy.kafkaclient.YCustomMessage;

public class YCustomMessageDeserializer implements Deserializer<YCustomMessage> {

    @Override
    public YCustomMessage deserialize(String topic, byte[] data) {
        return (Objects.nonNull(data)) ? (YCustomMessage) SerializationUtils.deserialize(data) : null;
    }

}