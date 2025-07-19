import axios, { AxiosResponse, CancelTokenSource } from "axios";

export interface ApiResponse {
  // Define the expected structure of your API response data here
  // This is optional but improves type safety
  data: any; // Replace with specific types if known
  status: number;
}

/**
 * Calls an API endpoint with the ability to cancel the request.
 * To cancel, pass a CancelTokenSource as the third argument.
 * @param url The API endpoint URL.
 * @param headers HTTP headers to include in the request.
 * @param cancelSource Optional Axios CancelTokenSource for cancellation.
 * @returns ApiResponse from the API call.
 */
export async function callAPI(
  url: string,
  headers: any,
  cancelSource?: CancelTokenSource
): Promise<ApiResponse> {
  try {
    const response: AxiosResponse<ApiResponse> = await axios.post(
      url,
      {},
      {
        headers: headers,
        cancelToken: cancelSource ? cancelSource.token : undefined,
      }
    );
    return response;
  } catch (error) {
    if (axios.isCancel(error)) {
      return { data: null, status: 499 }; // 499 Client Closed Request (custom)
    }
    return error.response;
    // throw error; // Or handle the error differently
  }
}
