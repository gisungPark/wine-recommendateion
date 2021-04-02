package com.ssafy.yourwine.model.dto;

import lombok.Data;

@Data
public class TasteFilterDTO {
	private int startAcidity;
	private int startSweet;
	private int startTannin;
	private int startBody;
	private int endAcidity;
	private int endSweet;
	private int endTannin;
	private int endBody;
}
