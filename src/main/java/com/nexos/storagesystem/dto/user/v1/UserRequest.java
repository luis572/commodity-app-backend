package com.nexos.storagesystem.dto.user.v1;

import com.nexos.storagesystem.dto.position.v1.PositionDto;

import java.util.Date;
import java.util.UUID;

public class UserRequest {

    public UUID uuid;

    public String name;

    public UUID positionUuid;

    public String lastName;

    public int age;

    public UserRequest() {}

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

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

    public UUID getPositionUuid() {
        return positionUuid;
    }

    public void setPositionUuid(UUID positionUuid) {
        this.positionUuid = positionUuid;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
