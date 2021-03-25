package com.ssafy.yourwine.model.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.ssafy.yourwine.model.key.ReviewKey;

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
	private ReviewKey reviewId = new ReviewKey();

	@Column(nullable = false)
	private String contents;

	@Column(nullable = false)
	private float point;

	@Column(nullable = false)
	private int cost;

	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
	private Date time;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "wine_id", insertable = false, updatable = false)
	private Wine wine;
}
