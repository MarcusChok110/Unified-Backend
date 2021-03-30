package com.marcuschok.UnifiedBackend.controller;

import com.marcuschok.UnifiedBackend.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/messages")
    @SendTo("/topic")
    public boolean socketResponse(Message message) {
        return true;
    }
}
