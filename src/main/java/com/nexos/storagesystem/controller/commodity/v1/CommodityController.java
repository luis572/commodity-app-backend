package com.nexos.storagesystem.controller.commodity.v1;

import com.nexos.storagesystem.dto.commodity.v1.CommodityDto;
import com.nexos.storagesystem.dto.commodity.v1.CommodityRequest;
import com.nexos.storagesystem.model.Commodity;
import com.nexos.storagesystem.service.commodity.CommodityService;
import io.swagger.annotations.Api;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@RestController("commodity.v1.crud")
@RequestMapping("v1/commodities")
@Api(tags = {"commodities", "crud"})
@Validated
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @GetMapping
    public Page<CommodityDto> index(Pageable pageable, @SearchSpec Specification<Commodity> specs) {
        return commodityService.findAll(pageable, specs);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CommodityDto> show(
            @PathVariable UUID uuid
    ) {
        return new ResponseEntity<>(commodityService.findCommodity(uuid), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommodityDto> create(
            @RequestBody CommodityRequest commodityRequest
    ) {
        return new ResponseEntity<>(commodityService.create(commodityRequest), HttpStatus.OK);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<CommodityDto> update(
            @PathVariable UUID uuid,
            @RequestBody CommodityRequest commodityRequest
    ) {
        return new ResponseEntity<>(commodityService.update(uuid, commodityRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> delete(
            @PathVariable UUID uuid,
            @RequestHeader("user-uuid") UUID  userUuid
    ) {
        commodityService.delete(userUuid,uuid);
        return new ResponseEntity<>("Item " + uuid.toString() + " removed successfully.", HttpStatus.OK);
    }

}
