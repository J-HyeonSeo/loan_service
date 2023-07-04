package com.jhsfully.api.controller;

import com.jhsfully.api.dto.UserDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserInformationController {

    UserDto.UserResponse getUserInfo(@PathVariable String userKey);
    UserDto.UserReceiveResponse receiveUserInfo(@RequestBody UserDto.UserReceiveRequest request);

}
