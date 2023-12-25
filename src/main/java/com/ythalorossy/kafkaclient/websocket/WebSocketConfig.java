package com.ythalorossy.kafkaclient.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    // config.enableSimpleBroker("/topic");
    config
      .enableStompBrokerRelay("/topic")
      .setRelayHost("172.18.23.194")
      .setRelayPort(61613)
      .setClientLogin("guest")
      .setClientPasscode("guest");
    config
      .setApplicationDestinationPrefixes("/app")
      // .setUserDestinationPrefix("/user")
      ;
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/kafka-websocket");
  }

}
