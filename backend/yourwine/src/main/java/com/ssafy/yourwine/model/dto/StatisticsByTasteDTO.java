package com.ssafy.yourwine.model.dto;

import lombok.Data;

@Data
public class StatisticsByTasteDTO {

	private boolean writeReview;
	private double sweetAvg;
	private double tanninAvg;
	private double bodyAvg;
	private double acidityAvg;
	private int flavorCntList[];
}
