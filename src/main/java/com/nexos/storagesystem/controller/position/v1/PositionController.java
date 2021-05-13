package com.nexos.storagesystem.controller.position.v1;

import com.nexos.storagesystem.dto.position.v1.PositionDto;
import com.nexos.storagesystem.dto.position.v1.PositionRequest;
import com.nexos.storagesystem.service.position.PositionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController("position.v1.crud")
@RequestMapping("v1/positions")
@Api(tags = {"positions", "crud"})
@Validated
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping("/{uuid}")
    public ResponseEntity<PositionDto> show(
            @PathVariable UUID uuid
    ) {
        return new ResponseEntity<>(positionService.findPosition(uuid), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PositionDto> create(
            @RequestBody PositionRequest positionRequest
    ) {
        return new ResponseEntity<>(positionService.create(positionRequest), HttpStatus.OK);
    }

}
