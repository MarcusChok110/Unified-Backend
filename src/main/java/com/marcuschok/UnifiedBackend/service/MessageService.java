package com.marcuschok.UnifiedBackend.service;

import com.marcuschok.UnifiedBackend.model.Message;
import com.marcuschok.UnifiedBackend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Message> findById(Long id) {
        return this.messageRepository.findById(id);
    }

    public Message save(Message message) {
        return this.messageRepository.save(message);
    }

    public void delete(Message message) {
        this.messageRepository.delete(message);
    }
}
