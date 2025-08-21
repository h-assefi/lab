package com.h.asefi.demo.status;

import com.h.asefi.demo.setting.Setting;
import com.h.asefi.demo.setting.SettingKey;
import com.h.asefi.demo.setting.SettingService;
import com.h.asefi.demo.status.dto.Status;
import com.h.asefi.demo.status.dto.StatusResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StatusServiceImplTest {

    private SettingService settingService;
    private StatusServiceImpl statusService;

    @BeforeEach
    void setUp() {
        settingService = mock(SettingService.class);
        statusService = new StatusServiceImpl(settingService);
    }

    @Test
    void getStatus_shouldReturnMaintenance_whenSettingIsTrue() {
        Setting setting = new Setting();
        setting.setKey(SettingKey.MaintenanceStatus);
        setting.setValue("true");
        when(settingService.getSettingByKey(SettingKey.MaintenanceStatus)).thenReturn(setting);

        StatusResponseDTO response = statusService.getStatus();
        assertEquals(Status.MAINTENANCE, response.status());
        assertEquals("Service is in maintenance mode", response.message());
    }

    @Test
    void getStatus_shouldReturnOk_whenSettingIsFalse() {
        Setting setting = new Setting();
        setting.setKey(SettingKey.MaintenanceStatus);
        setting.setValue("false");
        when(settingService.getSettingByKey(SettingKey.MaintenanceStatus)).thenReturn(setting);

        StatusResponseDTO response = statusService.getStatus();
        assertEquals(Status.OK, response.status());
        assertEquals("", response.message());
    }

    @Test
    void getStatus_shouldReturnOk_whenSettingIsNull() {
        when(settingService.getSettingByKey(SettingKey.MaintenanceStatus)).thenReturn(null);

        StatusResponseDTO response = statusService.getStatus();
        assertEquals(Status.OK, response.status());
        assertEquals("", response.message());
    }

    @Test
    void getStatus_shouldReturnOk_whenSettingValueIsNull() {
        Setting setting = new Setting();
        setting.setKey(SettingKey.MaintenanceStatus);
        setting.setValue(null);
        when(settingService.getSettingByKey(SettingKey.MaintenanceStatus)).thenReturn(setting);

        StatusResponseDTO response = statusService.getStatus();
        assertEquals(Status.OK, response.status());
        assertEquals("", response.message());
    }

    @Test
    void isMaintenanceMode_shouldReturnTrue_whenStatusIsMaintenance() {
        Setting setting = new Setting();
        setting.setKey(SettingKey.MaintenanceStatus);
        setting.setValue("true");
        when(settingService.getSettingByKey(SettingKey.MaintenanceStatus)).thenReturn(setting);

        assertTrue(statusService.isMaintenanceMode());
    }

    @Test
    void isMaintenanceMode_shouldReturnFalse_whenStatusIsOk() {
        Setting setting = new Setting();
        setting.setKey(SettingKey.MaintenanceStatus);
        setting.setValue("false");
        when(settingService.getSettingByKey(SettingKey.MaintenanceStatus)).thenReturn(setting);

        assertFalse(statusService.isMaintenanceMode());
    }

    @Test
    void setMaintenanceMode_shouldUpdateSetting() {
        statusService.setMaintenanceMode(true);
        verify(settingService).addOrUpdate(SettingKey.MaintenanceStatus, "true");

        statusService.setMaintenanceMode(false);
        verify(settingService).addOrUpdate(SettingKey.MaintenanceStatus, "false");
    }
}