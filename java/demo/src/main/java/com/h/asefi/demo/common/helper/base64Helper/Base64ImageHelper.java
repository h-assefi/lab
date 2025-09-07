package com.h.asefi.demo.common.helper.base64Helper;

import lombok.Setter;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class Base64ImageHelper extends Base64Helper {

    private static int maxSizeKB = 1024; // Default max size is 1024KB (1MB)
    @Setter
    private static boolean checkMimeType = true;
    @Setter
    private static boolean checkMaxSize = true;
    @Setter
    private static List<MimeType> allowedMimeTypes = List.of(MimeType.PNG, MimeType.JPEG, MimeType.JPG); // Default allowed types

    // Method to set max size in KB (User-configurable)
    public static void setMaxSizeKB(int maxSizeKB) {
        if (maxSizeKB > 0) {
            Base64ImageHelper.maxSizeKB = maxSizeKB;
        } else {
            throw new IllegalArgumentException("Max size must be greater than 0 KB.");
        }
    }

    public static boolean isValidBase64Image(String base64String) {
        if (base64String == null || base64String.isEmpty()) {
            return false;
        }

        try {
            // Strip out the data URI prefix if it exists
            base64String = base64String.replaceFirst("^data:image/[^;]+;base64,", "");

            // Decode Base64
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);

            // Check if the size exceeds the user-defined limit
            if (checkMaxSize && decodedBytes.length > maxSizeKB * 1024) {
                return false; // Image is too large
            }

            // Validate image format
            ByteArrayInputStream inputStream = new ByteArrayInputStream(decodedBytes);
            if (ImageIO.read(inputStream) == null) {
                return false; // Not a valid image
            }

            // Determine MIME type
            if (checkMimeType) {
                String mimeType = getMimeType(decodedBytes);
                return allowedMimeTypes.stream().anyMatch(m -> m.getType().equals(mimeType));
            }

            return true;
        } catch (IllegalArgumentException | IOException e) {
            return false; // Invalid Base64 or not an image
        }
    }

    private static String getMimeType(byte[] imageBytes) {
        if (imageBytes.length < 8) {
            return null;
        }

        // Check for PNG signature
        if (imageBytes[0] == (byte) 0x89 && imageBytes[1] == (byte) 0x50 &&
                imageBytes[2] == (byte) 0x4E && imageBytes[3] == (byte) 0x47) {
            return "image/png";
        }

        // Check for JPEG signature (SOI marker)
        if (imageBytes[0] == (byte) 0xFF && imageBytes[1] == (byte) 0xD8) {
            return "image/jpeg";
        }

        return null; // Unknown format
    }
}
