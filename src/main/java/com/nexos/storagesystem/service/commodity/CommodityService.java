package com.nexos.storagesystem.service.commodity;

import com.nexos.storagesystem.dto.commodity.v1.CommodityDto;
import com.nexos.storagesystem.dto.commodity.v1.CommodityRequest;
import com.nexos.storagesystem.dto.position.v1.PositionDto;
import com.nexos.storagesystem.dto.position.v1.PositionRequest;
import com.nexos.storagesystem.model.Commodity;
import com.nexos.storagesystem.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface CommodityService {

     CommodityDto findCommodity(UUID uuid);

     CommodityDto create(CommodityRequest commodityRequest);

     CommodityDto update(UUID uuid,CommodityRequest commodityRequest);

     void delete (UUID userUuid,UUID uuid);

     Page<CommodityDto> findAll(Pageable pageable, Specification<Commodity> specs);
}
