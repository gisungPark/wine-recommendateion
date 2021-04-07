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
                <span class="modal-name">Profile</span>
              </v-col>
            </v-row>
          </v-card-title>
          <v-card-text>
            <v-container>
              <div class="wrap">
                <div class="row">
                  <div class="img-item"><img src="" alt="" /></div>
                  <div class="img-item"><img src="" alt="" /></div>
                  <div class="img-item"><img src="" alt="" /></div>
                  <div class="img-item"><img src="" alt="" /></div>
                </div>
                <div class="row">
                  <div class="img-item"><img src="" alt="" /></div>
                  <div class="img-item"><img src="" alt="" /></div>
                  <div class="img-item"><img src="" alt="" /></div>
                  <div class="img-item"><img src="" alt="" /></div>
                </div>
              </div>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";

export default {
  components: {},
  data: () => ({}),
  computed: {
    ...mapState("loginDialog", ["profileDialog"]),
  },
  methods: {
    ...mapMutations("loginDialog", ["SET_LOGIN_TOGGLE", "SET_PROFILE_TOGGLE"]),

    close() {
      this.SET_LOGIN_TOGGLE();
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
.wrap {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
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
  background-image: url("https://img0.yna.co.kr/photo/cms/2019/09/19/19/PCM20190919000219505_P2.jpg");
  background-size: contain;
}
</style>