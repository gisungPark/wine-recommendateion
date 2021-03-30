package com.ssafy.yourwine.model.dto;

import com.ssafy.yourwine.model.entity.Grape;
import com.ssafy.yourwine.model.entity.Wine;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WineDTO {

	private Long wineId;
	private String kname;
	private String ename;
	private String alcohol;
	private String type;
	private String temper;
	private float avg;
	private int price;
	private String year;
	private String detail;
	private String shop;
	private int sweet;
	private int acidity;
	private int body;
	private int tannin;
	private String area;
	private Grape grape;
	
	
	public WineDTO(Wine wine) {
		this.wineId = wine.getWineId();
		this.kname = wine.getKname();
		this.ename = wine.getEname();
		this.alcohol = wine.getAlcohol();
		this.type = wine.getType();
		this.temper = wine.getTemper();
		this.avg = wine.getAvg();
		this.price = wine.getPrice();
		this.year = wine.getYear();
		this.detail = wine.getDetail();
		this.shop = wine.getShop();
		this.sweet = wine.getSweet();
		this.acidity = wine.getAcidity();
		this.body = wine.getBody();
		this.tannin = wine.getTannin();
		this.area = wine.getArea();
		this.grape = wine.getGrape();
	}
	
	
}
