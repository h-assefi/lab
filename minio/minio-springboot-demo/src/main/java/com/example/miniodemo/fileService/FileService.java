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
    /**
     * Uploads a file to the configured MinIO bucket.
     *
     * @param file the file to upload (multipart form data)
     * @throws Exception if the upload fails or MinIO is unreachable
     */
    void uploadFile(MultipartFile file) throws Exception;

    /**
     * Retrieves a file from MinIO as an InputStream.
     *
     * @param filename the name of the file to retrieve
     * @return InputStream of the file's contents
     * @throws Exception if the file does not exist or retrieval fails
     */
    InputStream getFile(String filename) throws Exception;

    /**
     * Generates a pre-signed URL for downloading a file from MinIO.
     * The URL can be shared and used to download the file without direct
     * authentication.
     *
     * @param filename the name of the file to generate the download URL for
     * @return a pre-signed URL string for downloading the file
     * @throws Exception if URL generation fails
     */
    String generateDownloadUrl(String filename) throws Exception;

    /**
     * Generates a pre-signed URL for uploading a file to MinIO.
     * The URL can be used to upload a file directly to MinIO without direct
     * authentication.
     *
     * @param filename the name of the file to be uploaded
     * @return a pre-signed URL string for uploading the file
     * @throws Exception if URL generation fails
     */
    String generateUploadUrl(String filename) throws Exception;
}
