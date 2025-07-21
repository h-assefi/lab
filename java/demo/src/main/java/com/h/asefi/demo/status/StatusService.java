package com.h.asefi.demo.status;

import com.h.asefi.demo.status.dto.StatusResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface StatusService {
    StatusResponseDTO getStatus();

    boolean isMaintenanceMode();
    
    void setMaintenanceMode(boolean status);

}
