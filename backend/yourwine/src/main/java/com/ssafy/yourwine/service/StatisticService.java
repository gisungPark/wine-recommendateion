package com.ssafy.yourwine.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.yourwine.config.security.JwtTokenProvider;
import com.ssafy.yourwine.model.dto.StatisticsByPreDTO;
import com.ssafy.yourwine.model.dto.StatisticsBySimilarDTO;
import com.ssafy.yourwine.model.dto.StatisticsByTasteDTO;
import com.ssafy.yourwine.model.dto.WineDTO;
import com.ssafy.yourwine.model.entity.BatchData;
import com.ssafy.yourwine.model.entity.User;
import com.ssafy.yourwine.model.entity.Wine;
import com.ssafy.yourwine.model.entity.WineFlavor;
import com.ssafy.yourwine.repository.BatchRepository;
import com.ssafy.yourwine.repository.UserRepository;
import com.ssafy.yourwine.repository.WineFlavorRepository;
import com.ssafy.yourwine.repository.WineRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class StatisticService {

	private final ModelMapper modelMapper = new ModelMapper();
	private final WineRepository wineRepository;
	private final UserRepository userRepository;
	private final WineFlavorRepository wineFlavorRepository;
	private final BatchRepository batchRepository;
	private final JwtTokenProvider jwtTokenProvider;

	//Preferences 탭 - 선호하는 품종, 와인종류 퍼센트 통계
	public StatisticsByPreDTO getWineListByUser (String token)
	{
		String userId = jwtTokenProvider.getUserId(token);
		User user = userRepository.findByUserId(userId);
		StatisticsByPreDTO statisticsByPreDto = new StatisticsByPreDTO();
		
		//리뷰없을 경우
		List<Wine> wineList = wineRepository.findAllByUserReview(userId);
		if(wineList.size() == 0) {
			statisticsByPreDto.setWriteReview(false);
			return statisticsByPreDto;
		}
			
		int redCnt = 0;
		int whiteCnt = 0;
		int spaclingCnt = 0;
		int grapeCntList [] = new int [11];
		for (Wine wine : wineList) {
			String type = wine.getType();
			Long grpeId = wine.getGrape().getGrapeId();
			int idx = Math.toIntExact(grpeId);
			//와인 품종
			grapeCntList[idx]++;
			//와인 종류
			if(type.equals("레드")) redCnt++;
			else if(type.equals("화이트")) whiteCnt++;
			else if(type.equals("스파클링")) spaclingCnt++;
			
		}
		
		//set
		statisticsByPreDto.setWriteReview(true);
		statisticsByPreDto.setRedCnt(redCnt);
		statisticsByPreDto.setWhiteCnt(whiteCnt);
		statisticsByPreDto.setSpaclingCnt(spaclingCnt);
		statisticsByPreDto.setGrapeCntList(grapeCntList);
		
		return statisticsByPreDto;
	}
	
	
	//Taste/Flavor 탭 - 와인향, 와인 맛 통계
	public StatisticsByTasteDTO getWineListByTaste (String token) {
		
		String userId = jwtTokenProvider.getUserId(token);
		User user = userRepository.findByUserId(userId);
		StatisticsByTasteDTO statisticsByTasteDto = new StatisticsByTasteDTO();
	
		//리뷰없을 경우
		List<Wine> wineList = wineRepository.findAllByUserReview(userId);
		if(wineList.size() == 0) {
			statisticsByTasteDto.setWriteReview(false);
			return statisticsByTasteDto;
		}
		
		//와인 맛
		int size = wineList.size();
		double tanninSum = 0;
		double bodySum = 0;
		double sweetSum = 0;
		double aciditySum = 0;
		for (Wine wine : wineList) {
			tanninSum += wine.getTannin();
			bodySum += wine.getBody();
			aciditySum += wine.getAcidity();
			sweetSum += wine.getSweet();
		}
		
		double tanninAvg = tanninSum / size;
		double bodyAvg = bodySum / size;
		double sweetAvg = sweetSum / size;
		double acidityAvg = aciditySum / size;
		
		//와인 향
		int flavorCntList[] = new int[19];
		List<WineFlavor> wineFlavorList = wineFlavorRepository.findAllByUserReview(userId);
		
		for (WineFlavor wineFlavor : wineFlavorList) {
			Long flavorId = wineFlavor.getFlavor().getFlavorId();
			int idx = Math.toIntExact(flavorId);
			flavorCntList[idx] ++;
		}
		
		//set
		statisticsByTasteDto.setWriteReview(true);
		statisticsByTasteDto.setTanninAvg(Math.round(tanninAvg*100)/100.0); //셋째자리 반올림
		statisticsByTasteDto.setAcidityAvg(Math.round(acidityAvg*100)/100.0);
		statisticsByTasteDto.setBodyAvg(Math.round(bodyAvg*100)/100.0);
		statisticsByTasteDto.setSweetAvg(Math.round(sweetAvg*100)/100.0);
		statisticsByTasteDto.setFlavorCntList(flavorCntList);
		
		return statisticsByTasteDto;
	}
	
	//Similar 탭 - 유사유저 평점 높은, 평점 낮은 통계
	public StatisticsBySimilarDTO getWineListBySimilar (String token) {
		
		String userId = jwtTokenProvider.getUserId(token);
		User user = userRepository.findByUserId(userId);
		StatisticsBySimilarDTO statisticsBySimilarDto = new StatisticsBySimilarDTO();
		
		List<Wine> wineList = wineRepository.findAllByUserReview(userId); 
		//리뷰가 있다면
		if(wineList.size() != 0) {
			statisticsBySimilarDto.setWriteReview(true);
		}
		
		BatchData batchData = batchRepository.findByUserId(userId);
		//평점배치작업이 진행 안되서 db에 없을 경우
		if(batchData == null) {
			statisticsBySimilarDto.setBatchData(false); 
			return statisticsBySimilarDto;
		}
		String lowString = batchData.getSimilarUserLow().replace(" ", "");
		String highString = batchData.getSimilarUserHige().replace(" ", "");
		
		// string -> string[]
		String[] lowStringList = lowString.substring(1, lowString.length() - 1).split(",");
		String[] highStringList = highString.substring(1, highString.length() - 1).split(",");
		
		// string -> Long []
		int[] lowList = Arrays.asList(lowStringList).stream().mapToInt(Integer::parseInt).toArray();
		int[] highList = Arrays.asList(highStringList).stream().mapToInt(Integer::parseInt).toArray();
		
		List<Wine> lowWineList = wineRepository.findAllByWineList(lowList);
		List<Wine> highWineList = wineRepository.findAllByWineList(highList);
		
		List<WineDTO> lowWineDtoList = new ArrayList<WineDTO>();
		List<WineDTO> highWineDtoList = new ArrayList<WineDTO>();
		
		//순서정렬 필요 -> 가져오면 기본 wine_id순으로 오름차순으로 정렬해서 그대로 주면 x 
		for (int i = 0; i < 5; i++) {
			for (Wine wine : lowWineList) {
				if(wine.getWineId() == lowList[i]) {
					WineDTO wineDto = modelMapper.map(wine, WineDTO.class);
					lowWineDtoList.add(wineDto);
				}
			}
		}
		
		for (int i = 0; i < 5; i++) {
			for (Wine wine : highWineList) {
				if(wine.getWineId() == highList[i]) {
					WineDTO wineDto = modelMapper.map(wine, WineDTO.class);
					highWineDtoList.add(wineDto);
				}
			}
		}
		//set
		statisticsBySimilarDto.setBatchData(true);
		statisticsBySimilarDto.setSimilarLowList(lowWineDtoList);
		statisticsBySimilarDto.setSimilarHighList(highWineDtoList);
		
		return statisticsBySimilarDto;
	}
	
}
