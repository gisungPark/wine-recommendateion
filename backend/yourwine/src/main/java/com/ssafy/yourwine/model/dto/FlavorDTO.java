package com.ssafy.yourwine.model.dto;

import com.ssafy.yourwine.model.entity.WineFlavor;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FlavorDTO {
    private Long flavorId;
    private String name;

    public FlavorDTO (WineFlavor wineFlavor) {
    	this.flavorId = wineFlavor.getFlavor().getFlavorId();
    	this.name = wineFlavor.getFlavor().getName();
    }
}
