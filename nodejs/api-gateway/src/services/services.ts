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
