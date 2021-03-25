package com.ssafy.yourwine.model.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class PreferenceDTO {
	TasteDTO tasteDTO;
	List<FlavorDTO> likeList;
	List<FlavorDTO> dislikeList;
}
