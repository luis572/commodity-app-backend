package com.nexos.storagesystem.service.commodity.impl;

import com.nexos.storagesystem.dto.commodity.v1.CommodityDto;
import com.nexos.storagesystem.dto.commodity.v1.CommodityMapper;
import com.nexos.storagesystem.dto.commodity.v1.CommodityRequest;
import com.nexos.storagesystem.model.Commodity;
import com.nexos.storagesystem.repository.commodity.CommodityRepository;
import com.nexos.storagesystem.repository.user.UserRepository;
import com.nexos.storagesystem.service.commodity.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    CommodityRepository commodityRepository;

    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    UserRepository userRepository;

    @Override
    public CommodityDto findCommodity(UUID uuid) {
        return commodityMapper.toDto(findPositionModel(uuid));
    }

    @Override
    public CommodityDto create(CommodityRequest commodityRequest) {
        if(commodityRepository.existsByName(commodityRequest.name))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"A commodity with this name already exists.");
        Commodity commodity = commodityMapper.toModel(commodityRequest);
        UUID userUuid = commodityRequest.userUuid;
        if(userUuid != null && userRepository.existsById(userUuid))
            commodity.user = userRepository.getOne(userUuid);
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User does not exist");
        return commodityMapper.toDto(commodityRepository.save(commodity));

    }

    @Override
    public CommodityDto update(UUID uuid, CommodityRequest commodityRequest) {
        Commodity commodity = findPositionModel(uuid);
        Commodity commodityUpdated = commodityMapper.updateModel(commodityRequest,commodity);
        return commodityMapper.toDto(commodityRepository.save(commodityUpdated));
    }

    @Override
    public void delete(UUID userUuid,UUID uuid) {
        Commodity commodity = findPositionModel(uuid);
        if(commodity.user.uuid.equals(userUuid))
            commodityRepository.delete(commodity);
        else
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @Override
    public Page<CommodityDto> findAll(Pageable pageable, Specification<Commodity> specs) {
        return commodityRepository.findAll(specs,pageable).map(
                commodity -> commodityMapper.toDto(commodity)
        );
    }

    private Commodity findPositionModel(UUID uuid) {
        return commodityRepository.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
