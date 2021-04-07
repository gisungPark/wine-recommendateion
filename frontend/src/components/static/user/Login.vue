<template>
  <div data-app>
    <v-row justify="center">
      <v-dialog v-model="loginDialog" persistent max-width="600px">
        <v-card height="480">
          <div id="closeBtn" @click="close">
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
          <v-card-title align="center">
            <v-row>
              <v-col>
                <span class="modal-name">Login</span>
              </v-col>
            </v-row>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col>
                  <ul>
                    <li>
                      <input
                        class="underline"
                        type="text"
                        placeholder="Email"
                        v-model="email"
                      />
                    </li>
                  </ul>
                </v-col>
              </v-row>
              <v-row>
                <v-col>
                  <ul>
                    <li>
                      <input
                        class="underline"
                        type="password"
                        placeholder="Password"
                        v-model="password"
                      />
                    </li>
                  </ul>
                </v-col>
              </v-row>
              <!-- <v-row no-gutters>
                <a id="findPw" @click="onFindPw">비밀번호 찾기</a>
              </v-row> -->
              <v-row style="margin-top: 20px">
                <v-col cols="auto" id="loginBtn-wrap">
                  <v-btn text id="loginBtn" @click="onLogin">CONTINUE</v-btn>
                </v-col>
              </v-row>
              <v-row justify="center" no-gutters>
                <v-col cols="auto" id="btn-range">OR</v-col>
              </v-row>
              <v-row justify="center" no-gutters>
                <v-col cols="auto" id="kakao-login">
                  <KakaoLogin
                    :api-key="getKakaoApiKey"
                    image="kakao_account_login_btn_medium_wide_ov"
                    :on-success="onKakaoCallback"
                    :on-failure="onFailure"
                  />
                </v-col>
              </v-row>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text id="joinBtn" @click="onJoin"
              >JOIN YourWine</v-btn
            >
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
    <JoinModal />
    <NicknameModal />
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
import * as authApi from "@/api/auth";
import KakaoLogin from "vue-kakao-login";
import JoinModal from "./Join.vue";
import NicknameModal from "./Nickname.vue";

const API_KEY = "ba1e87a869408be092569e9742130104";

export default {
  components: {
    KakaoLogin,
    JoinModal,
    NicknameModal,
  },
  data: () => ({
    email: "",
    password: "",
  }),
  computed: {
    ...mapState("userInfo", ["userInfo"]),
    ...mapState("loginDialog", [
      "loginDialog",
      "joinDialog",
      "nicknameDialog",
      "findPwDialog",
    ]),
    getKakaoApiKey() {
      return API_KEY;
    },
  },
  methods: {
    ...mapMutations("loginDialog", [
      "SET_LOGIN_TOGGLE",
      "SET_JOIN_TOGGLE",
      "SET_NICKNAME_TOGGLE",
      "SET_FINDPW_TOGGLE",
    ]),
    ...mapMutations("userInfo", ["SET_TEMP_TOKEN"]),
    ...mapMutations("guideBtn", [
      "SET_GUIDEBTN_TOGGLE",
      "SET_GUIDEBTNTIP_TOGGLE",
    ]),
    onLogin() {
      const response = this.$store
        .dispatch("userInfo/login", {
          email: this.email,
          password: this.password,
        })
        .then((result) => {
          console.log(result);
          if (result.data.code === 0) {
            // 로그인 완료 ##################################
            alert(this.email + "님 환영합니다.");
            this.close();
            this.SET_GUIDEBTN_TOGGLE();
            this.$router.push({ name: "Mypage" });
          } else {
            alert("아이디 비밀번호를 확인하세요!!");
          }
        });
    },
    onFindPw() {
      this.SET_NICKNAME_TOGGLE();
    },
    close() {
      this.email = "";
      this.password = "";
      this.SET_LOGIN_TOGGLE();
    },
    async onKakaoCallback(data) {
      console.log("1111111111111111111111111");
      const response = this.$store
        .dispatch("userInfo/kakaoLogin", {
          data: data,
        })
        .then((result) => {
          console.log("333333333333333333333333");
        });
      // const response = await authApi.kakaoLogin(data);
      // console.log("카카오 로그인 시작!!");
      // console.log(response);
    },
    onFailure() {},
    onJoin() {
      this.SET_JOIN_TOGGLE();
    },
  },
};
</script>

<style scoped>
.modal-name {
  font-size: 32px;
  font-weight: bold;
  margin: 0 auto;
}
ul {
  margin-left: 35px;
  margin-bottom: 5px;
}
li > input {
  width: 90%;
  font-size: 22px;
}
li > span {
  font-size: 12px;
  color: #f44336;
}
.underline {
  border: 1px solid black;
  border-top-width: 0px;
  border-left-width: 0px;
  border-right-width: 0px;
  border-bottom-width: 1px;
}
#findPw {
  position: relative;
  top: 3px;
  left: 390px;
}
#closeBtn {
  position: absolute;
  right: 15px;
  margin-top: 5px;
  cursor: pointer;
}
#loginBtn-wrap {
  margin: 0 auto;
}
#loginBtn {
  width: 300px;
  height: 49px;
  background-color: var(--basic-color-btn);
  color: white;
}
#btn-range {
  font-size: 20px;
  font-weight: bold;
  margin: 8px auto;
}
#kakao-login {
  margin: 0 auto;
}
#joinBtn {
  font-size: 15px;
}
</style>