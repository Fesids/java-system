package com.store.departments.Services;

import com.store.departments.DTO.DepartmentRequest;
import com.store.departments.Models.Department;

import java.util.List;

public interface DepartmentService {

    Department createDepartment(DepartmentRequest departmentRequest) throws Exception;

    List<Department> ListDepartment();

    Department updateDepartment(DepartmentRequest departmentRequest, String id) throws Exception;

    Department detailDepartment(String dept_id);

    void deleteDepartment(String dept_id) throws Exception;

}
