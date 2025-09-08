# Base64 Helper Module

The `base64Helper` module provides utility classes for encoding, decoding, and handling Base64 data, especially for images and MIME types. These helpers simplify working with Base64 strings in Java applications, such as converting images to Base64 format for web APIs or decoding Base64 data for file storage.

## Purpose

- **Base64Helper.java**:  
  General-purpose Base64 encoding and decoding utilities for strings and byte arrays.

- **Base64ImageHelper.java**:  
  Specialized methods for converting images to and from Base64 strings, including support for image MIME types.

- **MimeType.java**:  
  Utility for detecting and working with MIME types, especially when handling image data.

## Usage

### Encode a String or Byte Array

```java
String encoded = Base64Helper.encode("your data");
byte[] encodedBytes = Base64Helper.encodeBytes(yourByteArray);
```

### Decode a Base64 String

```java
String decoded = Base64Helper.decode("base64String");
byte[] decodedBytes = Base64Helper.decodeBytes("base64String");
```

### Encode an Image to Base64

```java
String base64Image = Base64ImageHelper.encodeImage(imageFile, MimeType.PNG);
```

### Decode a Base64 Image String

```java
BufferedImage image = Base64ImageHelper.decodeImage(base64ImageString);
```

### Detect MIME Type

```java
String mimeType = MimeType.detectMimeType(fileOrBytes);
```

## When to Use

- When you need to transfer images or files as Base64 strings in APIs.
- When you need to decode Base64 data for file storage or processing.
- When you need to detect or validate MIME types for uploaded files.

---

These helpers make it easy to work with Base64 data and images in your Java application, ensuring proper encoding, decoding, and MIME type
