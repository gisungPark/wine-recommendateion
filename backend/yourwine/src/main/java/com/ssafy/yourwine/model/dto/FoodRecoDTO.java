package com.ssafy.yourwine.model.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class FoodRecoDTO {

	private List<WineDTO> wineList;
	private String mention;
}
