package com.request.request.models;


import com.request.request.DTO.RequestDTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "request_history")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long request_id;

    private Long user_sender_id;

    private String sender_dept_id;

    private String destination_dept_id;

    private String subject;

    private String body;

    @Column(name = "request_image", nullable = true)
    private String request_image;

    private LocalDateTime created_at;

    /*public Request(RequestDTO requestDTO){
        this.dept_id = requestDTO.dept_id();
        this.subject = requestDTO.subject();
        this.body = requestDTO.body();

    }*/


}
