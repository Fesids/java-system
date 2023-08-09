package com.request.request.controller;


import com.request.request.services.upload.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class testeFile {

    @Autowired
    private FileStorageService fileStorageService;


    @PostMapping("/new")
    public void teste(@RequestParam("teste")MultipartFile multipartFile){
        fileStorageService.save(multipartFile);
    }



}
