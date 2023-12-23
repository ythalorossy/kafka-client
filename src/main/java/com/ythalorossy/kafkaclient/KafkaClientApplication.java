package com.ythalorossy.kafkaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class KafkaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaClientApplication.class, args);
	}


	@KafkaListener(id = "myId", topics = "quickstart-events")
	public void listen(String in) {
		System.out.println(in);
	}
}
