import * as crypto from "crypto";
import "dotenv/config";

export class Security {
  static algorithm = "aes-256-cbc"; // Use a strong encryption algorithm

  // Ensure proper key handling (store securely in environment variables or a key management system)
  static key = process.env.API_SECRET_KEY; // Replace with your actual key
  static iv = process.env.API_SECRET_IV_KEY; // Initialization Vector (random for each encryption)

  static encrypt(text: string): string {
    const cipher = crypto.createCipheriv(this.algorithm, this.key, this.iv);

    let encrypted = cipher.update(text, "utf8", "hex");
    encrypted += cipher.final("hex");
    return encrypted;
  }

  static decrypt(encryptedText: string): string {
    const decipher = crypto.createDecipheriv(this.algorithm, this.key, this.iv);

    let decrypted = decipher.update(encryptedText, "hex", "utf8");
    decrypted += decipher.final("utf8");
    return decrypted;
  }
}
