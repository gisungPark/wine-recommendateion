package com.ssafy.yourwine.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.yourwine.model.dto.PreferenceDTO;
import com.ssafy.yourwine.model.dto.WineDTO;
import com.ssafy.yourwine.service.FoodService;
import com.ssafy.yourwine.service.RecoService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reco")
@RequiredArgsConstructor
public class RecoController {

    private final RecoService recoService;
    private final FoodService foodService;

    @GetMapping("/checkpreference")
    @ApiOperation(value = "선호도 입력여부 체크", notes="Parameter\n" +
            "- token(RequestHeader): 액세스 토큰\n" +
            "Respnse\n" +
            "- check: 입력 했을 경우(true), 입력 안했을 경우(false)\n")
    public boolean checkPreference(@RequestHeader("TOKEN") String token){
        boolean check = recoService.checkPreference(token);

        return check;
    }

    @PutMapping("/updatePreference")
    @ApiOperation(value = "선호도 변경", notes="Parameter\n" +
            "- token(RequestHeader): 액세스 토큰\n" +
            "- PreferenceDTO\n" +
            "-- flavorDTO\n" +
            "--- sweet: 당도\n" +
            "--- body: 바디감\n" +
            "--- tannin: 탄닌\n" +
            "--- acidity: 산도\n" +
            "-- likeList(List(FlavorDTO)): 좋아하는 향 ID 리스트\n" +
            "--- flavorId: 향 ID\n" +
            "--- flavorName: 향 이름\n" +
            "--- flavorImg: 향 이미지 경로\n" +
            "-- dislikeList(List(FlavorDTO)): 싫어하는 향 ID 리스트\n" +
            "--- flavorId: 향 ID\n" +
            "--- flavorName: 향 이름\n" +
            "--- flavorImg: 향 이미지 경로\n" +
            "Respnse(x)\n")
    public void updatePreference(@RequestHeader("TOKEN") String token, @RequestBody PreferenceDTO preferenceDTO){
        recoService.updatePreference(token, preferenceDTO);
    }

    @PostMapping("/savePreference")
    @ApiOperation(value = "선호도 입력", notes="Parameter\n" +
            "- token(RequestHeader): 액세스 토큰\n" +
            "- PreferenceDTO\n" +
            "-- flavorDTO\n" +
            "--- sweet: 당도\n" +
            "--- body: 바디감\n" +
            "--- tannin: 탄닌\n" +
            "--- acidity: 산도\n" +
            "-- likeList(List(FlavorDTO)): 좋아하는 향 ID 리스트\n" +
            "--- flavorId: 향 ID\n" +
            "--- flavorName: 향 이름\n" +
            "--- flavorImg: 향 이미지 경로\n" +
            "-- dislikeList(List(FlavorDTO)): 싫어하는 향 ID 리스트\n" +
            "--- flavorId: 향 ID\n" +
            "--- flavorName: 향 이름\n" +
            "--- flavorImg: 향 이미지 경로\n" +
            "Respnse(x)\n")
    public void savePreference(@RequestHeader("TOKEN") String token, @RequestBody PreferenceDTO preferenceDTO){
        recoService.savePreference(token, preferenceDTO);
    }

    @PostMapping("/getPreference")
    @ApiOperation(value = "선호도 가져오기", notes="Parameter\n" +
            "- token(RequestHeader): 액세스 토큰\n" +
            "Respnse(x)\n" +
            "- PreferenceDTO\n" +
            "-- flavorDTO\n" +
            "--- sweet: 당도\n" +
            "--- body: 바디감\n" +
            "--- tannin: 탄닌\n" +
            "--- acidity: 산도\n" +
            "-- likeList(List(FlavorDTO)): 좋아하는 향 ID 리스트\n" +
            "--- flavorId: 향 ID\n" +
            "--- flavorName: 향 이름\n" +
            "--- flavorImg: 향 이미지 경로\n" +
            "-- dislikeList(List(FlavorDTO)): 싫어하는 향 ID 리스트\n" +
            "--- flavorId: 향 ID\n" +
            "--- flavorName: 향 이름\n" +
            "--- flavorImg: 향 이미지 경로\n")
    public PreferenceDTO getPreference(@RequestHeader("TOKEN") String token){
        return recoService.getPreference(token);
    }
    
    @ApiOperation(value = "음식 매칭 와인리스트", notes = "음식ID에 매칭되는 와인 리뷰 리스트.1페이지당 아이템 10개씩 줌.리스트는 평점높은순으로 없을 경우 no food data 에러발생")
    @GetMapping("/food-recolist/{foodId}")
    public List<WineDTO> getWineListByFood (@PathVariable Long foodId, @RequestParam int page){
    	return foodService.getWineListByFood(foodId, page);
    }
    
    //선호도 추천 테스트중
    @GetMapping("/preference-recolist")
    public void test(@RequestHeader("TOKEN") String token) {
    	recoService.getWineListByPreference(token);
    }

    @GetMapping("/getTopten/{min}/{max}")
    @ApiOperation(value = "Top 10 가져오기", notes="Parameter\n" +
            "- min(PathVariable): 가격 최소 값 범위\n" +
            "- max(PathVariable): 가격 최대 값 범위\n" +
            "Respnse(x)\n" +
            "- PreferenceDTO\n" +
            "--- flavorImg: 향 이미지 경로\n")
    public List<WineDTO> getTopten(@PathVariable Integer min, @PathVariable Integer max){
        return recoService.getTopten(min, max);
    }

    @GetMapping("/getTodayWine")
    @ApiOperation(value = "오늘의 와인 가져오기", notes="Parameter(x)\n" +
            "Respnse(x)\n" +
            "- PreferenceDTO\n" +
            "--- flavorImg: 향 이미지 경로\n")
    public WineDTO getTodayWine(){
        return recoService.getTodayWine();
    }

}
