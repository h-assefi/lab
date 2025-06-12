import axios, { AxiosResponse } from "axios"; // Import Axios types

export interface ApiResponse {
  // Define the expected structure of your API response data here
  // This is optional but improves type safety
  data: any; // Replace with specific types if known
  status: number;
}

export async function callAPI(url: string, headers: any): Promise<ApiResponse> {
  try {
    const response: AxiosResponse<ApiResponse> = await axios.post(
      url,
      {},
      {
        headers: headers,
      }
    );
    return response;
  } catch (error) {
    // console.error("Error fetching data:", error);
    return error.response;
    // throw error; // Or handle the error differently
  }
}
