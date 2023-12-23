package com.ythalorossy.kafkaclient.serializations;

import org.apache.kafka.common.serialization.Serializer;

import com.ythalorossy.kafkaclient.YCustomMessage;

public class YCustomMessageSerializer implements Serializer<YCustomMessage> {

    @Override
    public byte[] serialize(String topic, YCustomMessage data) {
        return data.toString().getBytes();
    }
}