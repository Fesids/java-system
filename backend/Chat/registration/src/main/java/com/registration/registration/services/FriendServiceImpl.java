package com.registration.registration.services;

import com.registration.registration.DTO.FriendDTO;
import com.registration.registration.models.Friend;
import com.registration.registration.repositories.FriendRepository;
import com.registration.registration.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendServiceImpl implements FriendService{

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private ProfileRepository profileRepository;


    @Override
    public Friend createFriend(FriendDTO friendDTO) {
        var profile = this.profileRepository.findById(friendDTO.profile_id());

        var friend_body = Friend.builder().profile(profile.get()).build();

        var friend = this.friendRepository.save(friend_body);

        return friend;
    }

    @Override
    public Friend getFriendByProfileId(Long profileId) {
        return friendRepository.findFriendByProfileId(profileId).get();

    }
}
