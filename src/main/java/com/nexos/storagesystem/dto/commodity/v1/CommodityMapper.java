package com.nexos.storagesystem.dto.commodity.v1;

import com.nexos.storagesystem.dto.user.v1.UserMapper;
import com.nexos.storagesystem.model.Commodity;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CommodityMapper {


    @Mapping(source = "commodity.user.uuid", target = "userUuid")
    CommodityDto toDto(Commodity commodity);

    Commodity toModel(CommodityRequest commodityRequest);

    Commodity updateModel(CommodityRequest commodityRequest, @MappingTarget Commodity commodity);

}
