package com.ssafy.yourwine.service;

import com.ssafy.yourwine.config.security.JwtTokenProvider;
import com.ssafy.yourwine.model.dto.*;
import com.ssafy.yourwine.model.entity.Review;
import com.ssafy.yourwine.model.entity.Scrap;
import com.ssafy.yourwine.model.entity.User;
import com.ssafy.yourwine.repository.ReviewRepository;
import com.ssafy.yourwine.repository.ScrapRepository;
import com.ssafy.yourwine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper modelMapper = new ModelMapper();
	private final UserRepository userRepository;
	private final ScrapRepository scrapRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final KakaoService kakaoService;
	private final ReviewRepository reviewRepository;
	
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
        String userId = UUID.randomUUID().toString();
        String uid = kakaoService.getKakaoProfile(kakaotoken);
        String token = jwtTokenProvider.generateToken(userId);

        User user = userRepository.findByEmailAndProvider(uid, 1);

        TokenResultDTO tokenResultDTO = new TokenResultDTO();

        if(uid != null) {
            if (user != null) {
                if (user.getFlag()) {
                    tokenResultDTO.setToken(token);
                    tokenResultDTO.setNickname(user.getNickname());
                    tokenResultDTO.setCode(2);

                    return tokenResultDTO;
                } else {
                    tokenResultDTO.setToken(token);
                    tokenResultDTO.setCode(3);

                    return tokenResultDTO;
                }
            } else {
                user = new User();

                user.setUserId(userId);
                user.setEmail(uid);
                user.setProvider(1);
                user.setToken(token);

                userRepository.save(user);

                tokenResultDTO.setToken(token);
                tokenResultDTO.setCode(3);

                return tokenResultDTO;
            }
        } else {
            tokenResultDTO.setCode(4);

            return tokenResultDTO;
        }
    }

    public TokenResultDTO updateUser(String token, String nickname) {
	    String user_id = jwtTokenProvider.getUserId(token);
        User user = userRepository.findByUserId(user_id);
        User user2 = userRepository.findByNickname(nickname);
        TokenResultDTO tokenResultDTO = new TokenResultDTO();

        if(user2 != null) {
            String new_token = jwtTokenProvider.generateToken(user_id);

            user.setToken(new_token);

            userRepository.save(user);

            tokenResultDTO.setToken(new_token);
            tokenResultDTO.setCode(6);

        } else {
            user.setNickname(nickname);
            user.setToken(null);
            user.setFlag(true);

            userRepository.save(user);

            tokenResultDTO.setCode(5);

        }

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

    public List<ReviewDTO> getReview(String token){
        String userId = jwtTokenProvider.getUserId(token);
        User user = userRepository.findByUserId(userId);
        List<ReviewDTO> reviewDTOList = reviewRepository.findByUser(user).stream().map(ReviewDTO::new).collect(Collectors.toList());

        return reviewDTOList;
    }
}
