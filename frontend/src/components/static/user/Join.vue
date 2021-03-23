<template>
  <div data-app>
    <v-row justify="center">
      <v-dialog v-model="joinDialog" persistent max-width="600px">
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
            <span class="headline">JOIN</span>
          </v-card-title>
          <v-card-text>
            <v-container justify-content: center;>
              <v-layout column>
                <v-flex>
                  <v-text-field
                    :rules="rules"
                    placeholder="ID"
                    maxlength="20"
                    v-model="userId"
                  ></v-text-field>
                </v-flex>
                <v-flex>
                  <v-text-field
                    :rules="[rules.required, rules.min]"
                    name="input-10-2"
                    :type="'password'"
                    placeholder="Password"
                    hint="최소 길이는 8자 입니다."
                    maxlength="20"
                    v-model="password"
                  ></v-text-field>
                </v-flex>
                <v-flex>
                  <v-text-field
                    :rules="[
                      rules.required,
                      rules.min,
                      passwordConfirmationRule,
                    ]"
                    name="input-10-2"
                    :type="'password'"
                    placeholder="Password Check"
                    maxlength="20"
                    v-model="passwordCheck"
                  ></v-text-field>
                </v-flex>
                <v-flex>
                  <v-text-field
                    placeholder="NickName"
                    maxlength="20"
                    v-model="nickname"
                  ></v-text-field>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="join"> JOIN </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";

export default {
  data: () => ({
    userId: "",
    password: "",
    passwordCheck: "",
    nickname: "",
    rules: [
      (value) => !!value || "필수 요소입니다.",
      (value) => (value || "").length <= 20 || "최대 길이는 20자 입니다.",
      (value) => {
        const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return pattern.test(value) || "유효한 이메일 형식이 아닙니다.";
      },
      {
        required: (value) => !!value || "필수 요소입니다.",
        min: (v) => v.length >= 8 || "최소 길이는 8자 입니다.",
      },
    ],
  }),
  computed: {
    ...mapState("loginDialog", ["joinDialog"]),
    passwordConfirmationRule() {
      return () =>
        this.password === this.passwordCheck || "비밀번호가 일치하지 않습니다.";
    },
  },
  methods: {
    ...mapMutations("loginDialog", ["SET_JOIN_TOGGLE"]),
    join() {
      alert(this.userId + " " + this.password + " " + this.nickname);
    },
    close() {
      this.SET_JOIN_TOGGLE();
    },
  },
};
</script>

<style scoped>
.headline {
  font-size: 40px;
  font-weight: bold;
  padding-top: 5px;
  margin: 0 auto;
}

v-text-field {
  font-size: 20px;
}

input {
  margin: 10px 50px;
  height: 20px;
  font-size: 20px;
}
</style>