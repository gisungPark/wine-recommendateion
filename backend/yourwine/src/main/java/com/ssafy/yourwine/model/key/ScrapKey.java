package com.ssafy.yourwine.model.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Embeddable
@Data
@Table( uniqueConstraints = {@UniqueConstraint(
        name = "scrapUniqueKey", columnNames = {"user_id", "wine_id"}
)})
public class ScrapKey implements Serializable{
	 
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "wine_id")
	private int wineId;
}

