package com.h.asefi.demo.setting;

import com.h.asefi.demo.common.Strings;
import com.h.asefi.demo.common.exception.exceptionTypes.BadRequestException;
import com.h.asefi.demo.common.exception.exceptionTypes.CustomException;
import com.h.asefi.demo.common.exception.exceptionTypes.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SettingServiceImpl implements SettingService {
    private final SettingRepository settingRepository;

    public SettingServiceImpl(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @Override
    public Setting getSettingByKey(String key) throws ResourceNotFoundException, BadRequestException {
        if (!StringUtils.hasText(key))
            throw new BadRequestException(Strings.BadRequest);

        return settingRepository.findSettingByKey(key)
                .orElseThrow(() -> new ResourceNotFoundException(Strings.NotFound(Setting.EntityTitle)));
    }

    @Override
    public void addOrUpdate(String key, String value) {
        if (!StringUtils.hasText(key))
            throw new BadRequestException(Strings.BadRequest);

        try {
            updateSetting(key, value);
        } catch (ResourceNotFoundException ignored) {
            addSetting(key, value);
        } catch (BadRequestException | CustomException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new CustomException(Strings.UnhandledException, exception);
        }
    }

    private void updateSetting(String key, String value) {
        Setting setting = getSettingByKey(key);
        setting.setValue(value);
        settingRepository.save(setting);
    }

    private void addSetting(String key, String value) {
        Setting setting = new Setting();
        setting.setValue(value);
        setting.setKey(key);
        try {
            settingRepository.save(setting);
        } catch (Exception exception) {
            throw new CustomException(Strings.UnhandledException, exception);
        }
    }
}
