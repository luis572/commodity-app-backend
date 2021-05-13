package com.nexos.storagesystem.dto.commodity.v1;

import com.nexos.storagesystem.dto.user.v1.UserDto;
import com.nexos.storagesystem.model.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.UUID;

public class CommodityRequest {

    public UUID uuid = null;

    public String name = null;

    public Integer amount = null;

    public UUID userUuid = null;

    public CommodityRequest() {}

    public CommodityRequest(UUID uuid, String name, Integer amount, UUID userUuid) {
        this.uuid = uuid;
        this.name = name;
        this.amount = amount;
        this.userUuid = userUuid;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public UUID getUserUuid() { return userUuid; }

    public void setUserUuid(UUID userUuid) { this.userUuid = userUuid; }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
