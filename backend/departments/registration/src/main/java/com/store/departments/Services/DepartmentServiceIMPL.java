package com.store.departments.Services;

import com.mongodb.client.result.UpdateResult;
import com.store.departments.DTO.DepartmentRequest;
import com.store.departments.Models.Department;
import com.store.departments.Repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceIMPL implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Department createDepartment(DepartmentRequest departmentRequest) throws Exception {
        try{
            Query query = new Query();
            query.addCriteria(Criteria.where("department_name")
                    .is(departmentRequest.department_name()));
            var deptNameExist = mongoTemplate.findOne(query, Department.class, "departments");


            var dept = new Department(departmentRequest);
            var newDept = departmentRepository.save(dept);
            return newDept;
        } catch (Exception e){
            throw new Exception("Failed to create department");
        }

    }

    @Override
    public List<Department> ListDepartment() {
        return mongoTemplate.findAll(Department.class);
    }

    @Override
    public Department updateDepartment(DepartmentRequest departmentRequest, String id) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        try{
            var oldDept = new Update().set("department_name", departmentRequest.department_name());

            FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);

            var deptUP = mongoTemplate.findAndModify(query, oldDept, Department.class);

            return deptUP;
        } catch (Exception e){
            throw new Exception("Failed to update department");
        }


    }

    @Override
    public Department detailDepartment(String dept_id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(dept_id));
        var deptNameExist = mongoTemplate.findOne(query, Department.class, "departments");

        var department = mongoTemplate.findOne(query, Department.class, "departments");

        return department;

    }

    @Override
    public void deleteDepartment(String dept_id) throws Exception {
        try{
            departmentRepository.deleteById(dept_id);

        } catch (Exception e){
            throw new Exception("failed to delete department with id "+dept_id+" failed");
        }
    }


}
