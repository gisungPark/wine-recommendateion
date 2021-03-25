package com.ssafy.yourwine.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FlavorDTO {
    private Long flavorId;
    private String name;
    private String img;

}
