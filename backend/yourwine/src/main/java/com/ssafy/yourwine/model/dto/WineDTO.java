package com.ssafy.yourwine.model.dto;

import com.ssafy.yourwine.model.entity.Grape;
import com.ssafy.yourwine.model.entity.Wine;
import com.ssafy.yourwine.model.entity.WineFlavor;
import com.ssafy.yourwine.model.entity.WineFoodMatch;

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
	
	public WineDTO (WineFoodMatch wineFoodMatch) {
		this.wineId = wineFoodMatch.getWine().getWineId();
		this.kname = wineFoodMatch.getWine().getKname();
		this.ename = wineFoodMatch.getWine().getEname();
		this.alcohol = wineFoodMatch.getWine().getAlcohol();
		this.type = wineFoodMatch.getWine().getType();
		this.temper = wineFoodMatch.getWine().getTemper();
		this.avg = wineFoodMatch.getWine().getAvg();
		this.price = wineFoodMatch.getWine().getPrice();
		this.year = wineFoodMatch.getWine().getYear();
		this.detail = wineFoodMatch.getWine().getDetail();
		this.shop = wineFoodMatch.getWine().getShop();
		this.sweet = wineFoodMatch.getWine().getSweet();
		this.acidity = wineFoodMatch.getWine().getAcidity();
		this.body = wineFoodMatch.getWine().getBody();
		this.tannin = wineFoodMatch.getWine().getTannin();
		this.area = wineFoodMatch.getWine().getArea();
		this.grape = wineFoodMatch.getWine().getGrape();
	}
	
//	public WineDTO (WineFlavor wineFlavor) {
//		this.wineId = wineFlavor.getWine().getWineId();
//		this.kname = wineFlavor.getWine().getKname();
//		this.ename = wineFlavor.getWine().getEname();
//		this.alcohol = wineFlavor.getWine().getAlcohol();
//		this.type = wineFlavor.getWine().getType();
//		this.temper = wineFlavor.getWine().getTemper();
//		this.avg = wineFlavor.getWine().getAvg();
//		this.price = wineFlavor.getWine().getPrice();
//		this.year = wineFlavor.getWine().getYear();
//		this.detail = wineFlavor.getWine().getDetail();
//		this.shop = wineFlavor.getWine().getShop();
//		this.sweet = wineFlavor.getWine().getSweet();
//		this.acidity = wineFlavor.getWine().getAcidity();
//		this.body = wineFlavor.getWine().getBody();
//		this.tannin = wineFlavor.getWine().getTannin();
//		this.area = wineFlavor.getWine().getArea();
//		this.grape = wineFlavor.getWine().getGrape();
//	}
}
