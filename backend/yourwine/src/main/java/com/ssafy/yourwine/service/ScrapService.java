package com.ssafy.yourwine.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.yourwine.config.security.JwtTokenProvider;
import com.ssafy.yourwine.model.entity.Scrap;
import com.ssafy.yourwine.model.key.ScrapKey;
import com.ssafy.yourwine.repository.ScrapRepository;
import com.ssafy.yourwine.repository.UserRepository;
import com.ssafy.yourwine.repository.WineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScrapService {

	private final ScrapRepository scrapRepository;
	private final WineRepository wineRepository;
	private final JwtTokenProvider jwtTokenProvider;

	// 스크랩 추가
	@Transactional
	public void saveScrap(String token, int wineId) {
		Scrap scrap = new Scrap();
		ScrapKey scrapKey = new ScrapKey();
		String userId = jwtTokenProvider.getUserId(token);
		scrapKey.setUserId(userId);
		scrapKey.setWineId(wineId);
		scrap.setScrapId(scrapKey);
		scrapRepository.save(scrap);
	}

	// 스크랩 삭제
	public void deleteScrap(String token, int wineId) {
		Scrap scrap = new Scrap();
		ScrapKey scrapKey = new ScrapKey();
		String userId = jwtTokenProvider.getUserId(token);
		wineRepository.findById(wineId).orElseThrow(() -> new IllegalArgumentException("no wine data"));
		scrapKey.setUserId(userId);
		scrapKey.setWineId(wineId);
		scrap.setScrapId(scrapKey);
		scrapRepository.delete(scrap);
	}
}
