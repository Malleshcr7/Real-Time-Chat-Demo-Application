package com.example.chatapplication.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String sender;
    private String content;
    private LocalDateTime timestamp;
    
    @Enumerated(EnumType.STRING)
    private MessageStatus status;
    
    private String mediaUrl;
    
    @Enumerated(EnumType.STRING)
    private MessageType type;
    
    public enum MessageStatus {
        SENT,
        DELIVERED,
        READ
    }
    
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
} 