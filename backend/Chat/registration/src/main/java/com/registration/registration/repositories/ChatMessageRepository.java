package com.registration.registration.repositories;

import com.registration.registration.models.ChatMessage;
import jakarta.validation.constraints.Digits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    @Query(value = "select * from chat_messages where msg_sender = :senderId and msg_receiver = :receiverId or msg_sender = :senderId and msg_receiver= :receiverId", nativeQuery = true)
    List<ChatMessage> findMessagesBySenderAndReceiver(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);

    @Query(value = "select * from chat_messages where msg_sender = :sentId and msg_receiver =:receiverId", nativeQuery = true)
    List<ChatMessage> findSentMessages(@Param("sentId") Long senderId, @Param("receiverId") Long receiverId);

    @Query(value = "select * from chat_messages where msg_receiver = :receiverId and msg_sender =:sentId", nativeQuery = true)
    List<ChatMessage> findReceivedMessages(@Param("sentId") Long senderId, @Param("receiverId") Long receiverId);


}
