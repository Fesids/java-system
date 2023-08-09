package com.store.departments.Controllers;

import com.store.departments.DTO.DepartmentRequest;
import com.store.departments.Services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
@CrossOrigin("*")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    @GetMapping("/detail/{dept_id}")
    private ResponseEntity<?> departmentDetail(@PathVariable("dept_id") String id){
        return ResponseEntity.ok().body(departmentService.detailDepartment(id));
    }


    @PostMapping("/new")
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentRequest departmentRequest){
        try {
            var dep = departmentService.createDepartment(departmentRequest);
            return ResponseEntity.ok().body(dep);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Failed to create department");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> listDepartment(){

        return ResponseEntity.ok().body(departmentService.ListDepartment());
    }

    @PatchMapping("/update/{dept_id}")
    public ResponseEntity<?> updateDepartment(@RequestBody DepartmentRequest departmentRequest, @PathVariable("dept_id") String dept_id){
        try {
            return ResponseEntity.ok().body(departmentService.updateDepartment(departmentRequest, dept_id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Failed to update department");
        }
        //return ResponseEntity.ok().body(departmentService.updateDepartment(departmentRequest, dept_id));
    }

    @DeleteMapping("/delete/{dept_id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("dept_id") String dept_id){
        try {
            departmentService.deleteDepartment(dept_id);
            return ResponseEntity.ok().body("delete success");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("something went wrong trying to delete");
        }
    }











}
