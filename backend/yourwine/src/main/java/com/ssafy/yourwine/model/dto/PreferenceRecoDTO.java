package com.ssafy.yourwine.model.dto;

import java.util.List;

import com.ssafy.yourwine.model.entity.Flavor;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PreferenceRecoDTO {

	private List<WineDTO> wineList;
	private List<FlavorDTO> likeFlavorList;
	private List<FlavorDTO> DislikeFlavorList;
	
}
