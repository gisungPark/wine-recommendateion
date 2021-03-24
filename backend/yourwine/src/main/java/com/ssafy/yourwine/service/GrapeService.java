package com.ssafy.yourwine.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.ssafy.yourwine.model.dto.GrapeDTO;
import com.ssafy.yourwine.model.entity.Grape;
import com.ssafy.yourwine.repository.GrapeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GrapeService {
	
	private final ModelMapper modelMapper = new ModelMapper();
	private final GrapeRepository grapeRepository;

	//품종 상세보기
	public GrapeDTO getGrape(int grapeId) {	
		Grape grape = grapeRepository.findByGrapeId(grapeId);
		if(grape == null)
			return null;
		GrapeDTO grapeDto = modelMapper.map(grape, GrapeDTO.class);
		return grapeDto;
	}
	
	//품종 전체 리스트
	public List<GrapeDTO> getGrapeList(){
		List<Grape> grapeList = grapeRepository.findAll();
		if(grapeList == null)
			return null;
		List<GrapeDTO> grapeDtoList = modelMapper.map(grapeList, new TypeToken<List<GrapeDTO>>() {}.getType());
		return grapeDtoList;
	}
}
