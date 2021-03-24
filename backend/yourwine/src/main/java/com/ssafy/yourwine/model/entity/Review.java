package com.ssafy.yourwine.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssafy.yourwine.model.key.ReviewKey;
import com.ssafy.yourwine.model.key.ScrapKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Review {

	
	@EmbeddedId
	@Column(name = "review_id", unique = true)
	private ReviewKey reviewKey = new ReviewKey();
	
	private String contents;
	private float point;
	private int cost;
	//private Timestamp time;
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable=false, updatable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "wine_id", insertable=false, updatable=false)
	private Wine wine;
}
