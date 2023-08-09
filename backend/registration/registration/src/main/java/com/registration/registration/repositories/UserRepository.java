package com.registration.registration.repositories;


import com.registration.registration.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUsername(String username);

    @Query(value = "select * from users where department_id = :dept_id", nativeQuery = true)
    Collection<User> findUserByDepartment(@Param("dept_id") String id);
}
