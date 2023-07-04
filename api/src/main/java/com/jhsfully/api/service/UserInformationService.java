package com.jhsfully.api.service;

import com.jhsfully.api.dto.UserDto;

public interface UserInformationService {

    UserDto.UserResponse getUserInfo(String userKey);
    UserDto.UserReceiveResponse receiveUserInfo(UserDto.UserReceiveRequest request);

}
