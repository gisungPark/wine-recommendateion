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
	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	
	//와인 상세보기
	public WineDTO getWine(int wineId) {
		Wine wine = wineRepository.findByWineId(wineId);
		if(wine == null)
			return null;
		WineDTO wineDto = modelMapper.map(wine, WineDTO.class);
		return wineDto;
	}
	
	//와인 전체 리스트
	public List<WineDTO> getWineList(){
		List<Wine> wineList = wineRepository.findAll();
		if(wineList == null)
			return null;
		List<WineDTO> wineDtoList = modelMapper.map(wineList, new TypeToken<List<WineDTO>>() {}.getType());
		return wineDtoList;
	}
	
	//스크랩 추가
	@Transactional
	public void saveScrap(String token, int wineId) {
		Scrap scrap = new Scrap();
		ScrapKey scrapKey = new ScrapKey();
		String userId = jwtTokenProvider.getUserId(token);
		scrapKey.setUserId(userId);
		scrapKey.setWineId(wineId);
		scrap.setId(scrapKey);
		scrapRepository.save(scrap);
	}
	
	//스크랩 삭제
	public void deleteScrap(String token, int wineId) {
		Scrap scrap = new Scrap();
		ScrapKey scrapKey = new ScrapKey();
		String userId = jwtTokenProvider.getUserId(token);
		wineRepository.findById(wineId).orElseThrow(() -> new IllegalArgumentException("no wine data"));
		scrapKey.setUserId(userId);
		scrapKey.setWineId(wineId);
		scrap.setId(scrapKey);
		scrapRepository.delete(scrap);
	}
	
	
	//와인삭제
//	public void delete(int wineId) {
//		Wine wine = wineRepository.findByWineId(wineId);
//		wineRepository.delete(wine);
//	}
	
}
