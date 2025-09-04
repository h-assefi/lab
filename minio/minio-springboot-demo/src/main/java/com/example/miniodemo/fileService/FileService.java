package com.example.miniodemo.fileService;

import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;

/**
 * Service interface for handling file operations with MinIO object storage.
 * <p>
 * Provides methods for uploading files, retrieving files as streams,
 * and generating pre-signed URLs for secure upload and download operations.
 * Implementations of this interface encapsulate the logic for interacting
 * with the MinIO server using the MinIO Java SDK.
 * </p>
 *
 * <ul>
 * <li>{@link #uploadFile(MultipartFile)}: Uploads a file to the MinIO
 * bucket.</li>
 * <li>{@link #getFile(String)}: Retrieves a file as an InputStream from
 * MinIO.</li>
 * <li>{@link #generateDownloadUrl(String)}: Generates a pre-signed URL for
 * downloading a file.</li>
 * <li>{@link #generateUploadUrl(String)}: Generates a pre-signed URL for
 * uploading a file.</li>
 * </ul>
 */
public interface FileService {
    void uploadFile(MultipartFile file) throws Exception;

    InputStream getFile(String filename) throws Exception;

    String generateDownloadUrl(String filename) throws Exception;

    String generateUploadUrl(String filename) throws Exception;
}
