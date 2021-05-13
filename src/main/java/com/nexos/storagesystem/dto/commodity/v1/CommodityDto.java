package com.nexos.storagesystem.dto.commodity.v1;

import com.nexos.storagesystem.dto.user.v1.UserDto;
import com.nexos.storagesystem.model.User;
import java.util.Date;
import java.util.UUID;

public class CommodityDto {

    public UUID uuid = null;

    public String name = null;

    public Integer amount = null;

    public UUID userUuid = null;

    public Date createdAt = null;

    public Date lastModifiedAt = null;

    public CommodityDto() {}

    public CommodityDto(UUID uuid, String name, Integer amount, UUID userUuid, Date createdAt, Date lastModifiedAt) {
        this.uuid = uuid;
        this.name = name;
        this.amount = amount;
        this.userUuid = userUuid;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public UUID getUserUuid() { return userUuid; }

    public void setUserUuid(UUID userUuid) { this.userUuid = userUuid; }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Date lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }
}
