import * as express from "express";
import * as cookieParser from "cookie-parser";
import * as csrf from "csurf";
import { ResponseHandling, StatusCode } from "./responseHandling";

/**
 * Applies CSRF protection middleware to the Express app.
 * - Uses cookies to store CSRF tokens.
 * - Provides a /csrf-token endpoint for the frontend to fetch the CSRF token.
 * - Handles CSRF errors and returns a standardized forbidden response.
 * @param app The Express application instance.
 */
export const applyCSRF = (app: express.Application) => {
  app.use(cookieParser()); // Required for CSRF token storage

  // CSRF Protection Middleware (uses cookies)
  const csrfProtection = csrf({ cookie: true });
  app.use(csrfProtection);

  // Endpoint to serve CSRF token to the frontend
  app.get("/csrf-token", (req, res) => {
    res.json({ csrfToken: req.csrfToken() });
  });

  // Error handler for CSRF errors
  app.use((err, req, res, next) => {
    if (err.code === "EBADCSRFTOKEN") {
      // Respond with 403 Forbidden if CSRF token is invalid
      return ResponseHandling(res, "Invalid CSRF Token", StatusCode.FORBIDDEN);
    } else {
      next(err);
    }
  });
};
