package com.ssafy.yourwine.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SignInDTO {
    private String email;
    private String password;
}
