package com.example.miniodemo.fileService;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {
    void uploadFile(MultipartFile file) throws Exception;

    InputStream getFile(String filename) throws Exception;

    String generateDownloadUrl(String filename) throws Exception;

    String generateUploadUrl(String filename) throws Exception;
}
