import { replaceAll } from "./string";

export function objectKeysToCamelCase(
  obj: Record<string, any>
): Record<string, any> {
  if (obj === null || typeof obj !== "object" || obj instanceof Date) {
    return obj;
  }

  const newObj = Array.isArray(obj) ? [] : {};
  for (let key in obj) {
    key = replaceAll(replaceAll(key, "-", ""), "_", "");
    const newKey = key.charAt(0).toLowerCase() + key.slice(1);
    newObj[newKey] = objectKeysToCamelCase(obj[key]);
  }

  return newObj;
}
