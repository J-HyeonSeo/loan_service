package com.jhsfully.api.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GenerateUserKey {

    public String generateKey(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
