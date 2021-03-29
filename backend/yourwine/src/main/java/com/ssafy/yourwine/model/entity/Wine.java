package com.ssafy.yourwine.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.websocket.Decoder.Text;

import lombok.Data;

@Entity
@Data
public class Wine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wine_id")
	private Long wineId;
	private String kname;
	private String ename;
	private String alcohol;
	private String type;
	private String temper;
	private float avg;
	private int price;
	private String year;
	@Column(name = "detail", columnDefinition = "text")
	private String detail;
	private String shop;
	private int sweet;
	private int acidity;
	private int body;
	private int tannin;
	private String area;
	@OneToOne
	@JoinColumn(name = "grape_id")
	private Grape grape;
	
	@OneToMany(mappedBy = "wine",cascade = CascadeType.ALL)
	private List<Scrap> scrap = new ArrayList<>();
	
	@OneToMany(mappedBy = "wine", cascade = CascadeType.ALL)
	private List<Review> review = new ArrayList<Review>();
	
	@OneToMany(mappedBy = "wine", cascade = CascadeType.ALL)
	private List<WineFoodMatch> wineFoodMatch = new ArrayList<WineFoodMatch>();
}
