<template>
  <div data-app>
    <v-row justify="center">
      <v-dialog v-model="reviewWriteDialog" persistent max-width="700px">
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
          <div style="height: 20px"></div>
          <v-card-title>
            <v-row>
              <v-col>
                <span class="modal-name">Rate This Wine</span>
              </v-col>
            </v-row>
          </v-card-title>
          <div style="height: 10px"></div>
          <v-card-text>
            <v-row>
              <v-col>
                <div class="rating-wrap">
                  <v-rating
                    v-model="rating"
                    color="#F9DC14"
                    background-color="grey darken-1"
                    size="64"
                    half-increments
                    hover
                  ></v-rating>
                </div>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <div class="rating-wrap price-wrap">
                  <div>별로에요</div>
                  <div>그저그래요</div>
                  <div>최고에요</div>
                </div>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-textarea
                  solo
                  name="input-7-4"
                  height="200px"
                  label="와인에 대한 평을 자유롭게 남겨주세요"
                ></v-textarea>
              </v-col>
            </v-row>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn text id="writeBtn">리뷰작성</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import ReviewItem from "@/components/static/reviews/ReviewItem.vue";

export default {
  name: "ReviewWrite",
  props: [],
  components: {
    ReviewItem,
  },
  computed: {
    ...mapState("reviewDialog", ["reviewWriteDialog"]),
  },
  data: () => ({
    rating: 3.5,
  }),
  methods: {
    ...mapMutations("reviewDialog", ["SET_REVIEW_WRITE_TOGGLE"]),
    close() {
      this.SET_REVIEW_WRITE_TOGGLE();
    },
  },
};
</script>

<style scoped>
#closeBtn {
  position: absolute;
  right: 15px;
  margin-top: 15px;
  cursor: pointer;
}

.modal-name {
  font-size: 32px;
  font-weight: bold;
  margin: 0 auto;
}

.rating-wrap {
  height: 80px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  border: 1px solid darkgrey;
}
.price-wrap {
}
.price-wrap > div {
  background-color: darkgrey;
  width: 85px;
  height: 45px;
  margin: auto 20px;
}
#writeBtn {
  height: 38px;
  width: 85px;
  color: white;
  font-size: 15px;
  background-color: #bb3254;
  position: relative;
  bottom: 15px;
  right: 10px;
}
</style>