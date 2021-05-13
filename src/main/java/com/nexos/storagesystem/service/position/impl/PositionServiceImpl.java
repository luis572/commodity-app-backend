package com.nexos.storagesystem.service.position.impl;

import com.nexos.storagesystem.dto.position.v1.PositionDto;
import com.nexos.storagesystem.dto.position.v1.PositionMapper;
import com.nexos.storagesystem.dto.position.v1.PositionRequest;
import com.nexos.storagesystem.model.Position;
import com.nexos.storagesystem.repository.position.PositionRepository;
import com.nexos.storagesystem.service.position.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.UUID;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private PositionMapper positionMapper;

    @Override
    public PositionDto findPosition(UUID uuid) {
        return positionMapper.toDto(findPositionModel(uuid));
    }

    @Override
    public PositionDto create(PositionRequest positionRequest) {
        return positionMapper.toDto(positionRepository.save(positionMapper.toModel(positionRequest)));
    }

    private Position findPositionModel(UUID uuid) {
        return positionRepository.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
