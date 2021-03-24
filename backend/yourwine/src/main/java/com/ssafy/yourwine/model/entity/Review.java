package com.ssafy.yourwine.model.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;

import lombok.Data;

@Data
public class Review {

	
	private String contents;
	private float point;
	private int cost;
	private Timestamp time;
}
