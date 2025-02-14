package com.tattoo_marketplace.infra.mappers;

import com.tattoo_marketplace.application.dto.user.RegisterUserRequest;
import com.tattoo_marketplace.application.dto.user.RegisterUserResponse;
import com.tattoo_marketplace.application.dto.user.UpdateUserRequest;
import com.tattoo_marketplace.application.dto.user.UserResponse;
import com.tattoo_marketplace.domain.entities.models.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "gender", ignore = true)
    User fromRegisterRequest(RegisterUserRequest request);

    // @Mapping(target = "password", ignore = true)
    // @Mapping(target = "gender", ignore = true)
    // User fromUpdateRequest(UpdateUserRequest request);
    void updateUserPartial(@MappingTarget User user, UpdateUserRequest request);

    RegisterUserResponse toRegisterResponse(User user);

    UserResponse toResponse(User user);

    List<UserResponse> toResponses(List<User> user);



}
