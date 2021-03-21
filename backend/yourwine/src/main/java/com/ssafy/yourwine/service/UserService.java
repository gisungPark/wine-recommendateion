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
                    tokenResultDTO.setCode(2);

                    return tokenResultDTO;
                } else {
                    tokenResultDTO.setToken(token);
                    tokenResultDTO.setCode(3);

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

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setNickname(user.getNickname());
        userDTO.setImg(user.getImg());
        userDTO.setProvider(user.getProvider());

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
}
