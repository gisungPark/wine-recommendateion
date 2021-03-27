package com.ssafy.yourwine.service;
import com.ssafy.yourwine.model.dto.FoodDTO;
import org.springframework.stereotype.Service;

import com.ssafy.yourwine.model.entity.Food;
import com.ssafy.yourwine.repository.FoodRepository;
import com.ssafy.yourwine.repository.GrapeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodService {

	private final FoodRepository foodRepository;

	
	
}
