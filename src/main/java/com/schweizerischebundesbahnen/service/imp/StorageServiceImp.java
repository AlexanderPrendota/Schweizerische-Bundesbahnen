package com.schweizerischebundesbahnen.service.imp;


import com.schweizerischebundesbahnen.config.StorageProperties;
import com.schweizerischebundesbahnen.exceptions.StorageException;
import com.schweizerischebundesbahnen.exceptions.StorageFileNotFoundException;
import com.schweizerischebundesbahnen.service.api.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


/**
 * Created by aleksandrprendota on 27.03.17.
 *
 * Implimentation StorageService interface
 */

@Service
public class StorageServiceImp implements StorageService {

    private final Path rootLocation;
    @Autowired
    public StorageServiceImp(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    /**
     * Put the upload file to root location
     * @param file File for saving
     * @param fileName New name of file
     */
    @Override
    public void store(MultipartFile file, String fileName) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            /*
            If we need more permissions
            Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            Set<PosixFilePermission> perms = new HashSet<>();
            perms.add(PosixFilePermission.OTHERS_READ);
            perms.add(PosixFilePermission.OTHERS_EXECUTE);
            perms.add(PosixFilePermission.OTHERS_WRITE);
            Files.setPosixFilePermissions(this.rootLocation.resolve(fileName), perms);
            */
            Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            //  Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

//    @Override
//    public Stream<Path> loadAll() {
//        try {
//            return Files.walk(this.rootLocation, 1)
//                    .filter(path -> !path.equals(this.rootLocation))
//                    .map(this.rootLocation::relativize);
//        } catch (IOException e) {
//            throw new StorageException("Failed to read stored files", e);
//        }
//
//    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    /**
     * Getting file from root location by name of file
     * @param filename string name of with with extension
     * @return Existing file
     */
    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

//    @Override
//    public void deleteAll() {
//        FileSystemUtils.deleteRecursively(rootLocation.toFile());
//    }

    /**
     * Init the root folder.
     * Create the directory.
     */
    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

}
