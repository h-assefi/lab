package com.h.asefi.demo.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = BaseController.BASE_URL)
public class BaseController {
    public static final String BASE_URL = "/api";
}
