package com.registration.registration.services;

import com.registration.registration.DTO.ProfileDTO;
import com.registration.registration.exceptions.ProfileNotFoundException;
import com.registration.registration.models.Friend;
import com.registration.registration.models.Profile;
import com.registration.registration.repositories.FriendRepository;
import com.registration.registration.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private FriendRepository friendRepository;




    @Override
    public Profile createProfile(ProfileDTO profileDTO) {
        var friendSet = new HashSet<Friend>();

        Iterator<Long> friendsList = profileDTO.friends().iterator();
        while (friendsList.hasNext()){
            var friend = friendRepository.findById(friendsList.next());
            friendSet.add(friend.get());
        }
        var profile = Profile.builder().pic(profileDTO.pic()).name(profileDTO.name())
                .user_id(profileDTO.user_id()).friends(friendSet).build();

        return profileRepository.save(profile);
    }

    @Override
    public Profile getProfileByUserId(Long userId) {
        return profileRepository.getProfileByUserId(userId)
                .orElseThrow(
                        ()-> new ProfileNotFoundException("No profile found")
                );
    }

    @Override
    public Profile getProfileById(Long profileId) {
        return profileRepository.findProfileById(profileId)
                .orElseThrow(
                        () -> new ProfileNotFoundException("No profile found")
                );
    }

    @Override
    public Profile addFriendToProfile(Long profileId, Long friendId) {
        var profile = profileRepository.findById(profileId).get();

        var friend =friendRepository.findById(friendId).get();
        /*var fid = friend.getProfile().getId();
        var other_profile = profileRepository.findById(fid).get();
        var other_friend = friendRepository.findFriendByProfileId(profileId).get();*/




        /*Iterator<Friend> friendList = profile.getFriends().iterator();
        while (friendList.hasNext()){
            if(friendList.next() == friend){
                throw new RuntimeException("Friend already exist");
            }
            profile.getFriends().add(friend);

        }*/
        profile.getFriends().add(friend);

        /*var fid = friend.getProfile().getId();
        var other_profile = profileRepository.findById(fid).get();
        var other_friend = friendRepository.findFriendByProfileId(profileId).get();
        other_profile.getFriends().add(other_friend);*/

        return profile;

    }
}
