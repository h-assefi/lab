# API Gateway

This project implements an **API Gateway** in Node.js using TypeScript and Express. The gateway acts as a single entry point for client requests, handling authentication, authorization, proxying, security, and response formatting for multiple backend services.

---

## Features

- **Reverse Proxy:** Routes requests to backend services based on path patterns.
- **Authentication & Authorization:** Validates Bearer tokens and checks authorization via an external AUTH service.
- **API Key Encryption:** Encrypts API keys before forwarding them to backend services.
- **CSRF Protection:** Integrates CSRF middleware for secure state-changing requests.
- **CORS Support:** Enables Cross-Origin Resource Sharing for frontend integration.
- **Consistent Response Formatting:** Converts response keys to camelCase and standardizes error handling.
- **Health Check Endpoint:** Provides `/health` endpoint for service monitoring.

---

## Project Structure

```
.
├── .env                  # Environment variables (API keys, secrets, ports)
├── index.ts              # Main entry point (Express app and proxy logic)
├── package.json          # Project dependencies and scripts
├── tsconfig.json         # TypeScript configuration
├── src/
│   ├── base/
│   │   ├── authorization/         # Authorization logic
│   │   ├── axios/                 # Axios wrapper for HTTP requests
│   │   ├── config/                # Configuration (e.g., db_config.ts)
│   │   ├── infrastructure/
│   │   │   ├── middleware/        # Express middlewares (CSRF, error, response, camelCase)
│   │   │   ├── public/            # Utility functions (object, string helpers)
│   │   │   └── security/          # Encryption utilities
│   ├── routes/
│   │   └── general/               # General routes (e.g., health check)
│   └── services/                  # Service definitions and routing info
```

---

## Key Files

- **index.ts**  
  Sets up the Express server, applies middleware, configures proxying, and handles service routing and errors.

- **src/services/services.ts**  
  Defines backend services, their paths, URLs, API keys, and authentication requirements.

- **src/base/authorization/authorization.ts**  
  Handles authorization by calling the AUTH service and validating tokens.

- **src/base/infrastructure/security/security.ts**  
  Provides encryption and decryption utilities for securing API keys.

- **src/base/infrastructure/middleware/**

  - `camelCaseMiddleware.ts`: Converts response keys to camelCase.
  - `csrfHandling.ts`: Adds CSRF protection.
  - `errorHandling.ts`: Standardizes error responses.
  - `responseHandling.ts`: Formats all API responses.

- **src/routes/general/healthCheckRoute.ts**  
  Implements the `/health` endpoint for uptime and status checks.

---

## Environment Variables

Configure the following in your `.env` file:

```env
PORT=4100
NODE_ENV=development
JWT_SECRET=your_jwt_secret
JWT_REFRESH_SECRET=your_refresh_secret
API_SECRET_KEY=your_encryption_key
API_SECRET_IV_KEY=your_iv_key
SHOP_API_KEY=your_shop_api_key
```

---

## Usage

1. **Install dependencies:**

   ```
   npm install
   ```

2. **Compile TypeScript:**

   ```
   npm run compile
   ```

3. **Start the API Gateway:**

   ```
   npm run start:js
   ```

   Or, for development with auto-reload:

   ```
   npm run start
   ```

4. **Health Check:**
   Visit [http://localhost:4100/health](http://localhost:4100/health) to verify the gateway is running.

---

## How It Works

- Incoming requests are matched against service path patterns.
- If authentication is required, the gateway checks for a Bearer token and validates it via the AUTH service.
- API keys are encrypted before being forwarded.
- Requests are proxied to the appropriate backend service.
- Responses are formatted and errors are handled consistently.

---

## Extending the Gateway

- **Add new services:**  
  Edit `src/services/services.ts` to define new backend services and their routing rules.

- **Customize middleware:**  
  Add or modify middleware in `src/base/infrastructure/middleware/` for logging, rate limiting, etc.

- **Enhance security:**  
  Integrate additional security features as needed (e.g., rate limiting, IP whitelisting).

---

## License

MIT License © 2025 Hossein Assefi

---
