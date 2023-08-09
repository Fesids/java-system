package com.registration.registration.services;

import com.registration.registration.DTO.ChatMessageDTO;
import com.registration.registration.models.ChatMessage;
import com.registration.registration.repositories.ChatMessageRepository;
import com.registration.registration.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
public class ChatMessageServiceImpl implements ChatMessageService{

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public ChatMessage createMessage(ChatMessageDTO chatMessageDTO) {
        var receiver = profileRepository.findById(chatMessageDTO.msg_receiver()).get();
        var sender = profileRepository.findById(chatMessageDTO.msg_sender()).get();
        var new_msg_body = ChatMessage.builder().msg_receiver(receiver)
                .msg_sender(sender).body(chatMessageDTO.body())
                .createdAt(LocalDateTime.now()).build();
        var new_msg = chatMessageRepository.save(new_msg_body);

        return new_msg;
    }

    @Override
    public List<ChatMessage> getChatMessagesChat(Long receiverId, Long senderId) {
        try{
            var messages = chatMessageRepository.findMessagesBySenderAndReceiver(senderId, receiverId);
            return messages;
        }catch (Exception e){
            throw new RuntimeException("Failed to retrieve messages");
        }
    }

    @Override
    public List<ChatMessage> getSentMessages(Long sent, Long receiver) {
        try{
            var resp = chatMessageRepository.findSentMessages(sent, receiver);
            return resp;
        }catch (Exception e){
            throw new RuntimeException("Failed to retrieve message");
        }
    }

    @Override
    public List<ChatMessage> getReceivedMessages(Long receiver, Long sent) {
        try{
            var resp = chatMessageRepository.findReceivedMessages(sent, receiver);
            return resp;
        }catch (Exception e){
            throw new RuntimeException("Failed to retrieve messages");
        }
    }
}
