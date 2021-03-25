package com.ssafy.yourwine.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.yourwine.config.security.JwtTokenProvider;
import com.ssafy.yourwine.model.entity.Scrap;
import com.ssafy.yourwine.model.entity.User;
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
	private final UserRepository userRepository;

	//스크랩 추가
	@Transactional
	public void saveScrap(String token, Long wineId) {
		Scrap scrap = new Scrap();
		ScrapKey scrapKey = new ScrapKey();
		String userId = jwtTokenProvider.getUserId(token);
		scrapKey.setUserId(userId);
		scrapKey.setWineId(wineId);
		scrap.setScrapId(scrapKey);
		scrapRepository.save(scrap);
	}

	//스크랩 삭제
	@Transactional
	public void deleteScrap(String token, Long wineId) {
		Scrap scrap = new Scrap();
		ScrapKey scrapKey = new ScrapKey();
		String userId = jwtTokenProvider.getUserId(token);
		wineRepository.findById(wineId).orElseThrow(() -> new IllegalArgumentException("no wine data"));
		scrapKey.setUserId(userId);
		scrapKey.setWineId(wineId);
		scrap.setScrapId(scrapKey);
		scrapRepository.delete(scrap);
	}
	
	//유저 스크랩 리스트
	public List<Scrap> getScrapList (String token){
		String userId = jwtTokenProvider.getUserId(token);
		User user = userRepository.findByUserId(userId);
		System.out.println(user.getUserId());
		List<Scrap> scrapList = scrapRepository.findScrapByUser(user);
		return scrapList;
	}
}
