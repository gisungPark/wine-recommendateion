package com.ssafy.yourwine.service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ssafy.yourwine.model.dto.FoodDTO;
import com.ssafy.yourwine.model.dto.WineDTO;
import com.ssafy.yourwine.model.entity.Food;
import com.ssafy.yourwine.model.entity.WineFoodMatch;
import com.ssafy.yourwine.repository.FoodRepository;
import com.ssafy.yourwine.repository.WineFoodMatchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodService {

	private final FoodRepository foodRepository;
	private final WineFoodMatchRepository wineFoodMatchRepository;
	private final ModelMapper modelMapper = new ModelMapper();
	
	public List<WineDTO> getWineListByFood (Long foodId){
		Food food = foodRepository.findById(foodId).orElseThrow(() -> new IllegalArgumentException("no food data"));
		List<WineDTO> wineDtoList = wineFoodMatchRepository.findAll().stream().filter(s -> s.getWineFoodId().getFoodId() == foodId).map(WineDTO::new).collect(Collectors.toList());
		//리스트에서 페이징 걸어야함.
		//List<WineDTO> wineDtoList = wineFoodMatchRepository.findByFood(food).stream().map(WineDTO::new).collect(Collectors.toList());
		//음식에 따른 다중 선택걸어야할듯..
//		List<WineDTO> wineDtoList = new ArrayList<WineDTO>();
//		List<WineFoodMatch> list = wineFoodMatchRepository.findByFood(food);
//		for (WineFoodMatch w : list) {
//			WineDTO wineDto = modelMapper.map(w.getWine(), WineDTO.class); 
//			wineDtoList.add(wineDto);
//		}
		//페이징
		return wineDtoList;
	}
	
}
