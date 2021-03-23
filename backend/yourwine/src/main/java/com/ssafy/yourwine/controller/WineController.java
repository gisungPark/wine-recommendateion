package com.ssafy.yourwine.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.yourwine.model.dto.GrapeDTO;
import com.ssafy.yourwine.service.GrapeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
	@ApiOperation(value = "품종 상세 정보", notes="품종 id에 해당하는 상세정보 반환한다. 해당하는 id가 없을 경우 null" )
	public GrapeDTO getGrape(@ApiParam(value="품종 ID") @PathVariable int grapeId) {
		return grapeService.getGrape(grapeId);
	}
	
	@GetMapping("/grape")
	@ApiOperation(value = "품종 전체 리스트", notes="품종 전체 리스트 반환한다. 리스트 없을 경우 null" )
	public List<GrapeDTO> getGrapeList(){
		return grapeService.getGrapeList();
	}
	
	
	
}
