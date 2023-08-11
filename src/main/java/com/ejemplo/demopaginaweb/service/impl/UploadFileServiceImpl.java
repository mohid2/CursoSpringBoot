package com.ejemplo.demopaginaweb.service.impl;

import com.ejemplo.demopaginaweb.service.IUploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import java.io.IOException;
import java.net.MalformedURLException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
;
@Service
public class UploadFileServiceImpl implements IUploadFileService {
    private final Logger log= LoggerFactory.getLogger(getClass());
    private final static String UPLOAD_FOLDER= "uploads";
    @Override
    public Resource load(String filename) throws MalformedURLException {
        Path pathFoto = getPath(filename);
        log.info("pathFoto:" + pathFoto);
        Resource resource=new UrlResource(pathFoto.toUri());
        if(!resource.exists() || !resource.isReadable()){
            throw new RuntimeException("Error: no se puede cargar la imgen "+pathFoto.toString());
        }

        return resource;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        String uniqueFileName= UUID.randomUUID().toString()+"_"+ file.getOriginalFilename();
        Path rutaPath=getPath(uniqueFileName);

        Files.copy(file.getInputStream(),rutaPath);

        return uniqueFileName;
    }

    @Override
    public boolean delete(String fileName) {
        Path rootPaht = getPath(fileName);
        File archivo= rootPaht.toFile();
        if(archivo.exists() && archivo.canRead()){
            if(archivo.delete()){
                return true;
            }
        }
        return false;
    }

    public Path getPath(String filename ){
        return  Paths.get(UPLOAD_FOLDER).resolve(filename).toAbsolutePath();

    }
}
