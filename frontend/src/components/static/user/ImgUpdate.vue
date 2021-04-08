<template>
  <div data-app>
    <v-row justify="center">
      <v-dialog v-model="profileDialog" persistent max-width="1000px">
        <v-card height="600">
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
                <span class="modal-name"> Profile image</span>
              </v-col>
            </v-row>
          </v-card-title>
          <v-card-text>
            <v-container>
              <div class="wrap">
                <div class="row">
                  <div
                    class="img-item"
                    :class="{ selected: isSelected(0) }"
                    @click="select(0)"
                  >
                    <img class="profile" :src="`${s3url_profile}0.svg`" />
                  </div>
                  <div
                    class="img-item"
                    :class="{ selected: isSelected(1) }"
                    @click="select(1)"
                  >
                    <img class="profile" :src="`${s3url_profile}1.svg`" />
                  </div>
                  <div
                    class="img-item"
                    :class="{ selected: isSelected(2) }"
                    @click="select(2)"
                  >
                    <img class="profile" :src="`${s3url_profile}2.svg`" />
                  </div>
                  <div
                    class="img-item"
                    :class="{ selected: isSelected(3) }"
                    @click="select(3)"
                  >
                    <img class="profile" v :src="`${s3url_profile}3.svg`" />
                  </div>
                </div>
                <div class="row">
                  <div
                    class="img-item"
                    :class="{ selected: isSelected(4) }"
                    @click="select(4)"
                  >
                    <img class="profile" :src="`${s3url_profile}4.svg`" />
                  </div>
                  <div
                    class="img-item"
                    :class="{ selected: isSelected(5) }"
                    @click="select(5)"
                  >
                    <img class="profile" :src="`${s3url_profile}5.svg`" />
                  </div>
                  <div
                    class="img-item"
                    :class="{ selected: isSelected(6) }"
                    @click="select(6)"
                  >
                    <img class="profile" :src="`${s3url_profile}6.svg`" />
                  </div>
                  <div
                    class="img-item"
                    :class="{ selected: isSelected(7) }"
                    @click="select(7)"
                  >
                    <img class="profile" :src="`${s3url_profile}7.svg`" />
                  </div>
                </div>
              </div>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn id="changeBtn" @click="onChange">Change</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
import * as mypageApi from "@/api/mypageApi";

export default {
  components: {},
  data: () => ({
    selectItem: -1,
  }),
  computed: {
    ...mapState(["s3url_profile"]),
    ...mapState("loginDialog", ["profileDialog"]),
  },
  methods: {
    ...mapMutations("loginDialog", ["SET_LOGIN_TOGGLE", "SET_PROFILE_TOGGLE"]),
    init() {
      this.selectItem = -1;
    },
    select(index) {
      this.selectItem = index;
    },
    isSelected(index) {
      if (index === this.selectItem) return true;
      else return false;
    },
    async onChange() {
      if (this.selectItem == -1) {
        alert("프로필 이미지를 선택해주세요!");
      } else {
        const response = await mypageApi.updateProfile(this.selectItem);
        if (response.status == 200) {
          this.close();
        }
      }
    },
    close() {
      this.selectItem = -1;
      this.SET_PROFILE_TOGGLE();
    },
  },
};
</script>

<style scoped>
#closeBtn {
  position: absolute;
  right: 15px;
  margin-top: 5px;
  cursor: pointer;
}

.modal-name {
  font-size: 42px;
  font-weight: bold;
  margin: 0 auto;
}
.wrap {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
}
.row {
  width: 100%;
  min-height: 50px;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  margin-top: 20px;
}
.img-item {
  width: 150px;
  height: 150px;
  border-radius: 70%;
  overflow: hidden;
  border: 1px solid black;
  /* background-image: url("https://img0.yna.co.kr/photo/cms/2019/09/19/19/PCM20190919000219505_P2.jpg");
  background-size: contain; */
}
.profile {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.selected {
  transform: scale(1.2) !important;
}

#changeBtn {
  top: 25px;
  margin-top: 30px;
  width: 150px;
  height: 45px;
  font-size: 15px;
  color: white;
  background-color: var(--basic-color-bg2);
}
</style>