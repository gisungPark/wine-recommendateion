<template>
  <div data-app>
    <v-row justify="center">
      <v-dialog v-model="joinDialog" persistent max-width="600px">
        <v-card height="480">
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
          <v-card-title align="center">
            <v-row>
              <v-col>
                <span class="modal-name">JOIN</span>
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
                        maxlength="20"
                        @blur="emailCheck"
                      />
                    </li>
                    <li>
                      <span>{{ this.msg.emailMsg }}</span>
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
                        maxlength="20"
                        @blur="pwCheck"
                      />
                    </li>
                    <li>
                      <span>{{ this.msg.passwordMsg }}</span>
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
                        placeholder="Pasword check"
                        v-model="passwordConfirm"
                        maxlength="20"
                        @blur="pwConfirmCheck"
                      />
                    </li>
                    <li>
                      <span>{{ this.msg.passwordCheckMsg }}</span>
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
                        type="text"
                        placeholder="Nickname"
                        v-model="nickname"
                        maxlength="20"
                        @blur="nicknameCheck"
                      />
                    </li>
                    <li>
                      <span>{{ this.msg.nicknameMsg }}</span>
                    </li>
                  </ul>
                </v-col>
              </v-row>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text id="joinBtn" @click="join">
              JOIN
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import * as authApi from "@/api/auth";

export default {
  data: () => ({
    email: "",
    password: "",
    passwordConfirm: "",
    nickname: "",
    msg: {
      emailMsg: "",
      passwordMsg: "",
      passwordCheckMsg: "",
      nicknameMsg: "",
    },
  }),
  computed: {
    ...mapState("loginDialog", ["joinDialog"]),
    passwordConfirmationRule() {
      return () =>
        this.password === this.passwordConfirm ||
        "비밀번호가 일치하지 않습니다.";
    },
  },
  methods: {
    ...mapMutations("loginDialog", ["SET_JOIN_TOGGLE"]),
    close() {
      this.SET_JOIN_TOGGLE();
    },
    join() {},

    // #########################################################
    //start 입력형식 체크
    emailCheck() {
      const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      if (!pattern.test(this.email))
        this.msg.emailMsg = "이메일 형식이 올바르지 않습니다.";
      else {
        this.msg.emailMsg = "";
        // ##################### email 중복체크 #####################
        authApi
          .duplicate(this.email)
          .then((response) => {
            console.log(response);
            // #######################################

            // #######################################
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    pwCheck() {
      if (this.password.length < 5)
        this.msg.passwordMsg = "비밀번호 최소 길이는 5자 입니다.";
      else this.msg.passwordMsg = "";
    },
    pwConfirmCheck() {
      if (this.password !== this.passwordConfirm)
        this.msg.passwordCheckMsg = "비밀번호가 일치하지 않습니다.";
      else this.msg.passwordCheckMsg = "";
    },
    nicknameCheck() {
      this.msg.nicknameMsg = "이미 존재하는 닉네임입니다.";
    },
    //end 입력형식 체크
    // #########################################################
  },
};
</script>

<style scoped>
.modal-name {
  font-size: 32px;
  font-weight: bold;
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

#joinBtn {
  position: absolute;
  right: 15px;
  bottom: 15px;
  font-size: 17px;
}
</style>