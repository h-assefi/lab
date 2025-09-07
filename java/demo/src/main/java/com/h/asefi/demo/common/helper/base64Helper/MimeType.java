package com.h.asefi.demo.common.helper.base64Helper;

import lombok.Getter;

@Getter
public enum MimeType {
    PNG("image/png"),
    JPEG("image/jpeg"),
    JPG("image/jpeg"),
    GIF("image/gif"),
    BMP("image/bmp"),
    WEBP("image/webp"),
    SVG("image/svg+xml");

    private final String type;

    MimeType(String type) {
        this.type = type;
    }

    // Method to get MimeType from a string
    public static MimeType fromString(String mimeType) {
        for (MimeType type : MimeType.values()) {
            if (type.getType().equalsIgnoreCase(mimeType)) {
                return type;
            }
        }
        return null; // Return null if no match is found
    }

    // Check if a given MIME type is valid
    public static boolean isValidMimeType(String mimeType) {
        return fromString(mimeType) != null;
    }

}
