package com.ssafy.yourwine.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.ToString;

@ToString
@Entity
public class Grape {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "grape_id")
	private int grapeId;
	private String kname;
	private String ename;
	private String detail;
	
}
