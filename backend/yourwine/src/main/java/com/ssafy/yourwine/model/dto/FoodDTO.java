package com.ssafy.yourwine.model.dto;

import com.ssafy.yourwine.model.entity.Food;
import com.ssafy.yourwine.model.entity.WineFoodMatch;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodDTO {
	
	private Long foodId;
    private String name;
    private String detail;
    
    
	public FoodDTO(WineFoodMatch wineFoodMatch) {
		this.foodId = wineFoodMatch.getFood().getFoodId();
		this.name = wineFoodMatch.getFood().getName();
		this.detail = wineFoodMatch.getFood().getDetail();
	}

    
}
