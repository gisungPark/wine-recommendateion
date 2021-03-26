package com.ssafy.yourwine.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.ssafy.yourwine.config.security.JwtTokenProvider;
import com.ssafy.yourwine.model.dto.WineDTO;
import com.ssafy.yourwine.model.dto.WineDetailDTO;
import com.ssafy.yourwine.model.entity.User;
import com.ssafy.yourwine.model.entity.Wine;
import com.ssafy.yourwine.repository.ReviewRepository;
import com.ssafy.yourwine.repository.ScrapRepository;
import com.ssafy.yourwine.repository.UserRepository;
import com.ssafy.yourwine.repository.WineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WineService {

	private final ModelMapper modelMapper = new ModelMapper();
	private final WineRepository wineRepository;
	private final UserRepository userRepository;
	private final ScrapRepository scrapRepository;
	private final ReviewRepository reviewRepository;
	private final JwtTokenProvider jwtTokenProvider;
	
	//와인 상세보기
	public WineDetailDTO getWine(Long wineId, String token) {
		WineDetailDTO wineDetailDto = new WineDetailDTO();
		Wine wine = wineRepository.findById(wineId).orElseThrow(() -> new IllegalArgumentException("no wine data"));
		WineDTO wineDto = modelMapper.map(wine, WineDTO.class);
		wineDetailDto.setWineDto(wineDto);
		//음식에 어울리는 음식 리스트
		//음식 이유
		if(!token.equals("guest")) {
			String userId = jwtTokenProvider.getUserId(token);
			User user = userRepository.findByUserId(userId);
			//스크랩여부
			if(scrapRepository.findByUserAndWine(user, wine).isPresent()) {
				wineDetailDto.setScrap(true);
			}else {
				wineDetailDto.setWriteReview(false);
			}
			//리뷰작성여부
			if(reviewRepository.findByUserAndWine(user, wine).isPresent()) {
				wineDetailDto.setWriteReview(true);
			}else {
				wineDetailDto.setWriteReview(false);
			}
		}
		return wineDetailDto;
	}
	
	//와인 전체 리스트
	public List<WineDTO> getWineList(){
		List<Wine> wineList = wineRepository.findAll();
		List<WineDTO> wineDtoList = modelMapper.map(wineList, new TypeToken<List<WineDTO>>() {}.getType());
		//필터
		//정렬
		//검색 키워드
		//페이징
		return wineDtoList;
	}
	
	//와인삭제
//	public void delete(int wineId) {
//		Wine wine = wineRepository.findByWineId(wineId);
//		wineRepository.delete(wine);
//	}
	
}
