package com.registration.registration.services;

import com.registration.registration.DTO.ProfileDTO;
import com.registration.registration.models.Profile;

public interface ProfileService {

    Profile createProfile(ProfileDTO profileDTO);

    Profile getProfileByUserId(Long userId);

    Profile getProfileById(Long profileId);

    Profile addFriendToProfile(Long profileId, Long friendId);

}
