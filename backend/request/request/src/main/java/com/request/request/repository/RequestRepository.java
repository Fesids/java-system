package com.request.request.repository;

import com.request.request.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;


@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query(value = "select * from request_history where destination_dept_id= :destination_id", nativeQuery = true)
    Collection<Request> findAllReceivedRequestById(@Param("destination_id") String destination_id);

    @Query(value = "select * from request_history where sender_dept_id= :sender_dept_id", nativeQuery = true)
    Collection<Request> findAllSenderRequestById(@Param("sender_dept_id") String sender_dept_id);

    @Modifying
    @Query(value = "update request_history set destination_dept_id= :destination_dept_id , sender_dept_id= :sender_dept_id, user_sender_id= :user_sender_id where request_id= :request_id", nativeQuery = true)
    void updateDestIdAndSenderId(@Param("destination_dept_id") String destination_dept_id, @Param("sender_dept_id") String sender_dept_id, @Param("user_sender_id") Long user_sender_id, @Param("request_id") Long request_id);
}
