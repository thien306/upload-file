package com.codegym.service;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class UploadFileService {

    public void uploadFile(MultipartFile file) throws IOException {
        String uploadFolder = "D:\\codegym\\modele 4-2\\Data Binding & Form\\upload-file\\src\\main\\resources\\upload_file.properties";
        String filename = file.getOriginalFilename();
        FileCopyUtils.copy(file.getBytes(), new File(uploadFolder + filename));

    }
}
