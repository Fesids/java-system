package com.registration.registration.controller;

import com.registration.registration.UTILS.GeralUtilities;
import com.registration.registration.models.Chat;
import com.registration.registration.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin("*")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/new/firstId/{firstId}/secondId/{secondId}")
    public ResponseEntity<Chat> createChat(@PathVariable("firstId") Long firstId, @PathVariable("secondId") Long secondId) {

        if (firstId == null || secondId == null) {
            throw new RuntimeException("Some of required path variables wasn't provided");
        }
        var chat = chatService.createChat(firstId, secondId);

        return ResponseEntity.created(GeralUtilities.toURI("/new")).body(chat);
    }

    @GetMapping("/all/profile/{profileId}")
    public ResponseEntity<List<Chat>> getChatsByProfile(@PathVariable("profileId") Long profileId){

        if(profileId == null){
            throw new RuntimeException("No profile provided");
        }

        var chats = chatService.findChatsByProfile(profileId);

        return ResponseEntity.ok(chats);

    }
}