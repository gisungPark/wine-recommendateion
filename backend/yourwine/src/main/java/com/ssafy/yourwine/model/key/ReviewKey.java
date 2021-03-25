package com.ssafy.yourwine.model.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class ReviewKey implements Serializable{

	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "wine_id")
	private Long wineId;
}
