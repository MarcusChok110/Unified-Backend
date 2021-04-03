package com.marcuschok.UnifiedBackend.service;

import com.marcuschok.UnifiedBackend.model.Message;
import com.marcuschok.UnifiedBackend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> findAll() {
        return this.messageRepository.findAllByOrderByTimestampAsc();
    }

    public Message save(Message message) {
        return this.messageRepository.save(message);
    }
}
