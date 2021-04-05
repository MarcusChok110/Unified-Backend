package com.marcuschok.UnifiedBackend.controller;

import com.marcuschok.UnifiedBackend.model.Message;
import com.marcuschok.UnifiedBackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> getMessages() {
        return this.messageService.findAll();
    }

    @GetMapping("/{id}")
    public Message getMessage(@PathVariable Long id) {
        Optional<Message> messageOptional = this.messageService.findById(id);

        if (messageOptional.isEmpty()) {
            throw new IllegalStateException("Message not found");
        }

        return messageOptional.get();
    }

    @PostMapping
    public Message createMessage(@RequestBody Message message) {
        return this.messageService.save(message);
    }

    @PutMapping("/{id}")
    public Message editMessage(@RequestBody Message message, @PathVariable Long id) {
        Optional<Message> messageOptional = this.messageService.findById(id);

        if (messageOptional.isEmpty()) {
            throw new IllegalStateException("Message not found");
        }
        message.setId(id);

        return this.messageService.save(message);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        Optional<Message> messageOptional = this.messageService.findById(id);
        if (messageOptional.isEmpty()) {
            throw new IllegalStateException("Message not found");
        }
        this.messageService.delete(messageOptional.get());
    }
}
