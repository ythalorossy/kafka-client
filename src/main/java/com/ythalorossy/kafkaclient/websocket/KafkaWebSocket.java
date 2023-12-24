package com.ythalorossy.kafkaclient.websocket;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class KafkaWebSocket {

    // @MessageMapping("/hello")
    // @SendToUser("/topic/greetings")
    // public Greeting greeting(
    //     @DestinationVariable("username") String username,
    //     HelloMessage message, 
    //     SimpMessageHeaderAccessor headerAccessor) 
    // throws Exception {
    //     Thread.sleep(1000); // simulated delay

    //     String receipt = headerAccessor.getFirstNativeHeader("receipt");

    //     // Use setNativeHeader instead of addNativeHeader
    //     headerAccessor.setNativeHeader("receipt-id", receipt);

    //     return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    // }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(
        HelloMessage message) 
    throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}
