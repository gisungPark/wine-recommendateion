package com.ssafy.yourwine.model.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class WineFlavorKey implements Serializable{

	
	@Column(name = "wine_id")
	private Long wineId;
	
	@Column(name = "flavor_id")
	private Long flavorId;
}
