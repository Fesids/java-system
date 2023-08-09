package com.registration.registration.controller;

import com.registration.registration.DTO.ChatMessageDTO;
import com.registration.registration.UTILS.GeralUtilities;
import com.registration.registration.models.ChatMessage;
import com.registration.registration.services.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
@CrossOrigin("*")
public class ChatMessageController {

    @Autowired
    private ChatMessageService chatMessageService;

    @PostMapping("/new")
    public ResponseEntity<ChatMessage> new_message(@RequestBody ChatMessageDTO chatMessagebody){

        var msg_resp = chatMessageService.createMessage(chatMessagebody);

        return ResponseEntity.created(GeralUtilities.toURI("/new")).body(msg_resp);

    }

    @GetMapping("/list/{senderId}/{receiverId}")
    public ResponseEntity<List<ChatMessage>> getMessagesChat(@PathVariable("senderId") Long senderId, @PathVariable("receiverId") Long receiverId){
        var messages = chatMessageService.getChatMessagesChat(receiverId, senderId);

        return ResponseEntity.ok().body(messages);
    }




    @GetMapping("/list/sent/{sentId}/receiver/{receiverId}")
    public ResponseEntity<List<ChatMessage>> getSentMessages(@PathVariable("sentId") Long sentId, @PathVariable("receiverId") Long receiverId){
        var messages_resp = chatMessageService.getSentMessages(sentId, receiverId);

        return ResponseEntity.ok().body(messages_resp);
    }

    @GetMapping("/list/receiver/{receiverId}/sent/{sentId}")
    public ResponseEntity<List<ChatMessage>> getReceivedMessages(@PathVariable("receiverId") Long receiverId, @PathVariable("sentId") Long sentId){
        var messages_resp = chatMessageService.getReceivedMessages(receiverId, sentId);

        return ResponseEntity.ok().body(messages_resp);
    }
}
