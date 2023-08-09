package com.store.departments.Models;

import com.store.departments.DTO.DepartmentRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@Builder
@Document("departments")
public class Department {

    @Id
    private String id;

    private String department_name;

    public Department(){
        this.id = new Object().toString();

    }

    public Department(DepartmentRequest departmentRequest){
        this.department_name = departmentRequest.department_name();
    }
}
