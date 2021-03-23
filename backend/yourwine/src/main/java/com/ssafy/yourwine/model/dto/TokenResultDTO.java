package com.ssafy.yourwine.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
public class TokenResultDTO {
    @ApiModelProperty(value="token")
    private String token;

    @ApiModelProperty(value="nickname")
    private String nickname;

    @ApiModelProperty(value="correct")
    private Integer code;
}

