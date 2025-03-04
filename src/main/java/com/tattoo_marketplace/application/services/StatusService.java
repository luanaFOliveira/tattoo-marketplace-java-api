package com.tattoo_marketplace.application.services;

import com.tattoo_marketplace.application.dto.status.StatusRequest;
import com.tattoo_marketplace.application.dto.status.StatusResponse;
import com.tattoo_marketplace.domain.entities.models.Status;
import java.util.List;

public interface StatusService {

    Status getById(Long id);

    Status getByName(String statusName);

    List<StatusResponse> findAll();

    StatusResponse register(StatusRequest request);

}
