package com.ssafy.yourwine.model.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ssafy.yourwine.model.key.FlavorKey;
import com.ssafy.yourwine.model.key.WineFlavorKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wine_flavor")
public class WineFlavor {

	 @EmbeddedId
	 @Column(name = "wineflavor_id", unique = true)
	 private WineFlavorKey wineFlavorId = new WineFlavorKey();
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "wine_id", insertable=false, updatable=false)
	private Wine wine;
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "flavor_id", insertable=false, updatable=false)
	private Flavor flavor;
	
}
