package com.ssafy.yourwine.service;

import com.ssafy.yourwine.config.security.JwtTokenProvider;
import com.ssafy.yourwine.model.dto.SignInDTO;
import com.ssafy.yourwine.model.dto.TokenResultDTO;
import com.ssafy.yourwine.model.dto.UserDTO;
import com.ssafy.yourwine.model.entity.User;
import com.ssafy.yourwine.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final KakaoService kakaoService;

	public UserService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, KakaoService kakaoService) {
		this.userRepository = userRepository;
		this.jwtTokenProvider = jwtTokenProvider;
		this.kakaoService = kakaoService;
	}
	
    public void saveUser(UserDTO userDTO) {
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setNickname(userDTO.getNickname());
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

    public TokenResultDTO signIn(SignInDTO signInDTO) {
        User user = userRepository.findByEmailAndPassword(signInDTO.getEmail(), signInDTO.getPassword());
        TokenResultDTO tokenResultDTO = new TokenResultDTO();

        if(user != null){
            tokenResultDTO.setToken(jwtTokenProvider.generateToken(user.getUserId()));
            tokenResultDTO.setNickname(user.getNickname());
            tokenResultDTO.setCorrect(true);

            user.setToken(tokenResultDTO.getToken());

            userRepository.save(user);
        } else {
            tokenResultDTO.setCorrect(false);
        }

        return tokenResultDTO;
    }

    public void logOut(String token) {
        User user = userRepository.findByToken(token);

        user.setToken(null);

        userRepository.save(user);
    }

    public TokenResultDTO checkUser(String kakaotoken) {
        String user_id = UUID.randomUUID().toString();
        String uid = kakaoService.getKakaoProfile(kakaotoken);
        String token = jwtTokenProvider.generateToken(user_id);

        User user = userRepository.findByEmailAndProvider(uid, 1);

        TokenResultDTO tokenResultDTO = new TokenResultDTO();

        if(uid != null) {
            if (user != null) {
                if (user.getFlag()) {
                    tokenResultDTO.setToken(token);
                    tokenResultDTO.setNickname(user.getNickname());
                    tokenResultDTO.setCorrect(true);

                    return tokenResultDTO;
                } else {
                    tokenResultDTO.setToken(token);
                    tokenResultDTO.setCorrect(false);

                    return tokenResultDTO;
                }
            } else {
                user = new User();

                user.setUserId(user_id);
                user.setEmail(uid);
                user.setProvider(1);
                user.setToken(token);

                userRepository.save(user);

                tokenResultDTO.setToken(token);
                tokenResultDTO.setCorrect(false);

                return tokenResultDTO;
            }
        } else {
            tokenResultDTO.setCorrect(false);

            return tokenResultDTO;
        }
    }

    public TokenResultDTO updateUser(String token, String nickname) {
	    String user_id = jwtTokenProvider.getUserId(token);
        User user = userRepository.findByUserId(user_id);
        String new_token = jwtTokenProvider.generateToken(user_id);

        user.setNickname(nickname);
        user.setToken(new_token);
        user.setFlag(true);

        userRepository.save(user);

        TokenResultDTO tokenResultDTO = new TokenResultDTO();

        tokenResultDTO.setToken(new_token);
        tokenResultDTO.setNickname(nickname);
        tokenResultDTO.setCorrect(true);

        return tokenResultDTO;
    }
}
