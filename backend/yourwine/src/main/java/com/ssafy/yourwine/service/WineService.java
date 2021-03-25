package com.ssafy.yourwine.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.ssafy.yourwine.config.security.JwtTokenProvider;
import com.ssafy.yourwine.model.dto.WineDTO;
import com.ssafy.yourwine.model.entity.Scrap;
import com.ssafy.yourwine.model.entity.User;
import com.ssafy.yourwine.model.entity.Wine;
import com.ssafy.yourwine.model.key.ScrapKey;
import com.ssafy.yourwine.repository.ScrapRepository;
import com.ssafy.yourwine.repository.UserRepository;
import com.ssafy.yourwine.repository.WineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WineService {

	private final ModelMapper modelMapper = new ModelMapper();
	private final WineRepository wineRepository;
	private final ScrapRepository scrapRepository;
	private final JwtTokenProvider jwtTokenProvider;
	
	//와인 상세보기
	public WineDTO getWine(Long wineId) {
		Wine wine = wineRepository.findById(wineId).orElseThrow(() -> new IllegalArgumentException("no wine data"));
		WineDTO wineDto = modelMapper.map(wine, WineDTO.class);
		return wineDto;
	}
	
	//와인 전체 리스트
	public List<WineDTO> getWineList(){
		List<Wine> wineList = wineRepository.findAll();
		List<WineDTO> wineDtoList = modelMapper.map(wineList, new TypeToken<List<WineDTO>>() {}.getType());
		return wineDtoList;
	}
	
	//와인삭제
//	public void delete(int wineId) {
//		Wine wine = wineRepository.findByWineId(wineId);
//		wineRepository.delete(wine);
//	}
	
}
