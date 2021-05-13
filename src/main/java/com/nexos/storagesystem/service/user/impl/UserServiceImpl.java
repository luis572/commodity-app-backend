package com.nexos.storagesystem.service.user.impl;

import com.nexos.storagesystem.dto.user.v1.UserDto;
import com.nexos.storagesystem.dto.user.v1.UserMapper;
import com.nexos.storagesystem.dto.user.v1.UserRequest;
import com.nexos.storagesystem.model.User;
import com.nexos.storagesystem.repository.position.PositionRepository;
import com.nexos.storagesystem.repository.user.UserRepository;
import com.nexos.storagesystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto findUser(UUID uuid) {
        return userMapper.toDto(findCommodityModel(uuid));
    }

    @Override
    public UserDto create(UserRequest userRequest) {
        User userModel = userMapper.toModel(userRequest);
        UUID positionUuid = userRequest.positionUuid;
        if(positionUuid != null && positionRepository.existsById(positionUuid))
            userModel.position = positionRepository.getOne(positionUuid);
        return userMapper.toDto(userRepository.save(userModel));
    }

    private User findCommodityModel(UUID uuid) {
        return userRepository.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
