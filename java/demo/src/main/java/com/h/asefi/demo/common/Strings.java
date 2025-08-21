package com.h.asefi.demo.common;

public class Strings {
    public static final String BadRequest = "Bad Request";
    public static final String UnhandledException = "Unhandled Exception";
    public static final String UnderMaintenance = "Under Maintenance";

    public static String NotFound(String item) {
        return String.format("%s not found", item == null ? "Item" : item);
    }
}
