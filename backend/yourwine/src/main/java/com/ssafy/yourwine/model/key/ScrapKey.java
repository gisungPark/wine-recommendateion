package com.ssafy.yourwine.model.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Embeddable
@Data
public class ScrapKey implements Serializable{
	 
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "wine_id")
	private Long wineId;
}

