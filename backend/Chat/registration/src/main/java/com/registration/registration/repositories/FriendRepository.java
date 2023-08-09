package com.registration.registration.repositories;

import com.registration.registration.models.Friend;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query(value = "select * from friends where profile_id= :profileId", nativeQuery = true)
    Optional<Friend> findFriendByProfileId(@Param("profileId") Long profileId);
}
