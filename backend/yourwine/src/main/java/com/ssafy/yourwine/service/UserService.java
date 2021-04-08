package com.ssafy.yourwine.service;

import com.ssafy.yourwine.config.security.JwtTokenProvider;
import com.ssafy.yourwine.model.dto.*;
import com.ssafy.yourwine.model.entity.*;

import com.ssafy.yourwine.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional //이거 추가했어요
public class UserService {

    private final ModelMapper modelMapper = new ModelMapper();
	private final UserRepository userRepository;
	private final ScrapRepository scrapRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final KakaoService kakaoService;
	private final ReviewRepository reviewRepository;
	private final FlavorRepository flavorRepository;
    private final LikeFlavorRepository likeFlavorRepository;
    private final DislikeFlavorRepository dislikeFlavorRepository;
	
    public void saveUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);

        user.setUserId(UUID.randomUUID().toString());
        user.setFlag(true);

        userRepository.save(user);
    }

    public boolean checkEmail(String email) {
        User user = userRepository.findByEmail(email);

        if(user != null)
            return true;
        else
            return false;
    }

    public boolean checkNickname(String nickname) {
        User user = userRepository.findByNickname(nickname);

        if(user != null)
            return true;
        else
            return false;
    }

    public TokenResultDTO logIn(SignInDTO signInDTO) {
        User user = userRepository.findByEmailAndPassword(signInDTO.getEmail(), signInDTO.getPassword());
        TokenResultDTO tokenResultDTO = new TokenResultDTO();

        if(user != null){
            tokenResultDTO.setToken(jwtTokenProvider.generateToken(user.getUserId()));
            tokenResultDTO.setNickname(user.getNickname());
            tokenResultDTO.setCode(0);

            user.setToken(tokenResultDTO.getToken());

            userRepository.save(user);
        } else {
            tokenResultDTO.setCode(1);
        }

        return tokenResultDTO;
    }

    public void logOut(String token) {
        User user = userRepository.findByToken(token);

        user.setToken(null);

        userRepository.save(user);
    }

    public TokenResultDTO checkUser(String kakaotoken) {
        String uid = kakaoService.getKakaoProfile(kakaotoken);

        User user = userRepository.findByEmailAndProvider(uid, 1);

        TokenResultDTO tokenResultDTO = new TokenResultDTO();

        if (user != null) {
            if (user.getFlag()) {
                String token = jwtTokenProvider.generateToken(user.getUserId());
                user.setToken(token);

                userRepository.save(user);

                tokenResultDTO.setToken(token);
                tokenResultDTO.setNickname(user.getNickname());
                tokenResultDTO.setCode(2);

                return tokenResultDTO;
            } else {
                String token = jwtTokenProvider.generateToken(user.getUserId());
                user.setToken(token);

                userRepository.save(user);
                tokenResultDTO.setToken(token);
                tokenResultDTO.setCode(3);

                return tokenResultDTO;
            }
        } else {
            String userId = UUID.randomUUID().toString();
            String token = jwtTokenProvider.generateToken(userId);

            User newUser = new User();

            newUser.setUserId(userId);
            newUser.setEmail(uid);
            newUser.setProvider(1);
            newUser.setToken(token);

            userRepository.save(newUser);

            tokenResultDTO.setToken(token);
            tokenResultDTO.setCode(3);

            return tokenResultDTO;
        }
    }

    public TokenResultDTO updateUser(String token, String nickname) {
	    String user_id = jwtTokenProvider.getUserId(token);
        User user = userRepository.findByUserId(user_id);
        TokenResultDTO tokenResultDTO = new TokenResultDTO();

        user.setNickname(nickname);
        user.setFlag(true);

        userRepository.save(user);

        tokenResultDTO.setCode(5);

        return tokenResultDTO;
    }

    public UserDTO getUserInfo(String token) {
        String user_id = jwtTokenProvider.getUserId(token);
        User user = userRepository.findByUserId(user_id);

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    public void updatePassword(String token, String password) {
        String user_id = jwtTokenProvider.getUserId(token);
        User user = userRepository.findByUserId(user_id);

        user.setPassword(password);

        userRepository.save(user);
    }

    public boolean checkPassword(String token, String password) {
        String user_id = jwtTokenProvider.getUserId(token);
        User user = userRepository.findByUserId(user_id);

        String password2 = user.getPassword();

        if(password.equals(password2)) {
            return true;
        } else {
            return false;
        }
    }

    public List<WineDTO> getScrap(String token){
        String userId = jwtTokenProvider.getUserId(token);
        User user = userRepository.findByUserId(userId);
        List<Scrap> scrapList = scrapRepository.findScrapByUser(user);
        List<WineDTO> wineDTOList = new ArrayList<>();

        for(Scrap scrap: scrapList){
            WineDTO wineDTO = modelMapper.map(scrap.getWine(), WineDTO.class);
            wineDTOList.add(wineDTO);
        }

        return wineDTOList;
    }

    
    public List<ReviewDTO> getReview(String token, int page){
        String userId = jwtTokenProvider.getUserId(token);
        User user = userRepository.findByUserId(userId);
        PageRequest pageRequest = PageRequest.of(page-1,5,Sort.by("time").descending());
        List<ReviewDTO> reviewDTOList = reviewRepository.findByUser(user, pageRequest).stream().map(ReviewDTO::new).collect(Collectors.toList());
       //리뷰 최신순으로 정렬시킴. 코드추가
        return reviewDTOList;
    }

    public List<FlavorDTO> getFlavor(String token){
        String userId = jwtTokenProvider.getUserId(token);
        User user = userRepository.findByUserId(userId);
        List<FlavorDTO> flavorDTOList = flavorRepository.findAll().stream().map(FlavorDTO::new)
                .collect(Collectors.toList());

        List<LikeFlavor> likeFlavorList = likeFlavorRepository.findByUser(user);
        List<DislikeFlavor> dislikeFlavorList = dislikeFlavorRepository.findByUser(user);

        for(LikeFlavor likeFlavor: likeFlavorList){
            FlavorDTO flavorDTO = modelMapper.map(likeFlavor.getFlavor(), FlavorDTO.class);
            flavorDTOList.get(flavorDTOList.indexOf(flavorDTO)).setIsLike(true);
        }

        for (DislikeFlavor dislikeFlavor : dislikeFlavorList) {
            FlavorDTO flavorDTO = modelMapper.map(dislikeFlavor.getFlavor(), FlavorDTO.class);
            flavorDTOList.get(flavorDTOList.indexOf(flavorDTO)).setIsHate(true);
        }

        return flavorDTOList;
    }

    public void updateProfile(String token, String number) {
        String user_id = jwtTokenProvider.getUserId(token);
        User user = userRepository.findByUserId(user_id);

        user.setImg(number);

        userRepository.save(user);
    }
}
