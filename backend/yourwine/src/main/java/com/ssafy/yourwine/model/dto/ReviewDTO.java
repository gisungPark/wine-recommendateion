package com.ssafy.yourwine.model.dto;

import java.util.Date;

import com.ssafy.yourwine.model.entity.Review;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewDTO {

	private Long wineId;
	private String wineName;
	private String nickname;
	private String userImg;
	private String contents;
	private float point;
	private int cost;
	private Date time;
	
	public ReviewDTO (Review review) {
		this.wineId = review.getWine().getWineId();
		this.wineName = review.getWine().getKname();
		this.nickname = review.getUser().getNickname();
		this.userImg = review.getUser().getImg();
		this.contents = review.getContents();
		this.point = review.getPoint();
		this.cost = review.getCost();
		this.time = review.getTime();
	}
}
