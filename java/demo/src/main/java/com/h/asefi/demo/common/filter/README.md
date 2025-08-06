# SecurityHeadersFilter

The `SecurityHeadersFilter` is a custom Spring Boot filter that adds important HTTP security headers to every response. These headers help protect your application from common web vulnerabilities such as Cross-Site Scripting (XSS), MIME type confusion, and content injection attacks.

## Headers Set by SecurityHeadersFilter

### 1. `X-XSS-Protection: 1; mode=block`

- **Purpose:**  
  Enables the browser’s built-in XSS (Cross-Site Scripting) filter. If the browser detects a reflected XSS attack, it will prevent the page from rendering.
- **Considerations:**
  - Supported in older browsers (like Internet Explorer and some versions of Chrome).
  - Modern browsers (like recent Chrome and Edge) have removed support for this header, relying on stronger built-in protections.
  - It is safe to include, but do not rely solely on it for XSS protection. Always escape and validate user input.

### 2. `X-Content-Type-Options: nosniff`

- **Purpose:**  
  Prevents browsers from MIME-sniffing a response away from the declared `Content-Type`. This helps stop attacks where a file is disguised as a different type (e.g., serving JavaScript as an image).
- **Considerations:**
  - Strongly recommended for all web applications.
  - Helps prevent some types of drive-by download and content confusion attacks.

### 3. `Content-Security-Policy: default-src 'self'`

- **Purpose:**  
  Restricts the sources from which content (scripts, images, styles, etc.) can be loaded. `'self'` means only resources from the same origin (your domain) are allowed.
- **Considerations:**
  - This is a strong defense against XSS and data injection attacks.
  - If your application loads resources (scripts, images, fonts, etc.) from external domains (like CDNs), you will need to adjust the policy to allow those sources (e.g., `default-src 'self' https://cdn.example.com`).
  - A strict policy may break functionality if not configured to match your resource usage.
  - For advanced use, consider specifying separate directives for scripts, styles, images, etc. (e.g., `script-src`, `style-src`).

## Summary

The `SecurityHeadersFilter` adds an extra layer of security to your Spring Boot application by setting HTTP headers that instruct browsers to enforce stricter security rules. While these headers are helpful, they should be used in combination with secure coding practices such as input validation, output escaping, and regular security reviews.

\*\*Always test your application after enabling or modifying security headers to ensure that all functionality works as expected and that no legitimate
