import { NextFunction, Request, Response } from "express";

export function camelCaseResponseKeys(
  req: Request,
  res: Response,
  next: NextFunction
) {
  const send = res.send;

  res.send = function (
    this: Response<any, Record<string, any>>,
    data: any
  ): any {
    if (typeof data === "object") {
      data = convertObjectKeys(data, true); // Recursive conversion
    }
    send.call(this, data);
  };
  next();
}

function convertObjectKeys(
  obj: Record<string, any>,
  isRoot = false
): Record<string, any> {
  if (obj === null || typeof obj !== "object") {
    return obj;
  }

  const newObj: Record<string, any> = Array.isArray(obj) ? [] : {};
  for (const key in obj) {
    const newKey = isRoot
      ? key.charAt(0).toLowerCase() + key.slice(1)
      : key.replace(/([A-Z])/g, (match) => `_${match.toLowerCase()}`);
    newObj[newKey] = convertObjectKeys(obj[key]);
  }

  return newObj;
}
