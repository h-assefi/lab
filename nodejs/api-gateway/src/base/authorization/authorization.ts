import { HttpStatusCode } from "axios";
import services from "../../services/services";
import { callAPI } from "../axios/axios";
import { Security } from "../infrastructure/security/security";

interface AuthorizationResponse {
  status: boolean;
  statusCode: number;
  response: any;
}

export const checkAuthorization = async (
  auth: string
): Promise<AuthorizationResponse> => {
  const apiUrl =
    services.filter((x) => x.name === "AUTH")[0].url +
    "/api/v1.0/auth/authorize";
  const result = await callAPI(apiUrl, {
    Authorization: auth,
    api_key: Security.encrypt(process.env.AUTHORIZATION_API_KEY),
  });

  if (result.status === HttpStatusCode.Ok) {
    return { status: true, statusCode: result.status, response: result.data };
  } else {
    return { status: false, statusCode: result.status, response: result.data };
  }
};
