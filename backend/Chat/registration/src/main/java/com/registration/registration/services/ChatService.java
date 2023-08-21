package com.registration.registration.services;

import com.registration.registration.models.Chat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface ChatService {

    Chat createChat(Long firstId, Long secondId);

    List<Chat> findChatsByProfile(Long profileId);
}
