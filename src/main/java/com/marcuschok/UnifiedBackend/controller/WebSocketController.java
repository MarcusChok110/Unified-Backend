package com.marcuschok.UnifiedBackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcuschok.UnifiedBackend.model.Message;
import com.marcuschok.UnifiedBackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class WebSocketController {

    private MessageService messageService;
    private ObjectMapper objectMapper;

    @Autowired
    public WebSocketController(MessageService messageService, ObjectMapper objectMapper) {
        this.messageService = messageService;
        this.objectMapper = objectMapper;
    }

    @MessageMapping("/add")
    @SendTo("/topic/responses")
    public Message addResponse(String messageJson) throws Exception {
        Message message = this.objectMapper.readValue(messageJson, Message.class);
        return this.messageService.save(message);
    }

    @MessageMapping("/delete")
    @SendTo("/topic/responses")
    public Long deleteResponse(String strId) {
        Long id = Long.parseLong(strId);
        Optional<Message> messageOptional = this.messageService.findById(id);
        if (messageOptional.isEmpty()) {
            throw new IllegalStateException("Message not found");
        }
        this.messageService.delete(messageOptional.get());
        return id;
    }
}
