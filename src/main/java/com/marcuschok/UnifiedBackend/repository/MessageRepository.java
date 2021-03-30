package com.marcuschok.UnifiedBackend.repository;

import com.marcuschok.UnifiedBackend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
