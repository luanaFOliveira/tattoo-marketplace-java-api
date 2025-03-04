package com.tattoo_marketplace.infra.services;

import com.tattoo_marketplace.application.dto.status.StatusRequest;
import com.tattoo_marketplace.application.dto.status.StatusResponse;
import com.tattoo_marketplace.application.services.StatusService;
import com.tattoo_marketplace.domain.entities.models.Status;
import com.tattoo_marketplace.domain.repository.StatusRepository;
import com.tattoo_marketplace.infra.mappers.StatusMapper;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

@AllArgsConstructor
@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;
    private final StatusMapper statusMapper;

    @Override
    public List<StatusResponse> findAll() {
        return statusMapper.toResponses(statusRepository.findAll());
    }

    @Override
    public Status getById(Long statusId) {
        return statusRepository.findById(statusId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find status for id=%s", statusId)));
    }

    @Override
    public Status getByName(String statusName) {
        return statusRepository.findByName(statusName)
                .orElseThrow(() -> new EntityNotFoundException("Can't find status for name=%s" + statusName));
    }

    @Override
    public StatusResponse register(StatusRequest request) {
        Status status = statusMapper.toEntity(request);

        return statusMapper.toResponse(statusRepository.save(status));
    }
}
