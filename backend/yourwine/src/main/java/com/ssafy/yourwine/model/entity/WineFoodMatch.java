package com.ssafy.yourwine.model.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ssafy.yourwine.model.key.WineFoodKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wine_food_match")
public class WineFoodMatch {
	
	@EmbeddedId
	@Column(name = "winefood_id", unique = true)
	private WineFoodKey wineFoodId = new WineFoodKey(); 
	
	@ManyToOne
	@JoinColumn(name = "wine_id", insertable=false, updatable=false)
	private Wine wine;
	
	@ManyToOne
	@JoinColumn(name = "food_id", insertable=false, updatable=false)
	private Food food;
	
}
