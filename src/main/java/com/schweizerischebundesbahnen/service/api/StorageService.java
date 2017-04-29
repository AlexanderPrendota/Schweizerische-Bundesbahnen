package com.schweizerischebundesbahnen.service.api;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Created by aleksandrprendota on 27.03.17.
 */
public interface StorageService {
    void init();

    void store(MultipartFile file, String fileName);

//    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

//    void deleteAll();

}
