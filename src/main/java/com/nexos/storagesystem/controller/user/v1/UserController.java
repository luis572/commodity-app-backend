package com.nexos.storagesystem.controller.user.v1;

import com.nexos.storagesystem.dto.user.v1.UserDto;
import com.nexos.storagesystem.dto.user.v1.UserRequest;
import com.nexos.storagesystem.service.user.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController("users.v1.crud")
@RequestMapping("v1/users")
@Api(tags = {"users", "crud"})
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{uuid}")
    public ResponseEntity<UserDto> show(
            @PathVariable UUID uuid
    ) {
        return new ResponseEntity<>(userService.findUser(uuid), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(
            @RequestBody UserRequest userRequest
    ) {
        return new ResponseEntity<>(userService.create(userRequest), HttpStatus.OK);
    }

}
