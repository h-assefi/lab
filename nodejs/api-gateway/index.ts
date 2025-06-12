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

app.use(camelCaseResponseKeys);

// Enable CORS for all origins (adjust for production)
app.use(cors());

applyCSRF(app);
healthCheckRoute.applyHealthCheckRoute(app);

// function setCorsHeaders(req, res, next) {
//   res.setHeader("Access-Control-Allow-Origin", "*");
//   res.setHeader(
//     "Access-Control-Allow-Methods",
//     "GET, POST, PUT, PATCH, DELETE"
//   );
//   res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
//   next();
// }

// app.use(setCorsHeaders);

services.forEach((service) => {
  app.all(service.path, async (req: express.Request, res: express.Response) => {
    req.url = req.url.replace(service.path, "");
    req.headers["api_key"] = Security.encrypt(service.apiKey);
    log(service.requireAuthentication);
    if (service.requireAuthentication) {
      //get bearer from header
      const auth = req.headers["authorization"];

      if (!auth) {
        return ResponseHandling(
          res,
          { message: "AUTHORIZATION IS REQUIRED" },
          HttpStatusCode.BadRequest
        );
      }

      if (!auth.startsWith("Bearer")) {
        return ResponseHandling(
          res,
          { message: "BAD AUTHORIZATION" },
          HttpStatusCode.BadRequest
        );
      }

      const r = await checkAuthorization(auth);

      if (r.status) {
        proxy.web(req, res, { target: service.url });
      } else {
        return ResponseHandling(res, r.response, r.statusCode);
      }
    } else proxy.web(req, res, { target: service.url });
  });
});

proxy.on("error", (err: Error, req: express.Request, res: express.Response) => {
  let errorMessage: string;
  const requestedPath = req.path.toLowerCase();

  if (requestedPath.indexOf("/shop/") > 0) {
    errorMessage = "Shop service is unavailable.";
  }  else {
    errorMessage = "Service is unavailable."; // Default
  }
  return ResponseHandling(res, errorMessage, StatusCode.SERVICE_UNAVAILABLE);
});

const port: number = parseInt(process.env.PORT) || 4100;

app.listen(port, () => {
  console.log(`API Gateway listening on port ${port}`);
});
