package com.ssafy.yourwine.service;
import com.ssafy.yourwine.model.dto.FoodDTO;
import org.springframework.stereotype.Service;

import com.ssafy.yourwine.model.entity.Food;
import com.ssafy.yourwine.repository.FoodRepository;

@Service
public class FoodService {

	private final FoodRepository foodRepository;

	public FoodService(FoodRepository foodRepository) {
		this.foodRepository = foodRepository;
	}
	
    public void saveFood(String name, String img) {
        Food food = new Food();
        food.setName(name);
        food.setImg(img);

        foodRepository.save(food);
    }

    public FoodDTO getFood(Integer foodId) {
	    Food food = new Food();
	    food = foodRepository.findByFoodId(foodId);

	    FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName(food.getName());
        foodDTO.setImg(food.getImg());

	    return foodDTO;
    }
	
	
}
