package com.h.asefi.demo.common.exception.exceptionFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessage {

    private String appName;

    private String context;

    private String message;

    private List<String> details;

    private String remoteUser;

    private String serverAddress;

}
