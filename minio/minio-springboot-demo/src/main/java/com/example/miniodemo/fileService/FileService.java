package com.example.miniodemo.fileService;

import io.minio.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Service
public class FileService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    public FileService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * Uploads a file to the MinIO server.
     *
     * First, this method checks if the specified bucket exists. If it doesn't, it
     * creates it.
     * Then, it uploads the file with the specified name to the bucket.
     *
     * @param file the file to upload
     * @throws Exception if there is an exception when uploading the file
     */
    public void uploadFile(MultipartFile file) throws Exception {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(file.getOriginalFilename())
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build());
    }

    /**
     * Retrieves a file from the MinIO server.
     *
     * @param filename the name of the file to retrieve
     * @return the contents of the file as an InputStream
     * @throws Exception if there is an exception when retrieving the file
     */
    public InputStream getFile(String filename) throws Exception {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(filename)
                        .build());
    }

    /**
     * Generates a presigned URL for downloading a file from the MinIO server.
     *
     * @param filename the name of the file to generate a download URL for
     * @return a presigned URL that can be used to download the file
     * @throws Exception if there is an exception when generating the presigned URL
     */
    public String generateDownloadUrl(String filename) throws Exception {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(filename)
                        .expiry(600, TimeUnit.SECONDS)
                        .build());
    }

    /**
     * Generates a presigned URL for uploading a file to the MinIO server.
     *
     * @param filename the name of the file to generate an upload URL for
     * @return a presigned URL that can be used to upload the file
     * @throws Exception if there is an exception when generating the presigned URL
     */
    public String generateUploadUrl(String filename) throws Exception {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.PUT)
                        .bucket(bucketName)
                        .object(filename)
                        .expiry(600, TimeUnit.SECONDS)
                        .build());
    }
}