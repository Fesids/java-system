package com.registration.registration.controller;

import com.registration.registration.DTO.ProfileDTO;
import com.registration.registration.UTILS.GeralUtilities;
import com.registration.registration.models.Profile;
import com.registration.registration.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin("*")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/new")
    public ResponseEntity<Profile> createProfile(@RequestBody ProfileDTO profileDTO){
        var respBody = profileService.createProfile(profileDTO);
        URI uri = GeralUtilities.toURI("/new");

        return ResponseEntity.created(uri).body(respBody);
    }


    @GetMapping("/detail/{profileId}")
    public ResponseEntity<Profile>  getProfile(@PathVariable("profileId") Long profileId){
        var resp = profileService.getProfileById(profileId);
        return ResponseEntity.ok().body(resp);
    }


    @GetMapping("/user_id/{userId}")
    public ResponseEntity<Profile> getProfileByUserId(@PathVariable("userId") Long user){
        var resp = profileService.getProfileByUserId(user);

        return ResponseEntity.ok().body(resp);
    }

    @PatchMapping("/{profileId}/friend/{friendId}")
    public ResponseEntity<Profile> addFriend(@PathVariable("profileId") Long profileId, @PathVariable("friendId") Long friendId){
        var profile = profileService.addFriendToProfile(profileId, friendId);

        return ResponseEntity.ok().body(profile);
    }

}
