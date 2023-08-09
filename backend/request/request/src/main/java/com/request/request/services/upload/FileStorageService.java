package com.request.request.services.upload;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {
    void init();

    void save(MultipartFile multipartFile);

    Resource load(String filename);

    void deleteAll();

    public Stream<Path> loadAll();

}
