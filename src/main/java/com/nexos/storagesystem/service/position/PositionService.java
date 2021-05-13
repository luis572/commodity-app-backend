package com.nexos.storagesystem.service.position;

import com.nexos.storagesystem.dto.position.v1.PositionDto;
import com.nexos.storagesystem.dto.position.v1.PositionRequest;
import com.nexos.storagesystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface PositionService {

    PositionDto findPosition(UUID uuid);

    PositionDto create(PositionRequest positionRequest);
}
