package com.registration.registration.repositories;

import com.registration.registration.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query(value = "select * from profiles where user_id= :userid", nativeQuery = true)
    Optional<Profile> getProfileByUserId(@Param("userid") Long userId);



    Optional<Profile> findProfileById(Long profile_id);


}
