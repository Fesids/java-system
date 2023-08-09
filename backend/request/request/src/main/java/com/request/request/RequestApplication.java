package com.request.request;

import com.request.request.services.upload.FileStorageService;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class RequestApplication implements CommandLineRunner {
    public static void main(String[] args){

        SpringApplication.run(RequestApplication.class, args);
    }

    @Resource
    private FileStorageService fileStorageService;

    @Override
    public void run(String... args) throws Exception {
        fileStorageService.init();
    }
}



