package com.ssafy.yourwine.service;

import com.ssafy.yourwine.config.security.JwtTokenProvider;
import com.ssafy.yourwine.model.dto.FlavorDTO;
import com.ssafy.yourwine.model.dto.PreferenceDTO;
import com.ssafy.yourwine.model.dto.TasteDTO;
import com.ssafy.yourwine.model.dto.WineDTO;
import com.ssafy.yourwine.model.entity.*;
import com.ssafy.yourwine.model.key.FlavorKey;
import com.ssafy.yourwine.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecoService {

	ModelMapper modelMapper = new ModelMapper();
	private final TasteDegreeRepository tasteDegreeRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final LikeFlavorRepository likeFlavorRepository;
	private final DislikeFlavorRepository dislikeFlavorRepository;
	private final UserRepository userRepository;
	
	public boolean checkPreference(String token) {
		String userId = jwtTokenProvider.getUserId(token);
		User user = userRepository.findByUserId(userId);
		TasteDegree tasteDegree = tasteDegreeRepository.findByUser(user);

		if(tasteDegree != null)
			return true;
		else
			return false;
	}

	@Transactional
	public void updatePreference(String token, PreferenceDTO preferenceDTO) {
		String userId = jwtTokenProvider.getUserId(token);
		User user = userRepository.findByUserId(userId);
		TasteDegree tasteDegree = tasteDegreeRepository.findByUser(user);
		tasteDegree.setSweet(preferenceDTO.getTasteDTO().getSweet());
		tasteDegree.setAcidity(preferenceDTO.getTasteDTO().getAcidity());
		tasteDegree.setTannin(preferenceDTO.getTasteDTO().getTannin());
		tasteDegree.setBody(preferenceDTO.getTasteDTO().getBody());
		System.out.println("여기야1");
		tasteDegreeRepository.save(tasteDegree);
		System.out.println("여기야2");

		likeFlavorRepository.deleteByUser(user);
		dislikeFlavorRepository.deleteByUser(user);
		System.out.println("여기야3");

		for(FlavorDTO flavorDTO: preferenceDTO.getLikeList()){
			LikeFlavor likeFlavor = new LikeFlavor();
			FlavorKey flavorKey = new FlavorKey();
			flavorKey.setUserId(userId);
			flavorKey.setFlavorId(flavorDTO.getFlavorId());
			likeFlavor.setLikeId(flavorKey);
			likeFlavorRepository.save(likeFlavor);
		}

		for(FlavorDTO flavorDTO: preferenceDTO.getDislikeList()){
			DislikeFlavor dislikeFlavor = new DislikeFlavor();
			FlavorKey flavorKey = new FlavorKey();
			flavorKey.setUserId(userId);
			flavorKey.setFlavorId(flavorDTO.getFlavorId());
			dislikeFlavor.setDislikeId(flavorKey);
			dislikeFlavorRepository.save(dislikeFlavor);
		}

	}

	public void savePreference(String token, PreferenceDTO preferenceDTO) {
		String userId = jwtTokenProvider.getUserId(token);
		User user = userRepository.findByUserId(userId);
		TasteDegree tasteDegree = modelMapper.map(preferenceDTO.getTasteDTO(), TasteDegree.class);
		tasteDegree.setUserId(userId);
		System.out.println(tasteDegree);

		tasteDegreeRepository.save(tasteDegree);

		for(FlavorDTO flavorDTO: preferenceDTO.getLikeList()){
			LikeFlavor likeFlavor = new LikeFlavor();
			FlavorKey flavorKey = new FlavorKey();
			flavorKey.setUserId(userId);
			flavorKey.setFlavorId(flavorDTO.getFlavorId());
			likeFlavor.setLikeId(flavorKey);
			likeFlavorRepository.save(likeFlavor);
		}

		for(FlavorDTO flavorDTO: preferenceDTO.getDislikeList()){
			DislikeFlavor dislikeFlavor = new DislikeFlavor();
			FlavorKey flavorKey = new FlavorKey();
			flavorKey.setUserId(userId);
			flavorKey.setFlavorId(flavorDTO.getFlavorId());
			dislikeFlavor.setDislikeId(flavorKey);
			dislikeFlavorRepository.save(dislikeFlavor);
		}

	}

	public PreferenceDTO getPreference(String token) {
		List<FlavorDTO> likeList = new ArrayList<>();
		List<FlavorDTO> dislikeList = new ArrayList<>();
		PreferenceDTO preferenceDTO = new PreferenceDTO();
		String userId = jwtTokenProvider.getUserId(token);
		User user = userRepository.findByUserId(userId);

		TasteDegree tasteDegree = tasteDegreeRepository.findByUser(user);
		List<LikeFlavor> likeFlavorList = likeFlavorRepository.findByUser(user);
		List<DislikeFlavor> dislikeFlavorList = dislikeFlavorRepository.findByUser(user);

		TasteDTO tasteDTO = modelMapper.map(tasteDegree, TasteDTO.class);
		for(LikeFlavor likeFlavor: likeFlavorList){
			FlavorDTO flavorDTO = modelMapper.map(likeFlavor.getFlavor(), FlavorDTO.class);
			likeList.add(flavorDTO);
		}

		for(DislikeFlavor dislikeFlavor: dislikeFlavorList){
			FlavorDTO flavorDTO = modelMapper.map(dislikeFlavor.getFlavor(), FlavorDTO.class);
			dislikeList.add(flavorDTO);
		}

		preferenceDTO.setTasteDTO(tasteDTO);
		preferenceDTO.setLikeList(likeList);
		preferenceDTO.setDislikeList(dislikeList);

		return preferenceDTO;
	}
}
