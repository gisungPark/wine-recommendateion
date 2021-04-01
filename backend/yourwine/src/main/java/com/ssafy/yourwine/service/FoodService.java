package com.ssafy.yourwine.service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
	
	public List<WineDTO> getWineListByFood (Long foodId, int page){
		Food food = foodRepository.findById(foodId).orElseThrow(() -> new IllegalArgumentException("no food data"));
		PageRequest pageRequest = PageRequest.of(page-1,10);
		List<WineDTO> wineDtoList = wineFoodMatchRepository.findByFood(food, pageRequest).stream().map(WineDTO::new).collect(Collectors.toList());
		return wineDtoList;
	}
	
}
