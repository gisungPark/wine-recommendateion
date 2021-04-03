package com.ssafy.yourwine.model.dto;

import lombok.Data;

@Data
public class StatisticsByPreDTO {

	private boolean writeReview;
	private int redCnt;
	private int whiteCnt;
	private int spaclingCnt;
	private int grapeCntList [];
}
