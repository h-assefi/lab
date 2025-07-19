package com.h.asefi.demo.setting;


import com.h.asefi.demo.common.exception.exceptionTypes.BadRequestException;
import com.h.asefi.demo.common.exception.exceptionTypes.CustomException;
import com.h.asefi.demo.common.exception.exceptionTypes.ResourceNotFoundException;

public interface SettingService {
    Setting getSettingByKey(String key) throws ResourceNotFoundException, BadRequestException;

    void addOrUpdate(String key, String value) throws BadRequestException, ResourceNotFoundException, CustomException;
}
