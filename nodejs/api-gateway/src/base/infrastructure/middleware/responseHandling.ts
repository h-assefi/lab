import * as express from "express";
import { LogicalError, UnhandledError } from "./errorHandling";
import "dotenv/config";

export function ResponseHandling(
  res: express.Response,
  value: any,
  statusCode: number = StatusCode.OK
): express.Response {
  if (typeof value === "object" && value?.name === UnhandledError.name)
    return res
      .status(StatusCode.SERVER_ERROR)
      .json({ message: value.message, innerException: value?.stack });
  else if (typeof value === "object" && value?.name === LogicalError.name) {
    return res
      .status(StatusCode.NOT_ACCEPTABLE)
      .json({ message: value.message });
  } else
    return res
      .status(statusCode)
      .json(typeof value === "object" ? { data: value } : { message: value });
}

export const StatusCode = {
  OK: 200,
  BAD_REQUEST: 400,
  UNAUTHORIZED: 401,
  FORBIDDEN: 403,
  NOT_FOUNT: 404,
  NOT_ACCEPTABLE: 406,
  SERVER_ERROR: 500,
  SERVICE_UNAVAILABLE: 503,
};
