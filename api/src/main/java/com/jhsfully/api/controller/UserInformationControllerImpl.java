package com.jhsfully.api.controller;

import com.jhsfully.api.dto.UserDto;
import com.jhsfully.api.service.UserInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fintech/v1/user")
public class UserInformationControllerImpl implements UserInformationController{

    private final UserInformationService userInformationService;

    @GetMapping("/private-info/{userKey}")
    public UserDto.UserResponse getUserInfo(@PathVariable String userKey){
        return userInformationService.getUserInfo(userKey);
    }

    @PostMapping("/information")
    public UserDto.UserReceiveResponse receiveUserInfo(
            @RequestBody UserDto.UserReceiveRequest request){
        return userInformationService.receiveUserInfo(request);
    }

}
