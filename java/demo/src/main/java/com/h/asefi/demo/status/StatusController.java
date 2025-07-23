package com.h.asefi.demo.status;

import com.h.asefi.demo.common.BaseController;
import com.h.asefi.demo.status.dto.Status;
import com.h.asefi.demo.status.dto.StatusResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController extends BaseController {
    public static final String GET_STATUS_URL = "/status";
    public static final String CHANGE_STATUS_URL = "/status/maintainable/{status}";

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping(value = GET_STATUS_URL)
    @Operation(summary = "This is the healthcheck and will return the availability of the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponseDTO.class))})
    })
    public ResponseEntity<StatusResponseDTO> getStatus() {
        StatusResponseDTO status = statusService.getStatus();
        return new ResponseEntity<>(status, status.status() == Status.OK ? HttpStatus.OK : HttpStatus.SERVICE_UNAVAILABLE);
    }

    @PostMapping(value = CHANGE_STATUS_URL)
    @Operation(summary = "Changes maintainable status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<Void> changeMaintainableStatus(@PathVariable boolean status) {
        statusService.setMaintenanceMode(status);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
