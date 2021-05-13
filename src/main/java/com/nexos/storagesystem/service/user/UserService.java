package com.nexos.storagesystem.service.user;

import com.nexos.storagesystem.dto.user.v1.UserDto;
import com.nexos.storagesystem.dto.user.v1.UserRequest;
import java.util.UUID;

public interface UserService {

    UserDto findUser(UUID uuid);

    UserDto create(UserRequest userRequest);

}
