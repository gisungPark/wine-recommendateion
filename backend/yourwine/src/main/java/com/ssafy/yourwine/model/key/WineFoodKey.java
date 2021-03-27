package com.ssafy.yourwine.model.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class WineFoodKey implements Serializable{

	
	@Column(name = "food_id")
	private Long FoodId;
	
	@Column(name = "wine_id")
	private Long wineId;
}
