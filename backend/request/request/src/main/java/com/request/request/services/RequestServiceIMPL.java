package com.request.request.services;

import com.request.request.DTO.RequestDTO;
import com.request.request.DTO.UpdateRequestDTO;
import com.request.request.exceptions.RequestNotFoundException;
import com.request.request.models.Request;
import com.request.request.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class RequestServiceIMPL implements RequestService {

    @Autowired
    private RequestRepository requestRepository;


    @Override
    public Request createRequest(RequestDTO requestDTO, Long user_id) {


        Request new_request = Request.builder().destination_dept_id(requestDTO.destination_dept_id())
                .request_image(requestDTO.request_image())
                .sender_dept_id(requestDTO.sender_dept_id())
                .user_sender_id(user_id)
                .subject(requestDTO.subject())
                .body(requestDTO.body())
                .created_at(LocalDateTime.now())
                .build();
        return requestRepository.save(new_request);
    }

    @Override
    public Collection<Request> RequestList() {
        return requestRepository.findAll();
    }

    @Override
    public List<Request> RequestListByDestinationId(String dept_id) {
        return requestRepository.findAllReceivedRequestById(dept_id).stream().toList();
    }

    @Override
    public Request requestDetail(Long req_id) {
        return requestRepository.findById(req_id)
                .orElseThrow(
                        () -> new RequestNotFoundException("No request found")
                );
    }

    @Override
    public String deleteRequest(Long req_id) {
        try {
            requestRepository.deleteById(req_id);
            return "request with id " + req_id + " was deleted";
        } catch (Exception e) {
            throw new RuntimeException("failed to delete request");
        }
    }

    @Override
    public Request updateRequest(Long req_id, UpdateRequestDTO updateRequestDTO) {
        requestRepository.updateDestIdAndSenderId(updateRequestDTO.destination_dept_id(), updateRequestDTO.sender_dept_id(), updateRequestDTO.user_sender_id(), req_id);

        return requestRepository.findById(req_id).get();


    }

    @Override
    public List<Request> RequestListBySenderId(String dept_id) {
        return requestRepository.findAllSenderRequestById(dept_id).stream().toList();
    }
}