import * as express from "express";
import { camelCaseResponseKeys } from "./src/base/infrastructure/middleware/camelCaseMiddleware";
import "dotenv/config";
import * as httpProxy from "http-proxy";
import * as cors from "cors";
import "dotenv/config";
import { Security } from "./src/base/infrastructure/security/security";
import services from "./src/services/services";
import { checkAuthorization } from "./src/base/authorization/authorization";
import {
  ResponseHandling,
  StatusCode,
} from "./src/base/infrastructure/middleware/responseHandling";
import { HttpStatusCode } from "axios";
import * as healthCheckRoute from "./src/routes/general/healthCheckRoute";
import { log } from "console";
import { applyCSRF } from "./src/base/infrastructure/middleware/csrfHandling";

const app: express.Application = express();
const proxy = httpProxy.createProxyServer();

// Middleware to convert all response keys to camelCase
app.use(camelCaseResponseKeys);

// Enable CORS for all origins (adjust for production)
app.use(cors());

// Apply CSRF protection middleware
applyCSRF(app);

// Register health check endpoint at /health
healthCheckRoute.applyHealthCheckRoute(app);

// Main proxy routing logic for all defined services
services.forEach((service) => {
  // Handles all HTTP methods for the service path
  app.all(service.path, async (req: express.Request, res: express.Response) => {
    // Remove the service path from the request URL before proxying
    req.url = req.url.replace(service.path, "");
    // Encrypt and attach the API key to the request headers
    req.headers["api_key"] = Security.encrypt(service.apiKey);
    log(service.requireAuthentication);

    // If the service requires authentication, validate the Bearer token
    if (service.requireAuthentication) {
      // Get the Authorization header
      const auth = req.headers["authorization"];

      // If no Authorization header, return error
      if (!auth) {
        return ResponseHandling(
          res,
          { message: "AUTHORIZATION IS REQUIRED" },
          HttpStatusCode.BadRequest
        );
      }

      // If Authorization header is not Bearer, return error
      if (!auth.startsWith("Bearer")) {
        return ResponseHandling(
          res,
          { message: "BAD AUTHORIZATION" },
          HttpStatusCode.BadRequest
        );
      }

      // Call the external AUTH service to check authorization
      const r = await checkAuthorization(auth);

      // If authorized, proxy the request to the backend service
      if (r.status) {
        proxy.web(req, res, { target: service.url });
      } else {
        // If not authorized, return the error response
        return ResponseHandling(res, r.response, r.statusCode);
      }
    } else {
      // If authentication is not required, proxy the request directly
      proxy.web(req, res, { target: service.url });
    }
  });
});

// Handles errors from the proxy server
proxy.on("error", (err: Error, req: express.Request, res: express.Response) => {
  let errorMessage: string;
  const requestedPath = req.path.toLowerCase();

  // Custom error message for shop service
  if (requestedPath.indexOf("/shop/") > 0) {
    errorMessage = "Shop service is unavailable.";
  } else {
    errorMessage = "Service is unavailable."; // Default
  }
  return ResponseHandling(res, errorMessage, StatusCode.SERVICE_UNAVAILABLE);
});

// Start the Express server on the configured port
const port: number = parseInt(process.env.PORT) || 4100;

app.listen(port, () => {
  console.log(`API Gateway listening on port ${port}`);
});
