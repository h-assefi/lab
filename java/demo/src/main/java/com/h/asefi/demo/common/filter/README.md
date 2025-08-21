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

---

# MaintenanceModeFilter

The `MaintenanceModeFilter` is a custom Spring Boot filter that helps enforce application maintenance mode. When maintenance mode is enabled, most endpoints will return a `503 Service Unavailable` response, indicating that the service is temporarily unavailable due to maintenance.

## How MaintenanceModeFilter Works

- Checks the current maintenance mode status using the `StatusService`.
- Allows requests to the `/status/maintainable` endpoint to always pass through (so maintenance mode can be toggled).
- For all other endpoints, if maintenance mode is active:
  - Responds with HTTP status `503 Service Unavailable`.
  - Returns a JSON response with a status and message indicating the service is under maintenance.
- If maintenance mode is not active, the request proceeds as normal.

## Example JSON Response

```json
{
  "status": "MAINTENANCE",
  "message": "The service is currently under maintenance. Please try again later."
}
```

## Considerations

- Ensure that the `/status/maintainable` endpoint is secured or restricted to authorized users to prevent unauthorized toggling of maintenance mode.
- Maintenance mode is useful for planned downtime, deployments, or critical updates.

---

## Summary

The `SecurityHeadersFilter` and `MaintenanceModeFilter` add important layers of security and operational control to your Spring Boot application. Security headers help protect against common web vulnerabilities, while maintenance mode ensures users receive clear communication during planned outages.  
**Always test your application after enabling or modifying these filters to ensure all functionality works as expected and that no legitimate requests are unintentionally blocked.**
