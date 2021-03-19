package com.ssafy.yourwine.controller;

import com.ssafy.yourwine.model.dto.FoodDTO;
import com.ssafy.yourwine.service.FoodService;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {

	private final FoodService foodService;

	public UserController(FoodService foodService) {
		this.foodService=foodService;
	}
	 
	@PostMapping("/test/{name}/{img}")
	@ApiOperation(value = "Test", notes = "Test코드")
	public void test(@PathVariable String name, @PathVariable String img){
		foodService.saveFood(name,img);
	}


	@GetMapping("/test/{foodId}")
	@ApiOperation(value = "Test", notes = "Test코드")
	public FoodDTO test2(@PathVariable Integer foodId){
		return foodService.getFood(foodId);
	}
}
