package com.ssafy.yourwine.model.dto;

import java.util.List;

import com.ssafy.yourwine.model.entity.Food;
import com.ssafy.yourwine.model.entity.Review;

import lombok.Data;

@Data
public class WineDetailDTO {
	
	private WineDTO wineDto;
	private boolean isScrap;
	private boolean isWriteReview;
	private List<ReviewDTO> reviewList;
	private List<FoodDTO> foodList;
	
}
