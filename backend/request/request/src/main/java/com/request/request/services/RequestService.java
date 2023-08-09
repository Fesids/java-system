package com.request.request.services;

import com.request.request.DTO.RequestDTO;
import com.request.request.DTO.UpdateRequestDTO;
import com.request.request.models.Request;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RequestService {

    Request createRequest(RequestDTO requestDTO, Long user_id);

    Collection<Request> RequestList();

    List<Request> RequestListByDestinationId(String dept_id);

    Request requestDetail(Long req_id);

    String deleteRequest(Long req_id);

    Request updateRequest(Long req_id, UpdateRequestDTO updateRequestDTO);

    List<Request> RequestListBySenderId(String dept_id);
}
