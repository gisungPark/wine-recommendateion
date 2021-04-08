package com.ssafy.yourwine.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class StatisticsBySimilarDTO {

	private boolean writeReview;
	private boolean batchData;
	private List<WineDTO> similarHighList;
	private List<WineDTO> similarLowList;
}
