package com.tattoo_marketplace.application.controllers;

import com.tattoo_marketplace.application.dto.status.StatusResponse;
import com.tattoo_marketplace.application.dto.status.StatusRequest;
import com.tattoo_marketplace.application.services.StatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Tag(name = "Status Controller")
@RequestMapping("/status")
@RestController
public class StatusController {

    private final StatusService statusService;

    @GetMapping
    @Operation(summary = "Get all status", description = "Get all status.")
    public ResponseEntity<List<StatusResponse>> getStatus() {
        List<StatusResponse> status = statusService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @PostMapping("/register")
    @Operation(summary = "Create a new status", description = "Creates a new status")
    public ResponseEntity<StatusResponse> register(@Valid @RequestBody StatusRequest request) {

        StatusResponse registeredStatus = statusService.register(request);

        return ResponseEntity.ok(registeredStatus);
    }

}
