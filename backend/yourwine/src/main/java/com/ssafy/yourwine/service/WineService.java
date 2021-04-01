package com.ssafy.yourwine.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.yourwine.config.security.JwtTokenProvider;
import com.ssafy.yourwine.model.dto.FlavorDTO;
import com.ssafy.yourwine.model.dto.FoodDTO;
import com.ssafy.yourwine.model.dto.ReviewDTO;
import com.ssafy.yourwine.model.dto.WineDTO;
import com.ssafy.yourwine.model.dto.WineDetailDTO;
import com.ssafy.yourwine.model.entity.User;
import com.ssafy.yourwine.model.entity.Wine;
import com.ssafy.yourwine.model.entity.WineFoodMatch;
import com.ssafy.yourwine.repository.ReviewRepository;
import com.ssafy.yourwine.repository.ScrapRepository;
import com.ssafy.yourwine.repository.UserRepository;
import com.ssafy.yourwine.repository.WineFlavorRepository;
import com.ssafy.yourwine.repository.WineFoodMatchRepository;
import com.ssafy.yourwine.repository.WineRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class WineService {

	private final ModelMapper modelMapper = new ModelMapper();
	private final WineRepository wineRepository;
	private final UserRepository userRepository;
	private final ScrapRepository scrapRepository;
	private final ReviewRepository reviewRepository;
	private final WineFlavorRepository wineFlavorRepository;
	private final WineFoodMatchRepository wineFoodMatchRepository;
	private final JwtTokenProvider jwtTokenProvider;

	// 와인 상세보기
	public WineDetailDTO getWine(Long wineId, String token) {
		Wine wine = wineRepository.findById(wineId).orElseThrow(() -> new IllegalArgumentException("no wine data"));
		WineDTO wineDto = modelMapper.map(wine, WineDTO.class);
		List<ReviewDTO> reviewDtoList = reviewRepository.findByWine(wine).stream().map(ReviewDTO::new)
				.collect(Collectors.toList());
		List<FoodDTO> foodDtoList = wineFoodMatchRepository.findByWine(wine).stream().map(FoodDTO::new)
				.collect(Collectors.toList());
		List<WineDTO> moreWineDtoList = wineRepository.findByGrape(wine.getGrape()).stream()
				.filter(s -> s.getWineId() != wineId).map(WineDTO::new).sorted(Comparator.comparing(WineDTO::getAvg).reversed())
				.limit(6).collect(Collectors.toList());
		List<FlavorDTO> flavorDtoList = wineFlavorRepository.findByWine(wine).stream().map(FlavorDTO::new).collect(Collectors.toList());
		
		int[] costArray = new int[3];
		for (ReviewDTO reviewDto : reviewDtoList) {
			if (reviewDto.getCost() == 1) //가성비 별로
				costArray[0]++;
			if (reviewDto.getCost() == 2) //가성비 소소
				costArray[1]++;
			if (reviewDto.getCost() == 3) //가성비 굿
				costArray[2]++;
		}
		
		reviewDtoList = reviewDtoList.stream().sorted(Comparator.comparing(ReviewDTO::getTime).reversed()).limit(3).collect(Collectors.toList());
		WineDetailDTO wineDetailDto = new WineDetailDTO();
		wineDetailDto.setWineDto(wineDto);// 와인정보
		wineDetailDto.setReviewList(reviewDtoList); // 리뷰리스트 3개
		wineDetailDto.setFoodList(foodDtoList); // 음식리스트
		wineDetailDto.setCostArray(costArray); // 가성비 인원수 cnt
		wineDetailDto.setMoreWineList(moreWineDtoList);// morewine 리스트
		wineDetailDto.setFlavorList(flavorDtoList); //와인향 정보 리스트
		
		if (!token.equals("guest")) { // 로그인 안 했을 경우
			String userId = jwtTokenProvider.getUserId(token);
			User user = userRepository.findByUserId(userId);
			if (scrapRepository.findByUserAndWine(user, wine).isPresent()) {
				wineDetailDto.setScrap(true);
			} else {
				wineDetailDto.setWriteReview(false);
			}
			if (reviewRepository.findByUserAndWine(user, wine).isPresent()) {
				wineDetailDto.setWriteReview(true);
			} else {
				wineDetailDto.setWriteReview(false);
			}
		}
		return wineDetailDto;
	}

	// 와인 검색 리스트
	public List<WineDTO> getWineList(String typeFilter, String grapeFilter, int pointFilter, int startPrice,
			int endPrice, int sort, String keyword, int page) {
		List<Wine> wineList = wineRepository.findAll();
		List<WineDTO> wineDtoList = modelMapper.map(wineList, new TypeToken<List<WineDTO>>() {
		}.getType());

		// 검색 키워드 - 있을때 적용
		if (!keyword.equals("."))
			wineDtoList = wineDtoList.stream()
					.filter(s -> s.getKname().contains(keyword) || s.getEname().contains(keyword))
					.collect(Collectors.toList());

		// 와인타입 필터 [전체, 레드, 화이트, 스파클링]
		if (!typeFilter.equals("전체"))
			wineDtoList = wineDtoList.stream().filter(s -> s.getType().equals(typeFilter)).collect(Collectors.toList());
		// 품종 필터 [전체 , 품종이름]
		if (!grapeFilter.equals("전체"))
			wineDtoList = wineDtoList.stream().filter(s -> s.getGrape().getKname().equals(grapeFilter))
					.collect(Collectors.toList());
		// 가격 필터 [0~max]
		wineDtoList = wineDtoList.stream().filter(s -> (s.getPrice() >= startPrice && s.getPrice() <= endPrice))
				.collect(Collectors.toList());
		// 별점 필터 기본-0
		if (pointFilter != 0)
			wineDtoList = wineDtoList.stream()
					.filter(s -> (pointFilter <= s.getAvg() && s.getAvg() < (pointFilter + 1)))
					.collect(Collectors.toList());
		// 정렬
		switch (sort) {
		case 0: { // 낮은 가격순
			wineDtoList = wineDtoList.stream().sorted(Comparator.comparing(WineDTO::getPrice))
					.collect(Collectors.toList());
			break;
		}
		case 1: { // 높은가격순
			wineDtoList = wineDtoList.stream().sorted(Comparator.comparing(WineDTO::getPrice).reversed())
					.collect(Collectors.toList());
			break;
		}
		case 2: { // 낮은별점순
			wineDtoList = wineDtoList.stream().sorted(Comparator.comparing(WineDTO::getAvg))
					.collect(Collectors.toList());
			break;
		}
		case 3: { // 높은 별점순
			wineDtoList = wineDtoList.stream().sorted(Comparator.comparing(WineDTO::getAvg).reversed())
					.collect(Collectors.toList());
			break;
		}
		}
		
		//페이징
		List<WineDTO> returnList = new ArrayList<WineDTO>();
		int item = 8;
		int size = wineDtoList.size();
		int startIdx = (page - 1 ) * item;
		int endIdx = startIdx + item - 1;
		endIdx = (endIdx < wineDtoList.size())? endIdx : size-1;
		if(startIdx < size) {
			for (int i = startIdx; i <= endIdx; i++) {
				returnList.add(wineDtoList.get(i));
			}	
		}
			return returnList;
		
	}

	// 와인삭제
//	public void delete(int wineId) {
//		Wine wine = wineRepository.findByWineId(wineId);
//		wineRepository.delete(wine);
//	}

}
