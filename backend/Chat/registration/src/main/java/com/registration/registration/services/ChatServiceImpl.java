package com.registration.registration.services;

import com.registration.registration.models.Chat;
import com.registration.registration.models.Profile;
import com.registration.registration.repositories.ChatRepository;
import com.registration.registration.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChatServiceImpl implements ChatService{

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Chat createChat(Long firstId, Long secondId) {
        var firstProfile = profileRepository.findById(firstId)
                .orElseThrow(()->new RuntimeException("Profile with id "+firstId+" not found"));
        var secProfile = profileRepository.findById(secondId)
                .orElseThrow(()->new RuntimeException("Profile with id "+secondId+" not found"));


        HashSet<Profile> profiles = new HashSet<>();
        profiles.add(firstProfile);
        profiles.add(secProfile);

        var chat = Chat.builder().members(profiles).build();


        return chatRepository.save(chat);
    }

    @Override
    public List<Chat> findChatsByProfile(Long profileId) {
        var profile = profileRepository.findById(profileId).get();

        var chats = chatRepository.findAll().stream().map(
                chat -> chat
        ).filter(chat -> chat.getMembers().contains(profile)).toList();

        var cts = chatRepository.findAll().stream().filter(
                chat-> chat.getMembers().contains(profile)
        ).toList();

        var teste = chatRepository.findAll();

        //var chats = chatRepository.
        return cts;
    }
}
