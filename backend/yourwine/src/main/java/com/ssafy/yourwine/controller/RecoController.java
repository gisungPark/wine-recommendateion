package com.ssafy.yourwine.controller;

import com.ssafy.yourwine.model.dto.PreferenceDTO;
import com.ssafy.yourwine.service.RecoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reco")
@RequiredArgsConstructor
public class RecoController {

    private final RecoService recoService;

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
}
