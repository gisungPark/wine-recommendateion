package com.ssafy.yourwine.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "batch_data")
public class BatchData {

	@Id
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "recolist")
	private String recoList;
	
	@Column(name = "similar_user_high")
	private String similarUserHige;
	
	@Column(name = "similar_user_low")
	private String similarUserLow;
	
}
