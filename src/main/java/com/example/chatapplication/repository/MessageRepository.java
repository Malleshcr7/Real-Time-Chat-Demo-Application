package com.example.chatapplication.repository;

import com.example.chatapplication.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderOrContentContaining(String sender, String content);
    List<Message> findByStatus(Message.MessageStatus status);
} 