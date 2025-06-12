import * as express from "express";
import * as cookieParser from "cookie-parser";
import * as csrf from "csurf";
import { ResponseHandling, StatusCode } from "./responseHandling";

export const applyCSRF = (app: express.Application) => {
  app.use(cookieParser()); // Required for CSRF token storage

  // CSRF Protection Middleware
  const csrfProtection = csrf({ cookie: true });
  app.use(csrfProtection);

  // Serve CSRF token to frontend
  app.get("/csrf-token", (req, res) => {
    res.json({ csrfToken: req.csrfToken() });
  });

  app.use((err, req, res, next) => {
    if (err.code === "EBADCSRFTOKEN") {
      return ResponseHandling(res, "Invalid CSRF Token", StatusCode.FORBIDDEN);
    } else {
      next(err);
    }
  });
};
