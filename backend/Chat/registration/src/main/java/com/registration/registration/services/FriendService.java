package com.registration.registration.services;

import com.registration.registration.DTO.FriendDTO;
import com.registration.registration.models.Friend;

public interface FriendService {

    Friend createFriend(FriendDTO friendDTO);

    Friend getFriendByProfileId(Long profileId);
}
