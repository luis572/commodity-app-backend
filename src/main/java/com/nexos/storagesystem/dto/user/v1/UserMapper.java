package com.nexos.storagesystem.dto.user.v1;

import com.nexos.storagesystem.dto.position.v1.PositionDto;
import com.nexos.storagesystem.dto.position.v1.PositionMapper;
import com.nexos.storagesystem.dto.position.v1.PositionRequest;
import com.nexos.storagesystem.model.Position;
import com.nexos.storagesystem.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {PositionMapper.class}
)
public abstract class UserMapper {


    public abstract UserDto toDto(User user);

    public abstract User toModel(UserRequest userRequest);

}
