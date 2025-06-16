import * as express from "express";

export const applyHealthCheckRoute = (app: express.Application) => {
  app.get("/health", (req, res) => {
    const healthCheck = {
      uptime: process.uptime(),
      message: "OK",
      timestamp: Date.now(),
    };
    try {
      res.status(200).json(healthCheck);
    } catch (err) {
      res.status(503).json({ message: "Service Unavailable" });
    }
  });
};
