package com.ssafy.yourwine.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.ssafy.yourwine.model.dto.GrapeDTO;
import com.ssafy.yourwine.model.entity.Grape;
import com.ssafy.yourwine.repository.GrapeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GrapeService {
	
	ModelMapper modelMapper = new ModelMapper();
	private final GrapeRepository grapeRepository;
	
	public GrapeDTO getGrape(Integer grapeId) {
		
		Grape grape = grapeRepository.findByGrapeId(grapeId);
		GrapeDTO grapeDto = new GrapeDTO();
		//modelMapper.map(grape, GrapeDTO.class);
		modelMapper.map(grape, grapeDto);
		System.out.println(grape);
		System.out.println(grapeDto);
		return grapeDto;
	}
}
