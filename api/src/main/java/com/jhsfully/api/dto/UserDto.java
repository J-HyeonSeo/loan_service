package com.jhsfully.api.dto;

import lombok.*;

public class UserDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserReceiveRequest{

        private long userIncomeAmount;
        private String userName;
        private String userRegistrationNumber;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class UserResponse extends Response{
        private UserData data;
    }

    @Data
    @Builder
    public static class UserData{
        private String userKey;
        private String userRegistrationNumber;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class UserReceiveResponse extends Response{
        private UserReceiveData data;
    }

    @Data
    @AllArgsConstructor
    public static class UserReceiveData{
        private String userKey;

    }

}
