package com.ssafy.yourwine.model.key;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class FlavorKey implements Serializable{
	 
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "flavor_id")
	private Long flavorId;
}

