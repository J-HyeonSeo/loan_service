package com.jhsfully.api.service.impl;

import com.jhsfully.api.dto.UserDto;
import com.jhsfully.api.exception.UserException;
import com.jhsfully.api.util.GenerateUserKey;
import com.jhsfully.domain.domain.UserInfo;
import com.jhsfully.domain.repository.UserInfoRepository;
import com.jhsfully.api.service.UserInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInformationServiceImpl implements UserInformationService {

    private final UserInfoRepository userInfoRepository;
    private final GenerateUserKey generateUserKey;

    @Override
    public UserDto.UserResponse getUserInfo(String userKey) {
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findByUserKey(userKey);

        if(!optionalUserInfo.isPresent()){
            throw new UserException("해당 유저를 찾을 없습니다.");
        }

        UserInfo userInfo = optionalUserInfo.get();

        UserDto.UserResponse response = new UserDto.UserResponse();

        response.setData(UserDto.UserData.builder()
                        .userKey(userInfo.getUserKey())
                        .userRegistrationNumber(userInfo.getUserRegistrationNumber())
                .build());
        response.setResponseCode("200");
        response.setResponseMessage("Success :)");
        return response;
    }

    @Override
    public UserDto.UserReceiveResponse receiveUserInfo(UserDto.UserReceiveRequest request) {

        String userKey = generateUserKey.generateKey();

        UserInfo userInfo = UserInfo.builder()
                .userKey(userKey)
                .userName(request.getUserName())
                .userRegistrationNumber(request.getUserRegistrationNumber())
                .userIncomingAmount(request.getUserIncomeAmount())
                .build();

        //암호화 수행해야함.
        userInfoRepository.save(userInfo);

        UserDto.UserReceiveResponse response = new UserDto.UserReceiveResponse();

        response.setData(new UserDto.UserReceiveData(userKey));
        response.setResponseCode("200");
        response.setResponseMessage("Success *_*!");

        return response;
    }
}
