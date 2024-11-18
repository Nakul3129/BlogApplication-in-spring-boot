package com.blogApplication.BlogApplication.in.spring.boot.services.impl;

import com.blogApplication.BlogApplication.in.spring.boot.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        // it fetches the original name of the file...
        String name = file.getOriginalFilename();

        // it generates the random name of the file
        String randomId = UUID.randomUUID().toString();
        String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));

        // fullpath
        String filePath = path+ File.separator+fileName1;

        File f = new File(path);
        if(! f.exists()){
            f.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));


        return fileName1;
    }

    @Override
    public InputStream getResource(String path, String filename) throws FileNotFoundException {

        String fullPath = path+File.separator+filename;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }
}
