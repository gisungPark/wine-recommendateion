package com.ssafy.yourwine.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class AvgRecoDTO {

	private boolean writeReview;
	private boolean batchData;
	private List<WineDTO> wineDtoList;
	
}
