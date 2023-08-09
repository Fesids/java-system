package com.request.request.services;

import com.request.request.DTO.ClientRequestDTO;
import com.request.request.exceptions.ClientRequestNotFoundException;
import com.request.request.models.ClientRequest;
import com.request.request.repository.ClientRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ClientRequestServiceIMPL implements ClientRequestService {

    @Autowired
    private ClientRequestRepository salesRequestRepository;


    @Override
    public ClientRequest clientRequest(ClientRequestDTO salesRequestDTO) {
        var new_req = ClientRequest.builder().client_email(salesRequestDTO.client_email())
                .destination_dept_id(salesRequestDTO.destination_dept_id())
                .subject(salesRequestDTO.subject())
                .body(salesRequestDTO.body())
                .created_at(LocalDateTime.now())
                .build();
        return salesRequestRepository.save(new_req);
    }

    @Override
    public List<ClientRequest> clientRequestListByDept(String dept_id) {
        return salesRequestRepository.getClientReqByDeptId(dept_id).stream().toList();
    }

    @Override
    public ClientRequest clientRequestDetail(Long request_id) {
        return salesRequestRepository.findById(request_id)
                .orElseThrow(
                        ()-> new ClientRequestNotFoundException("Client not found")
                );

    }

    @Override
    public String deleteRequest(Long request_id) {
        try{
            salesRequestRepository.deleteById(request_id);
            return "client request with id " +
                    request_id+" was deleted";

        }catch (Exception e){
            throw new RuntimeException("failed to delete request");
        }
    }
}
