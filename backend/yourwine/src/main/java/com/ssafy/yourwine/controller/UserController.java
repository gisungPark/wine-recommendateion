package com.ssafy.yourwine.controller;

import com.ssafy.yourwine.model.dto.SignInDTO;
import com.ssafy.yourwine.model.dto.TokenResultDTO;
import com.ssafy.yourwine.model.dto.UserDTO;
import com.ssafy.yourwine.service.UserService;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService=userService;
	}
	 
	@PostMapping("/signup")
	@ApiOperation(value = "일반 회원 가입", notes="Parameter\n" +
			"- UserDTO(RequestBody)\n" +
			"-- email: 이메일\n" +
			"-- password: 비밀번호\n" +
			"-- nickname: 닉네임\n" +
			"Respnse(x)")
	public void signUp(@RequestBody UserDTO userDTO){
		userService.saveUser(userDTO);
	}

	@GetMapping("/checkemail/{email}")
	@ApiOperation(value = "이메일 중복 체크", notes="Parameter\n" +
			"- email(PathVariable): 이메일\n" +
			"Respnse\n" +
			"- check: 중복인지 아닌지(true 중복x false 중복o")
	public Boolean checkEmail(@PathVariable String email){
		boolean check = userService.checkEmail(email);

		return check;
	}

	@GetMapping("/checknickname/{nickname}")
	@ApiOperation(value = "닉네임 중복 체크", notes="Parameter\n" +
			"- nickname(PathVariable): 닉네임\n" +
			"Respnse\n" +
			"- check: 중복인지 아닌지(true 중복x false 중복o")
	public Boolean checkNickname(@PathVariable String nickname){
		boolean check = userService.checkNickname(nickname);

		return check;
	}

	@PutMapping("/login")
	@ApiOperation(value = "일반 로그인", notes="Parameter\n" +
			"- SignInDTO(RequestBody)\n" +
			"-- email: 이메일(아이디)\n" +
			"-- password: 비밀번호\n" +
			"Respnse\n" +
			"- TokenResultDTO\n" +
			"-- token: 액세스 토큰\n" +
			"-- nickname: 닉네임(로그인한 유저 닉네임)\n" +
			"-- code: 로그인 성공(0) 로그인 실패(1: 없는 계정 or 틀린 비밀번호)")
	public TokenResultDTO logIn(@RequestBody SignInDTO signInDTO){
		TokenResultDTO tokenResultDTO = userService.logIn(signInDTO);

		return tokenResultDTO;
	}

	@PutMapping("/logout")
	@ApiOperation(value = "로그아웃", notes="Parameter\n" +
			"- token(RequestHeader): 액세스 토큰\n" +
			"Respnse(x)\n")
	public void logOut(@RequestHeader("TOKEN") String token){
		userService.logOut(token);
	}

	@PostMapping("/checkUser")
	@ApiOperation(value = "소셜 로그인 회원 체크", notes="Parameter\n" +
			"- token: 카카오 토큰(RequestParam)\n" +
			"Respnse\n" +
			"- TokenResultDTO\n" +
			"-- token: 액세스 토큰\n" +
			"-- nickname: 닉네임(로그인한 유저 닉네임)\n" +
			"-- code: 소셜 로그인 회원 맞을 경우(2) 소셜 로그인 회원 아닐 경우(3) 보낸 카카오 토큰 자체가 유효 x(4)")
	public TokenResultDTO checkUser(@RequestParam String token){
		TokenResultDTO tokenResultDTO = userService.checkUser(token);

		return tokenResultDTO;
	}

	@PutMapping("/updateUser")
	@ApiOperation(value = "소셜 추가 정보 입력(회원 가입 or 닉네임 변경)", notes="Parameter\n" +
			"- token(RequestHeader): 액세스 토큰\n" +
			"- nickname(RequestParam): 사용할 닉네임 입력\n" +
			"Respnse\n" +
			"- TokenResultDTO\n" +
			"-- token: 액세스 토큰\n" +
			"-- nickname: 닉네임(로그인한 유저 닉네임)\n" +
			"-- code: 추가 입력폼 정상 적용(5) 추가 입력폼 적용 실패(6: 닉네임 중복, 추가 입력폼 다시 입력)")
	public TokenResultDTO updateUser(@RequestHeader("TOKEN") String token, @RequestParam String nickname){
		TokenResultDTO tokenResultDTO = userService.updateUser(token, nickname);

		return tokenResultDTO;
	}

	@GetMapping("/getUserInfo")
	@ApiOperation(value = "회원 정보 가져오기(본인)", notes="Parameter\n" +
			"- token(RequestHeader): 액세스 토큰\n" +
			"Respnse\n" +
			"- UserDTO\n" +
			"-- email: 이메일\n" +
			"-- nickname: 닉네임\n" +
			"-- img: 이미지 경로\n" +
			"-- provider: 공급자(0은 일반회원 1은 카카오)")
	public UserDTO getUserInfo(@RequestHeader("TOKEN") String token){
		UserDTO userDTO = userService.getUserInfo(token);

		return userDTO;
	}

	@PutMapping("/updatePassword")
	@ApiOperation(value = "비밀번호 수정(일반 회원)", notes="Parameter\n" +
			"- token(RequestHeader): 액세스 토큰\n" +
			"- password(RequestParam): 비밀번호\n" +
			"Respnse(x)\n")
	public void update(@RequestHeader("TOKEN") String token, @RequestParam String password){
		userService.updatePassword(token, password);
	}

	@GetMapping("/checkPassword")
	@ApiOperation(value = "본인 확인(비밀번호를 통해)", notes="Parameter\n" +
			"- token(RequestHeader): 액세스 토큰\n" +
			"- password(RequestParam): 비밀번호\n" +
			"Respnse\n" +
			"- check: 비밀번호 맞을때(true), 틀릴때(false)")
	public boolean checkPassword(@RequestHeader("TOKEN") String token, @RequestParam String password){
		boolean check = userService.checkPassword(token, password);

		return check;
	}

}
