package com.ssafy.yourwine.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDTO {
    private String email;
    private String password;
    private String nickname;
    private String img;
    private Integer provider;
}
