<template>
  <div data-app>
    <v-row justify="center">
      <v-dialog v-model="nicknameDialog" persistent max-width="600px">
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
    nickname: "",
    msg: {
      nicknameMsg: "",
    },
  }),
  computed: {
    ...mapState("loginDialog", ["nicknameDialog"]),
  },
  methods: {
    ...mapMutations("loginDialog", ["SET_NICKNAME_TOGGLE"]),
    ...mapMutations("userinfo", ["SET_TEMP_TOKEN"]),
    close() {
      this.nickname = "";
      this.msg.nicknameMsg = "";
      this.SET_TEMP_TOKEN("");
      this.SET_NICKNAME_TOGGLE();
    },
    // ##################### nickname 중복체크 #####################
    async nicknameCheck() {
      if (this.nickname.length < 3) {
        this.msg.nicknameMsg = "닉네임 최소 길이는 3글자 입니다.";
        return false;
      } else {
        this.msg.nicknameMsg = "";
        try {
          const response = await authApi.nicknameDuplicate(this.nickname);
          if (response.data) {
            this.msg.nicknameMsg = "이미 존재하는 닉네임입니다. ";
            return false;
          } else {
            this.msg.nicknameMsg =
              this.nickname + " 은 사용가능한 닉네임 입니다.!";
            return true;
          }
        } catch (error) {
          console.log(error);
          return false;
        }
      }
    },

    join() {
      if (this.nicknameCheck()) {
        const response = this.$store
          .dispatch("userInfo/kakaoJoin", {
            nickname: this.nickname,
          })
          .then((result) => {
            console.log("555555555555555555555555555555");
            if (result.data.code === 5) {
              console.log("닉네임 등록을 성공적으로 마쳤습니다.!");
              this.close();
            } else {
              alert("닉네임을 확인하세요!");
            }
          });
      } else {
        alert("닉네임을 확인하세요!");
      }
    },
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