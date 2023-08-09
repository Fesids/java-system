package com.registration.registration.services;

import com.registration.registration.DTO.ChatMessageDTO;
import com.registration.registration.models.ChatMessage;

import java.util.List;

public interface ChatMessageService {

    ChatMessage createMessage(ChatMessageDTO chatMessageDTO);

    List<ChatMessage> getChatMessagesChat(Long receiverId, Long senderId);

    List<ChatMessage> getSentMessages(Long sent, Long receiver);

    List<ChatMessage> getReceivedMessages(Long receiver, Long sent);
}
