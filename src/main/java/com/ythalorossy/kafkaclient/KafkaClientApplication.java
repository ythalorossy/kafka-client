package com.ythalorossy.kafkaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import com.ythalorossy.kafkaclient.websocket.Greeting;

@SpringBootApplication
public class KafkaClientApplication {

	private SimpMessagingTemplate template;

	public KafkaClientApplication(SimpMessagingTemplate template) {
		this.template = template;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaClientApplication.class, args);
	}

	// @KafkaListener(id = "myId", topics = "quickstart-events")
	// public void listen(String in) {
	// 	System.out.println(in);

	// 	Greeting greeting = new Greeting(in);

	// 	template.convertAndSend("/topic/kafka", greeting);
	// }
}