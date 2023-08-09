package com.request.request.services;

import com.request.request.DTO.ClientRequestDTO;
import com.request.request.models.ClientRequest;

import java.util.List;

public interface ClientRequestService {

    ClientRequest clientRequest(ClientRequestDTO salesRequestDTO);

    List<ClientRequest> clientRequestListByDept(String dept_id);

    ClientRequest clientRequestDetail(Long request_id);

    String deleteRequest(Long request_id);
}













