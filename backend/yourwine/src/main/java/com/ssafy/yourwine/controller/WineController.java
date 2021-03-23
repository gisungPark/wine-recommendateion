package com.ssafy.yourwine.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.yourwine.model.dto.GrapeDTO;
import com.ssafy.yourwine.model.entity.Grape;
import com.ssafy.yourwine.service.GrapeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wine")
public class WineController {

	private final GrapeService grapeService;
	
	@GetMapping("/test")
	public void test() {
		System.out.println("테스트입니다!");
	}
	
	@GetMapping("/grape/{grapeId}")
	public GrapeDTO getGrape(@PathVariable int grapeId) {
		return grapeService.getGrape(grapeId);
	}
	
}
