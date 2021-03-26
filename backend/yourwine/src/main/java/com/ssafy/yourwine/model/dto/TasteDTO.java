package com.ssafy.yourwine.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TasteDTO {
    private Integer sweet;
    private Integer body;
    private Integer tannin;
    private Integer acidity;

}
