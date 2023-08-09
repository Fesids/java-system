package com.registration.registration.controller;

import com.registration.registration.DTO.FriendDTO;
import com.registration.registration.UTILS.GeralUtilities;
import com.registration.registration.models.Friend;
import com.registration.registration.services.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/friend")
@CrossOrigin("*")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<Friend> getFriendByProfileId(@PathVariable("profileId") Long profileId){
        var resp = friendService.getFriendByProfileId(profileId);

        return ResponseEntity.ok().body(resp);
    }


    @PostMapping("/new")
    public ResponseEntity<Friend> createFriend(@RequestBody FriendDTO friendDTO){

        var friend = friendService.createFriend(friendDTO);

        var uri = GeralUtilities.toURI("/new");

        return ResponseEntity.created(uri).body(friend);

    }
}
