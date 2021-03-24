package com.ssafy.yourwine.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.yourwine.model.dto.*;
import com.ssafy.yourwine.service.GrapeService;
import com.ssafy.yourwine.service.ScrapService;
import com.ssafy.yourwine.service.WineService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wine")
public class WineController {

	private final GrapeService grapeService;
	private final WineService WineService;
	private final ScrapService ScrapService;

	@GetMapping("/test/{wineId}")
	public void test(@PathVariable int wineId) {
		// WineService.delete(wineId);
		System.out.println("와인 지우기 cascade 테스트입니다!");
	}

	@ApiOperation(value = "품종 상세 정보", notes = "품종 id에 해당하는 상세정보 반환한다. 해당하는 id가 없을 경우 null")
	@GetMapping("/grape/{grapeId}")
	public GrapeDTO getGrape(@ApiParam(value = "품종 ID") @PathVariable int grapeId) {
		return grapeService.getGrape(grapeId);
	}

	@ApiOperation(value = "품종 전체 리스트", notes = "품종 전체 리스트 반환한다. 리스트 없을 경우 null")
	@GetMapping("/grape")
	public List<GrapeDTO> getGrapeList() {
		return grapeService.getGrapeList();
	}

	@ApiOperation(value = "와인 상세 정보", notes = "와인 id에 해당하는 상세정보 반환한다. 해당하는 id가 없을 경우 null")
	@GetMapping("/{wineId}")
	public WineDTO getWine(@PathVariable int wineId) {
		return WineService.getWine(wineId);
	}

	@ApiOperation(value = "와인 검색시 와인 전체 리스트", notes = "와인 검색시 와인 전체 리스트 반환한다. 리스트 없을 경우 null")
	@GetMapping("/list")
	public List<WineDTO> getWineList() {
		return WineService.getWineList();
	}

	@ApiOperation(value = "와인 스크랩 추가", notes = "스크랩을 추가한다!")
	@PostMapping("/scrap/{wineId}")
	public void saveScrap(@ApiParam(value = "토큰") @RequestHeader("TOKEN") String token, @ApiParam(value = "와인ID") @PathVariable int wineId) {
		ScrapService.saveScrap(token, wineId);
	}

	@ApiOperation(value = "와인 스크랩 삭제", notes = "스크랩을 삭제한다!")
	@DeleteMapping("/scrap/{wineId}")
	public void deleteScrap(@ApiParam(value = "토큰") @RequestHeader("TOKEN") String token, @ApiParam(value = "와인ID") @PathVariable int wineId) {
		ScrapService.deleteScrap(token, wineId);
	}
	
	
	
	
}
