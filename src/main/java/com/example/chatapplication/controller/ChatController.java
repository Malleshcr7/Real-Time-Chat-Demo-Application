package com.example.chatapplication.controller;

import com.example.chatapplication.model.ChatMessage;
import com.example.chatapplication.model.Message;
import com.example.chatapplication.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ChatController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        // Save message to database
        Message message = new Message();
        message.setSender(chatMessage.getSender());
        message.setContent(chatMessage.getContent());
        message.setType(Message.MessageType.CHAT);
        message.setTimestamp(LocalDateTime.now());
        message.setStatus(Message.MessageStatus.SENT);
        messageRepository.save(message);

        // Notify sender about message status
        messagingTemplate.convertAndSendToUser(
            chatMessage.getSender(),
            "/queue/status",
            "Message sent successfully"
        );

        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, 
                             SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/chat.markAsRead")
    public void markMessageAsRead(@Payload Long messageId) {
        Message message = messageRepository.findById(messageId).orElse(null);
        if (message != null) {
            message.setStatus(Message.MessageStatus.READ);
            messageRepository.save(message);
            
            // Notify sender about message being read
            messagingTemplate.convertAndSendToUser(
                message.getSender(),
                "/queue/status",
                "Message has been read"
            );
        }
    }
} 