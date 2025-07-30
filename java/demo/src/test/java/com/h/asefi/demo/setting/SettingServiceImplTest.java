package com.h.asefi.demo.setting;

import com.h.asefi.demo.common.Strings;
import com.h.asefi.demo.common.exception.exceptionTypes.BadRequestException;
import com.h.asefi.demo.common.exception.exceptionTypes.CustomException;
import com.h.asefi.demo.common.exception.exceptionTypes.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SettingServiceImplTest {

    private SettingRepository settingRepository;
    private SettingServiceImpl settingService;

    @BeforeEach
    void setUp() {
        settingRepository = mock(SettingRepository.class);
        settingService = new SettingServiceImpl(settingRepository);
    }

    @Test
    void getSettingByKey_shouldReturnSetting_whenKeyExists() {
        Setting setting = new Setting();
        setting.setKey("testKey");
        setting.setValue("testValue");
        when(settingRepository.findSettingByKey("testKey")).thenReturn(Optional.of(setting));

        Setting result = settingService.getSettingByKey("testKey");
        assertEquals("testKey", result.getKey());
        assertEquals("testValue", result.getValue());
    }

    @Test
    void getSettingByKey_shouldThrowBadRequestException_whenKeyIsBlank() {
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            settingService.getSettingByKey(" ");
        });
        assertEquals(Strings.BadRequest, exception.getMessage());
    }

    @Test
    void getSettingByKey_shouldThrowResourceNotFoundException_whenKeyNotFound() {
        when(settingRepository.findSettingByKey("missingKey")).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            settingService.getSettingByKey("missingKey");
        });
        assertTrue(exception.getMessage().contains(Setting.EntityTitle));
    }

    @Test
    void addOrUpdate_shouldUpdateSetting_whenKeyExists() {
        Setting setting = new Setting();
        setting.setKey("updateKey");
        setting.setValue("oldValue");
        when(settingRepository.findSettingByKey("updateKey")).thenReturn(Optional.of(setting));

        settingService.addOrUpdate("updateKey", "newValue");

        ArgumentCaptor<Setting> captor = ArgumentCaptor.forClass(Setting.class);
        verify(settingRepository).save(captor.capture());
        assertEquals("newValue", captor.getValue().getValue());
    }

    @Test
    void addOrUpdate_shouldAddSetting_whenKeyDoesNotExist() {
        when(settingRepository.findSettingByKey("newKey")).thenReturn(Optional.empty());

        settingService.addOrUpdate("newKey", "newValue");

        ArgumentCaptor<Setting> captor = ArgumentCaptor.forClass(Setting.class);
        verify(settingRepository).save(captor.capture());
        assertEquals("newKey", captor.getValue().getKey());
        assertEquals("newValue", captor.getValue().getValue());
    }

    @Test
    void addOrUpdate_shouldThrowBadRequestException_whenKeyIsBlank() {
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            settingService.addOrUpdate(" ", "value");
        });
        assertEquals(Strings.BadRequest, exception.getMessage());
    }

    @Test
    void addOrUpdate_shouldThrowCustomException_onUnhandledException() {
        when(settingRepository.findSettingByKey("errKey")).thenThrow(new RuntimeException("DB error"));
        CustomException exception = assertThrows(CustomException.class, () -> {
            settingService.addOrUpdate("errKey", "value");
        });
        assertEquals(Strings.UnhandledException, exception.getMessage());
    }
}