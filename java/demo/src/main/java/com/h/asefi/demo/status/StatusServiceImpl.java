package com.h.asefi.demo.status;

import com.h.asefi.demo.setting.Setting;
import com.h.asefi.demo.setting.SettingKey;
import com.h.asefi.demo.setting.SettingService;
import com.h.asefi.demo.status.dto.Status;
import com.h.asefi.demo.status.dto.StatusResponseDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {

    private final SettingService settingService;

    public StatusServiceImpl(SettingService settingService) {
        this.settingService = settingService;
    }

    @Override
    public StatusResponseDTO getStatus() {

        Setting setting = settingService.getSettingByKey(SettingKey.MAINTENANCE_STATUS);
        if (setting != null && setting.getValue() != null) {
            if (setting.getValue().equalsIgnoreCase("true")) {
                return new StatusResponseDTO(Status.MAINTENANCE, "Service is in maintenance mode");
            }
        }
        return new StatusResponseDTO(Status.OK, "");
    }

    @Override
    @Cacheable(value = "isMaintenanceCache", key = "'maintenanceStatus'")
    public boolean isMaintenanceMode() {
        StatusResponseDTO status = getStatus();
        return status.status().equals(Status.MAINTENANCE);
    }

    @Override
    @CacheEvict(value = "isMaintenanceCache", key = "'maintenanceStatus'")
    public void setMaintenanceMode(boolean status) {
        settingService.addOrUpdate(SettingKey.MAINTENANCE_STATUS, String.valueOf(status));
    }

}
