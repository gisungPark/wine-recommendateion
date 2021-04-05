package com.ssafy.yourwine.controller;

import com.ssafy.yourwine.model.dto.*;
import com.ssafy.yourwine.service.StatisticService;
import com.ssafy.yourwine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final StatisticService statisticService;
	 
	@PostMapping("/signup")
	@ApiOperation(value = "일반 회원 가입", notes="Parameter\n" +
			"- UserDTO(RequestBody)\n" +
			"-- email: 이메일\n" +
			"-- password: 비밀번호\n" +
			"-- nickname: 닉네임\n" +
			"Response(x)")
	public void signUp(@RequestBody UserDTO userDTO){
		userService.saveUser(userDTO);
	}

	@GetMapping("/checkemail/{email}")
	@ApiOperation(value = "이메일 중복 체크", notes="Parameter\n" +
			"- email(PathVariable): 이메일\n" +
			"Response\n" +
			"- check: 중복인지 아닌지(true 중복x false 중복o")
	public Boolean checkEmail(@PathVariable String email){
		boolean check = userService.checkEmail(email);

		return check;
	}

	@GetMapping("/checknickname/{nickname}")
	@ApiOperation(value = "닉네임 중복 체크", notes="Parameter\n" +
			"- nickname(PathVariable): 닉네임\n" +
			"Response\n" +
			"- check: 중복인지 아닌지(true 중복x false 중복o")
	public Boolean checkNickname(@PathVariable String nickname){
		boolean check = userService.checkNickname(nickname);

		return check;
	}

	@PostMapping("/login")
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

	@PostMapping("/logout")
	@ApiOperation(value = "로그아웃", notes="Parameter\n" +
			"- token(RequestHeader): 액세스 토큰\n" +
			"Response(x)\n")
	public void logOut(@RequestHeader("TOKEN") String token){
		userService.logOut(token);
	}

	@PostMapping("/checkUser")
	@ApiOperation(value = "소셜 로그인 회원 체크", notes="Parameter\n" +
			"- token: 카카오 토큰(RequestParam)\n" +
			"Response\n" +
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
			"Response\n" +
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
			"Response\n" +
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
			"Response(x)\n")
	public void update(@RequestHeader("TOKEN") String token, @RequestParam String password){
		userService.updatePassword(token, password);
	}

	@GetMapping("/checkPassword")
	@ApiOperation(value = "본인 확인(비밀번호를 통해)", notes="Parameter\n" +
			"- token(RequestHeader): 액세스 토큰\n" +
			"- password(RequestParam): 비밀번호\n" +
			"Response\n" +
			"- check: 비밀번호 맞을때(true), 틀릴때(false)")
	public boolean checkPassword(@RequestHeader("TOKEN") String token, @RequestParam String password){
		boolean check = userService.checkPassword(token, password);

		return check;
	}

	@GetMapping("/getScrap")
	@ApiOperation(value = "나의 찜하기 리스트", notes="Parameter\n" +
			"- token(RequestHeader): 액세스 토큰\n" +
			"Response\n" +
			"")
	public List<WineDTO> getScrap(@RequestHeader("TOKEN") String token) {
		List<WineDTO> wineDTOList = userService.getScrap(token);

		return wineDTOList;
	}

	@GetMapping("/getReview")
	@ApiOperation(value = "나의 리뷰 리스트", notes="Parameter\n" +
			"- token(RequestHeader): 액세스 토큰\n" +
			"Response\n" +
			"")
	public List<ReviewDTO> getReview(@RequestHeader("TOKEN") String token, @RequestParam int page) {
		List<ReviewDTO> reviewDTOList = userService.getReview(token,page);

		return reviewDTOList;
	}

	@GetMapping("/getFlavor")
	@ApiOperation(value = "향 리스트", notes="Parameter\n" +
			"- token(RequestHeader): 액세스 토큰\n" +
			"Response\n" +
			"")
	public List<FlavorDTO> getFlavor(@RequestHeader("TOKEN") String token) {
		List<FlavorDTO> flavorDTOList = userService.getFlavor(token);

		return flavorDTOList;
	}

	/** 
	 * 통계
	 * */
	
	@GetMapping("/statistics/pre")
	@ApiOperation(value = "선호하는 품종, 와인종류에 대한 통계", notes="- **성공시, 와인종류 갯수, 품종id별 갯수 리스트 제공**  \n"+
	"- **통계는 리뷰데이터 기반으로 이루어지기 때문에 리뷰데이터 없을 경우 writeReview = false 반환 -> 리뷰작성 유도 멘트/ 리스트 null**  \n"+
	"- **와인종류는 redCnt,whiteCnt,spaclingCnt에 담아 갯수를 반환합니다.** \n"+
	"- **품종은 grapeCntList 배열에 갯수를 세서 담아서 드립니다. 배열의 인덱스는 품종 번호입니다. 품종은 총 10가지이며, 배열의 인덱스는 0~10까지 있습니다. 그리고 인덱스는 DB의 grape_id 순서와 일치합니다.** \n"
	+"- **0번째 인덱스는 아무값도 없고, 1부터~10까지가 품종번호입니다. 인덱스 = 품종번호, 그 안의 값이 품종별 갯수입니다 **")
	public StatisticsByPreDTO getStatisticsByPre (@RequestHeader("TOKEN") String token) {
		return statisticService.getWineListByUser(token);
	}
	
	@GetMapping("/statistics/taste")
	@ApiOperation(value = "와인향, 입맛에 대한 통계", notes="- **성공시, 4가지맛에 대한 평균값, 향id별 갯수 리스트 제공 / **  \n"+ "- **통계는 리뷰데이터 기반으로 이루어지기 때문에 리뷰데이터 없을 경우 writeReview = false 반환 -> 리뷰작성 유도 멘트/리스트 null **  \n"
			+"- **입맛에 대한 통계는  sweetAvg, tanninAvg, bodyAvg, acidityAvg에 유저의 입맛 평균치를 계산해드립니다. (소수 둘째자리까지, 셋째자리는 반올림) ex) 2.54** \n"+
			"- **향은 flavorCntList 배열에 갯수를 세서 담아드립니다. 배열의 인덱스는 향 번호입니다. 향은 총 18가지이며, 배열의 인덱스는 0~18까지 있습니다. 그리고 인덱스는 DB의 flavor_id 순서와 일치합니다.** \n"
			+"- **0번째 인덱스는 아무값도 없고, 1부터~18까지가 향번호입니다. 인덱스 = 향번호, 그 안의 값이 향별 갯수입니다 **")
			
	public StatisticsByTasteDTO getStatisticsByTaste (@RequestHeader("TOKEN") String token) {
		return statisticService.getWineListByTaste(token);
	}
	
	
	@GetMapping("/statistics/similar")
	@ApiOperation(value = "유사유저 높게준, 낮게준 평점 기반 통계", notes="- **성공시, 유사유저 평점 높게준, 낮게준 와인 리스트 5개 제공 순서대로가 1,2,3,4,5 순위입니다. **  \n"+"- **통계는 리뷰데이터 기반으로 이루어지기 때문에 리뷰데이터 없을 경우 writeReview = false 반환 -> 리뷰작성 유도 멘트/리스트 null**  \n"
			+ "**유저유사도 관련 추천은 1시간마다 배치작업으로 이루어집니다. 배치작업이 완료된 후에 DB에 들어가게 됩니다. 그리고 해당 통계 리스트는 DB에서 조회합니다**\n"
			+ "**따라서, 리뷰를 작성했더라도 바로 DB에 통계값이 들어가는 것이 아니므로 batchData라는 변수를 추가했습니다.** \n"
			+ "**batchData가 false라면 해당 리스트를 제공할 수 없으므로 프론트에서 별도의 멘트로 안내해야합니다.**\n")
	public StatisticsBySimilarDTO getStatisticsBySimilar (@RequestHeader("TOKEN") String token) {
		return statisticService.getWineListBySimilar(token);
	}
}
