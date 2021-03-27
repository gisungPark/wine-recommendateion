package com.ssafy.yourwine.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.yourwine.model.dto.GrapeDTO;
import com.ssafy.yourwine.model.dto.ReviewDTO;
import com.ssafy.yourwine.model.dto.WineDTO;
import com.ssafy.yourwine.model.dto.WineDetailDTO;
import com.ssafy.yourwine.model.entity.Review;
import com.ssafy.yourwine.service.GrapeService;
import com.ssafy.yourwine.service.ReviewService;
import com.ssafy.yourwine.service.ScrapService;
import com.ssafy.yourwine.service.WineService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
	private final ReviewService reviewService;

	@PostMapping("/test/{wineId}")
	public void test(@PathVariable Long wineId) {
		// return reviewService.getWineReviewList(wineId);
	}

	@ApiOperation(value = "품종 상세 정보", notes = "- 품종 id에 해당하는 상세정보 반환한다. 해당하는 id가 없을 경우 no grape data 에러발생")
	@GetMapping("/grape/{grapeId}")
	public GrapeDTO getGrape(@ApiParam(value = "품종 ID") @PathVariable Long grapeId) {
		return grapeService.getGrape(grapeId);
	}

	@ApiOperation(value = "품종 전체 리스트", notes = "- 품종 전체 리스트 반환한다. 리스트 없을 경우 null")
	@GetMapping("/grape")
	public List<GrapeDTO> getGrapeList() {
		return grapeService.getGrapeList();
	}

	@ApiOperation(value = "와인 상세 정보", notes = "- 와인 id에 해당하는 상세정보 반환한다. \n **- 와인 정보/ 유저 스크랩여부/ 유저리뷰 작성여부/ 음식리스트/ 리뷰리스트 3개**")
	@GetMapping("/{wineId}")
	public WineDetailDTO getWine(@PathVariable Long wineId, @ApiParam(value = "로그인 안 했을경우, token에 guest 담아서 주기", defaultValue = "guest", required = true) @RequestHeader("TOKEN") String token) {
		return WineService.getWine(wineId, token);
	}

	@ApiOperation(value = "와인 검색시 와인 전체 리스트", notes = "- 와인 검색시 와인 전체 리스트 반환한다. 리스트 없을 경우 []")
	@ApiImplicitParams({
		@ApiImplicitParam(name="typeFilter",value = "타입필터 - 전체(기본)/레드/화이트/스파클링", required = true, defaultValue = "전체"),
		@ApiImplicitParam(name="grapeFilter",value = "품종필터 - 전체(기본)/품종 한국이름", required = true, defaultValue = "전체", dataType = ""),
		@ApiImplicitParam(name="pointFilter",value = "별점필터 - 0(기본)/1/2/3/4/5", required = true, defaultValue = "0", dataType = "int"),
		@ApiImplicitParam(name="startPrice",value = "시작가격 - 0(기본)", required = true, defaultValue = "0", dataType = "int"),
		@ApiImplicitParam(name="endPrice",value = "끝가격 - max(기본)", required = true, defaultValue = "99999999", dataType = "int"),
		@ApiImplicitParam(name="sort",value = "정렬 - 0(기본)/1(낮은가격)2(높은가격)/3(낮은별점)/4(높은별점)", required = true, defaultValue = "0", dataType = "int"),
		@ApiImplicitParam(name="keyword",value = "검색키워드 - 없으면. 있으면 해당키워드값", defaultValue = ".", required = true),
	})
	@GetMapping("/list")
	public List<WineDTO> getWineList(@RequestParam String typeFilter, @RequestParam String grapeFilter,
			@RequestParam int pointFilter, @RequestParam int startPrice, @RequestParam int endPrice,
			@RequestParam int sort, @RequestParam String keyword) {
		return WineService.getWineList(typeFilter, grapeFilter, pointFilter, startPrice, endPrice, sort, keyword);
	}

	@ApiOperation(value = "와인 스크랩 추가", notes = "스크랩을 추가한다!")
	@PostMapping("/scrap/{wineId}")
	public void saveScrap(@ApiParam(value = "토큰") @RequestHeader("TOKEN") String token,
			@ApiParam(value = "와인ID") @PathVariable Long wineId) {
		ScrapService.saveScrap(token, wineId);
	}

	@ApiOperation(value = "와인 스크랩 삭제", notes = "스크랩을 삭제한다!")
	@DeleteMapping("/scrap/{wineId}")
	public void deleteScrap(@ApiParam(value = "토큰") @RequestHeader("TOKEN") String token,
			@ApiParam(value = "와인ID") @PathVariable Long wineId) {
		ScrapService.deleteScrap(token, wineId);
	}

	@ApiOperation(value = "와인 리뷰 추가", notes = "reviewDto에 담아서 보내주기. \n **userImg, wineImg, wineName, nickname,time 주지마세요 ! ** 나머지는 필수")
	@PostMapping("/reivew")
	public void saveReview(@RequestBody ReviewDTO reviewDto, @RequestHeader("TOKEN") String token) {
		reviewService.saveReview(reviewDto, token);
	}

	@ApiOperation(value = "와인 리뷰 수정", notes = "reviewDto에 수정할 내용 담아서 보내주기. \n **userImg, wineImg, wineName, nickname,time 주지마세요 ! ** 나머지는 필수.  \n 변경사항만 주는게 아니라 기존 사항에서 변경된 사항 포함시켜서 줘야함. ex) 별점만 변경했다면, 기존contents, 가성비는 기존꺼랑 같이 담아서 보내줘야함")
	@PutMapping("/reivew")
	public void updateReview(@RequestBody ReviewDTO reviewDto, @RequestHeader("TOKEN") String token) {
		reviewService.updateReview(reviewDto, token);
	}

	@ApiOperation(value = "와인 리뷰 삭제", notes = "삭제할 와인ID에 해당하는 리뷰 삭제")
	@DeleteMapping("/reivew/{wineId}")
	public void deleteReview(@RequestHeader("TOKEN") String token, @PathVariable Long wineId) {
		reviewService.deleteReview(token, wineId);
	}

	@ApiOperation(value = "와인ID 전체 리뷰 리스트 ", notes = "와인ID에 해당되는 리뷰 리스트. 와인ID없을 경우 no wine data 에러발생")
	@GetMapping("/reivew-list/{wineId}")
	public List<ReviewDTO> getReviewList(@PathVariable Long wineId) {
		return reviewService.getWineReviewList(wineId);
	}

}
