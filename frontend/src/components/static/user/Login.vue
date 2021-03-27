<template>
  <div data-app>
    <v-row justify="center">
      <v-dialog v-model="loginDialog" persistent max-width="600px">
        <v-card>
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
                      />
                    </li>
                  </ul>
                </v-col>
              </v-row>
              <v-row style="margin-top: 30px">
                <v-col cols="auto" id="loginBtn-wrap">
                  <v-btn text id="loginBtn" @click="onLogin">CONTINUE</v-btn>
                </v-col>
              </v-row>
              <v-row justify="center">
                <v-col cols="auto" id="btn-range">OR</v-col>
              </v-row>
              <v-row justify="center">
                <v-col cols="auto" id="kakao-login">
                  <KakaoLogin
                    :api-key="getKakaoApiKey"
                    image="kakao_account_login_btn_medium_wide_ov"
                    :on-success="onSuccess"
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
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import KakaoLogin from "vue-kakao-login";
import { login } from "@/api/login.js";

const API_KEY = "ba1e87a869408be092569e9742130104";

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
.modal-name {
  font-size: 32px;
  font-weight: bold;
  margin: 0 auto;
}
ul {
  margin-left: 15px;
  margin-bottom: 10px;
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
  height: 42px;
  background-color: var(--basic-color-bg2);
  color: white;
}
#btn-range {
  font-size: 22px;
  font-weight: bold;
  margin: 0 auto;
}
#kakao-login {
  margin: 0 auto;
}
#joinBtn {
  font-size: 15px;
}
</style>