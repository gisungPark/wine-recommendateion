package com.ssafy.yourwine.model.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssafy.yourwine.model.key.ScrapKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Scrap {
	
	@EmbeddedId
	@Column(name = "scrap_id", unique = true)
	private ScrapKey id = new ScrapKey();
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable=false, updatable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "wine_id", insertable=false, updatable=false)
	private Wine wine;
}
