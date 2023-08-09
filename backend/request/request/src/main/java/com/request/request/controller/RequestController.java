package com.request.request.controller;


import com.request.request.DTO.ClientRequestDTO;
import com.request.request.DTO.RequestDTO;
import com.request.request.DTO.UpdateRequestDTO;
import com.request.request.UTILS.GeralUtilities;
import com.request.request.models.ClientRequest;
import com.request.request.models.Request;
import com.request.request.services.ClientRequestService;
import com.request.request.services.RequestService;
import com.request.request.services.upload.FileStorageService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/request")
@CrossOrigin("*")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private ClientRequestService clientRequestService;


    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/list")
    public ResponseEntity<List<Request>> AllRequestList(){
        return ResponseEntity.ok().body(requestService.RequestList().stream().toList());
    }


    @PostMapping("/new/{user_id}")
    public ResponseEntity<Request> createRequest(
            @RequestParam("sender_dept_id") String sender_dept_id,

            @RequestParam("destination_dept_id") String destination_dept_id,
            @RequestParam("subject") String subject,

            @RequestParam("body") String body,

            @RequestParam("request_image")MultipartFile multipartFile

            , @PathVariable("user_id") Long user_id){

        var fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        var requestDTO = new RequestDTO(fileName,sender_dept_id, destination_dept_id, subject, body);

        fileStorageService.save(multipartFile);

        var new_request  = requestService.createRequest(requestDTO, user_id);
        return ResponseEntity.created(GeralUtilities.toURi("/new")).body(new_request);
    }

    @PostMapping("/client/send")
    public ResponseEntity<ClientRequest> newClient(@RequestBody ClientRequestDTO clientRequestDTO){
        var new_client_req = clientRequestService.clientRequest(clientRequestDTO);
        return ResponseEntity.created(GeralUtilities.toURi("/client")).body(new_client_req);
    }

    @GetMapping("/list/department/{id}")
    public ResponseEntity<List<ClientRequest>> list(@PathVariable("id") String id){
        return ResponseEntity.ok().body(clientRequestService.clientRequestListByDept(id));
    }

    @GetMapping("/list/received/department/{id}")
    public ResponseEntity<List<Request>> receivedRequestList(@PathVariable("id") String id){
        return ResponseEntity.ok().body(requestService.RequestListByDestinationId(id));

    }

    @GetMapping("/list/sent/department/{id}")
    public ResponseEntity<List<Request>> sentRequestList(@PathVariable("id") String id){
        return ResponseEntity.ok().body(requestService.RequestListBySenderId(id));
    }

    @GetMapping("/clientRequest/detail/{req_id}")
    public ResponseEntity<ClientRequest> clientRequestDetail(@PathVariable("req_id") Long req_id){
        return ResponseEntity.ok().body(clientRequestService.clientRequestDetail(req_id));
    }

    @GetMapping("/all/detail/{req_id}")
    public ResponseEntity<Request> requestDetail(@PathVariable("req_id") Long req_id){
        return ResponseEntity.ok().body(requestService.requestDetail(req_id));
    }

    @DeleteMapping("/clientRequest/delete/{req_id}")
    public ResponseEntity<?> deleteClientRequest(@PathVariable("req_id") Long req_id){
        return ResponseEntity.ok().body(clientRequestService.deleteRequest(req_id));
    }

    @DeleteMapping("/requests/delete/{req_id}")
    public ResponseEntity<?> deleteRequest(@PathVariable("req_id") Long req_id){
        return ResponseEntity.status(HttpStatus.OK).body(requestService.deleteRequest(req_id));
    }

    @PutMapping("/update/request/{req_id}")
    public ResponseEntity<?> updateRequest(@PathVariable("req_id") Long req_id, @RequestBody UpdateRequestDTO updateRequestDTO){
        return ResponseEntity.ok().body(requestService.updateRequest(req_id, updateRequestDTO));
    }
}
