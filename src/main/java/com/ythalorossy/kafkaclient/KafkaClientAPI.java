package com.ythalorossy.kafkaclient;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = "kafka")
public class KafkaClientAPI {

    private final KafkaTemplate<String, YCustomMessage> kafkaTemplate;

    public KafkaClientAPI(KafkaTemplate<String, YCustomMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/publish")
    public YCustomMessage publishCustomMessage(@RequestBody YCustomMessage cm) {

        System.out.println(cm);

        YCustomMessage data = new YCustomMessage(cm.getHeader(), cm.getMessage(), cm.getComments());
        kafkaTemplate.send("quickstart-events", data);

        return cm;
    }

    @GetMapping(value = "/check")
    public YCustomMessage checkStatus() {
        return new YCustomMessage("Status", "UP", "Running as expected at " + LocalDateTime.now());
    }

}
