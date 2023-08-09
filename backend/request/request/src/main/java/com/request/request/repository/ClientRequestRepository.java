package com.request.request.repository;

import com.request.request.models.ClientRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ClientRequestRepository extends JpaRepository<ClientRequest, Long> {

    @Query(value = "select * from client_requests where destination_dept_id = :dept_id",nativeQuery = true)
    Collection<ClientRequest> getClientReqByDeptId(@Param("dept_id") String dept_id);
}
