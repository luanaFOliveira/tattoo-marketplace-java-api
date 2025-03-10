package com.tattoo_marketplace.infra.mappers;

import com.tattoo_marketplace.application.dto.status.StatusRequest;
import com.tattoo_marketplace.application.dto.status.StatusResponse;
import com.tattoo_marketplace.domain.entities.models.Status;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = "spring", 
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StatusMapper {

    Status toEntity(StatusRequest request);

    StatusResponse toResponse(Status status);

    List<StatusResponse> toResponses(List<Status> statusList);

    void updateStatus(@MappingTarget Status status, StatusRequest request);
}
