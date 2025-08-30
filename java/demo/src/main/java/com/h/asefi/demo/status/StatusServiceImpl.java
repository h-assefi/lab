package com.h.asefi.demo.status;

import com.h.asefi.demo.setting.Setting;
import com.h.asefi.demo.setting.SettingKey;
import com.h.asefi.demo.setting.SettingService;
import com.h.asefi.demo.status.dto.Status;
import com.h.asefi.demo.status.dto.StatusResponseDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link StatusService} interface.
 * <p>
 * This service provides methods to check and update the application's
 * maintenance mode status.
 * It interacts with the {@link SettingService} to persist and retrieve the
 * maintenance status,
 * and uses caching to optimize status checks.
 * </p>
 *
 * <ul>
 * <li>{@link #getStatus()} - Returns the current status of the application (OK
 * or MAINTENANCE).</li>
 * <li>{@link #isMaintenanceMode()} - Checks if the application is in
 * maintenance mode (cached).</li>
 * <li>{@link #setMaintenanceMode(boolean)} - Sets the maintenance mode status
 * and evicts the cache.</li>
 * </ul>
 *
 * <b>Caching:</b>
 * The {@code isMaintenanceMode} result is cached for performance. The cache is
 * evicted when {@code setMaintenanceMode} is called.
 */
@Service
public class StatusServiceImpl implements StatusService {

    private final SettingService settingService;

    /**
     * Constructs a new StatusServiceImpl with the provided SettingService.
     *
     * @param settingService the service used to manage application settings
     */
    public StatusServiceImpl(SettingService settingService) {
        this.settingService = settingService;
    }

    /**
     * Retrieves the current status of the application.
     * If the MAINTENANCE_STATUS setting is set to "true", returns MAINTENANCE
     * status.
     * Otherwise, returns OK status.
     *
     * @return StatusResponseDTO containing the current status and an optional
     *         message
     */
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

    /**
     * Checks if the application is currently in maintenance mode.
     * The result is cached for performance.
     *
     * @return true if in maintenance mode, false otherwise
     */
    @Override
    @Cacheable(value = "isMaintenanceCache", key = "'maintenanceStatus'")
    public boolean isMaintenanceMode() {
        StatusResponseDTO status = getStatus();
        return status.status().equals(Status.MAINTENANCE);
    }

    /**
     * Sets the maintenance mode status for the application.
     * Updates the MAINTENANCE_STATUS setting and evicts the related cache entry.
     *
     * @param status true to enable maintenance mode, false to disable
     */
    @Override
    @CacheEvict(value = "isMaintenanceCache", key = "'maintenanceStatus'")
    public void setMaintenanceMode(boolean status) {
        settingService.addOrUpdate(SettingKey.MAINTENANCE_STATUS, String.valueOf(status));
    }

}
