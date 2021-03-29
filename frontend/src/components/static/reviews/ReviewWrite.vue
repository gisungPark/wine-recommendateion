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
          <div style="height: 15px"></div>
          <v-card-text>
            <span class="description">와인은 만족스러웠나요?</span>
            <v-row>
              <v-col>
                <div class="rating-wrap">
                  <v-rating
                    v-model="rating"
                    color="#F9DC14"
                    background-color="grey darken-1"
                    size="50"
                    half-increments
                    hover
                  ></v-rating>
                </div>
              </v-col>
            </v-row>
            <span class="description">가성비는 만족스러웠나요?</span>
            <v-row>
              <v-col>
                <div class="rating-wrap price-wrap">
                  <div
                    id="test1"
                    :class="{
                      'cost-item-inactive': !getCostRating(1),
                      'cost-item-active': getCostRating(1),
                    }"
                    @click="setCostRating(1)"
                  ></div>
                  <div
                    id="test2"
                    :class="{
                      'cost-item-inactive': !getCostRating(2),
                      'cost-item-active': getCostRating(2),
                    }"
                    @click="setCostRating(2)"
                  ></div>
                  <div
                    id="test3"
                    :class="{
                      'cost-item-inactive': !getCostRating(3),
                      'cost-item-active': getCostRating(3),
                    }"
                    @click="setCostRating(3)"
                  ></div>
                </div>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-textarea
                  v-model="reviewContent"
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
            <v-btn text id="writeBtn" @click="writeReivew">리뷰작성</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";

export default {
  name: "ReviewWrite",
  props: [],
  components: {},
  computed: {
    ...mapState("reviewDialog", ["reviewWriteDialog"]),
  },
  data: () => ({
    rating: 5,
    costRating: -1,
    reviewContent: "",
  }),
  methods: {
    ...mapMutations("reviewDialog", ["SET_REVIEW_WRITE_TOGGLE"]),
    close() {
      this.SET_REVIEW_WRITE_TOGGLE();
    },
    getCostRating(n) {
      if (n == this.costRating) return true;
      else return false;
    },
    setCostRating(n) {
      this.costRating = n;
    },
    writeReivew() {
      if (this.costRating < 0) alert("가성비 점수를 선택해 주세요!!");
      console.log("별점: " + this.rating);
      console.log("가성비: " + this.costRating);
      console.log("리뷰 내용: " + this.reviewContent);
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

.description {
  color: black;
  font-size: 18px;
  font-weight: 600;
}

.rating-wrap {
  height: 65px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  border: 1px solid darkgrey;
  margin-top: 8px;
  margin-bottom: 10px;
}
.price-wrap > div {
  width: 120px;
  height: 55px;
  margin: auto 20px;
  background-position: center;
  background-size: contain;
}

#test1 {
  background-image: url(../../../assets/images/price-rating-3.png);
}
#test2 {
  background-image: url(../../../assets/images/price-rating-2.png);
}
#test3 {
  background-image: url(../../../assets/images/price-rating-1.png);
}

/* .price-wrap:nth-child(1) {
  background-image: url(../../../assets/images/price-rating-1.png);
}
.price-wrap:nth-child(2) {
  background-image: url(../../../assets/images/price-rating-2.png);
}
.price-wrap:nth-child(3) {
  background-image: url(../../../assets/images/price-rating-3.png);
} */
.cost-item-inactive {
  opacity: 0.2;
}
.cost-item-active {
  opacity: 1;
  transform: scale(1.2) !important;
}

#writeBtn {
  height: 38px;
  width: 85px;
  color: white;
  font-size: 15px;
  background-color: var(--basic-color-btn);
  position: relative;
  bottom: 15px;
  right: 10px;
}
</style>