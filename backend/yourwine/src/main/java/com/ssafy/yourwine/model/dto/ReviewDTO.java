package com.ssafy.yourwine.model.dto;

import java.sql.Timestamp;
import java.util.Date;

import com.ssafy.yourwine.model.entity.Grape;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ReviewDTO {

	private Long wineId;
	private String contents;
	private float point;
	private int cost;
	private Date time;
}
