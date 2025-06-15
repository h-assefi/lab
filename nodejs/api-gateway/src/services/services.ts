/**
 * This file defines the structure and configuration for backend services
 * that the API Gateway can proxy requests to.
 * 
 * - The ServiceType interface specifies the required properties for each service.
 * - The services array lists all available backend services, including their
 *   routing path, target URL, API key, and authentication requirements.
 * - This configuration is imported by the gateway to determine how to route
 *   and secure incoming requests.
 */

export interface ServiceType {
  name: string;
  path: string;
  url: string;
  apiKey: string;
  requireAuthentication: boolean;
  pathsRequireExtendHeaderSize: string[];
}

const services: ServiceType[] = [
  {
    name: "SHOP",
    path: "/api/*/shop/*",
    url: "http://localhost:4200",
    apiKey: process.env.SHOP_API_KEY,
    requireAuthentication: true,
    pathsRequireExtendHeaderSize: ["/a/product"],
  },
];

export default services;
