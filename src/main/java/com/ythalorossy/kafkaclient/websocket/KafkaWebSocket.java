package com.ythalorossy.kafkaclient.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class KafkaWebSocket {

    @MessageMapping("/hello")
    @SendToUser("/topic/greetings")
    public Greeting greeting(
        @Payload HelloMessage message, 
        SimpMessageHeaderAccessor headerAccessor) 
    throws Exception {

        String receipt = headerAccessor.getFirstNativeHeader("receipt");

        // Use setNativeHeader instead of addNativeHeader
        headerAccessor.setNativeHeader("receipt-id", receipt);

        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}
