package com.jhsfully.api.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Response {

    private String responseCode;
    private String responseMessage;


}
