<template>
  <div data-app>
    <v-row justify="center">
      <v-dialog v-model="loginDialog" persistent max-width="600px">
        <v-card>
          <div
            style="
              position: absolute;
              right: 15px;
              margin-top: 15px;
              cursor: pointer;
            "
            @click="close"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="24"
              height="24"
              viewBox="0 0 24 24"
            >
              <path
                d="M24 20.188l-8.315-8.209 8.2-8.282-3.697-3.697-8.212
                  8.318-8.31-8.203-3.666 3.666 8.321 8.24-8.206 8.313 3.666 3.666 8.237-8.318 8.285 8.203z"
              />
            </svg>
          </div>
          <v-card-title>
            <span class="headline">Login</span>
          </v-card-title>
          <v-card-text>
            <v-container justify-content: center;>
              <v-col cols="12" sm="6" md="4">
                <input v-model="userId" placeholder="ID" />
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <input v-model="userPw" placeholder="PW" />
              </v-col>
              <!-- STRART ################################# -->
              <!-- 로그인 버튼 중앙 정렬 필요 -->
              <v-row>
                <div class="btnBar">
                  <v-btn
                    id="loginBtn"
                    style="width: 200px"
                    text
                    @click="onLogin"
                  >
                    CONTINUE
                  </v-btn>
                </div>
              </v-row>
              <!-- END ################################# -->
              <v-spacer></v-spacer>
              <v-col cols="12">
                <div
                  style="
                    font-size: 30px;
                    font-weight: bold;
                    margin: 10px 10px;
                    text-align: center;
                  "
                >
                  <span>OR</span>
                </div>
              </v-col>
              <v-spacer></v-spacer>
              <v-row>
                <div class="btnBar">
                  <KakaoLogin
                    :api-key="getKakaoApiKey"
                    :on-success="onSuccess"
                    :on-failure="onFailure"
                  />
                </div>
              </v-row>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="onJoin">
              JOIN YourWine
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import KakaoLogin from "vue-kakao-login";
import { login } from "@/api/login.js";

const API_KEY = process.env.VUE_APP_KAKAO_LOGIN_API_KEY;

export default {
  components: {
    KakaoLogin,
  },
  data: () => ({
    userId: "",
    userPw: "",
  }),
  computed: {
    ...mapState("loginDialog", ["loginDialog", "joinDialog"]),
    getKakaoApiKey() {
      return API_KEY;
    },
  },
  methods: {
    ...mapMutations("loginDialog", ["SET_LOGIN_TOGGLE", "SET_JOIN_TOGGLE"]),
    ...mapMutations("guideBtn", [
      "SET_GUIDEBTN_TOGGLE",
      "SET_GUIDEBTNTIP_TOGGLE",
    ]),

    onLogin() {
      const success = (response) => {
        //1. respose.code
        switch (response.code) {
          case 0: // 로그인 성공 => 토큰, 닉네임 저장
            break;
          case 1: // 로그인 실패 => 토큰, 닉네임 저장 x
            break;
          case 2: // 소셜 로그인 성공 => 토큰, 닉네임 저장
            break;
          case 3: // 소셜 최초 로그인 => 회원 가입 이동
            break;
          case 4: // 토큰 유효성 검사 실패
            break;
          case 5: // 정상 가입 완료
            break;
          case 6: // 가입 실패
            break;
        }
      };
      const fail = {};
      const userInfo = {};

      login(userInfo, success, fail);

      this.SET_LOGIN_TOGGLE();
      this.SET_GUIDEBTN_TOGGLE();
      this.$router.push({ name: "Mypage" });
    },

    close() {
      this.SET_LOGIN_TOGGLE();
    },
    onSuccess() {
      alert("로그인 성공");
    },
    onFailure() {},
    onJoin() {
      console.log("click join btn!!");
      this.SET_JOIN_TOGGLE();
    },
  },
};
</script>

<style scoped>
input {
  margin-left: 50px;
  height: 20px;
  font-size: 20px;
}

.btnBar {
  cursor: pointer;
  border-radius: 25px;
  height: 49px;
  margin: auto;
}

#loginBtn {
  color: white;
  background-color: #821a33;
}
.headline {
  font-size: 40px;
  font-weight: bold;
  padding-top: 5px;
  margin: 0 auto;
}
</style>