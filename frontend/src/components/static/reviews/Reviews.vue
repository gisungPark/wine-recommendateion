<template>
  <div data-app>
    <v-row justify="center">
      <v-dialog v-model="reviewDialog" persistent max-width="1000px">
        <v-card>
          <div id="closeBtn" @click="close">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="24"
              height="24"
              viewBox="0 0 24 24"
            >
              <path
                class="blue"
                d="M24 20.188l-8.315-8.209 8.2-8.282-3.697-3.697-8.212
                  8.318-8.31-8.203-3.666 3.666 8.321 8.24-8.206 8.313 3.666 3.666 8.237-8.318 8.285 8.203z"
              />
            </svg>
          </div>
          <div style="height: 70px"></div>
          <v-card-text>
            <!-- #########################################################  -->
            <!-- 리뷰 item #################################################  -->
            <v-row v-for="(review, idx) in review" :key="review + idx">
              <v-col>
                <div
                  class="review-item-frame"
                  :class="{
                    'myreview-border': userInfo.nickname == review.nickname,
                    'myreview-border2': userInfo.nickname != review.nickname,
                  }"
                >
                  <div class="review-top">
                    <div class="review-content">
                      <span>
                        {{ review.contents }}
                      </span>
                    </div>
                    <div class="review-icon">
                      <div
                        v-show="userInfo.nickname == review.nickname"
                        class="review-icon-1"
                        @click="onModify(review)"
                      ></div>
                      <div
                        v-show="userInfo.nickname == review.nickname"
                        class="review-icon-2"
                        @click="onDelete(review)"
                      ></div>
                    </div>
                  </div>
                  <div style="height: 30px"></div>
                  <div class="review-writer">
                    <div class="writer-info">
                      <div class="writer-img">
                        <img
                          class="profile"
                          :src="`${s3url_profile}${review.userImg}.svg`"
                        />
                      </div>
                      <span class="writer-nickname">
                        {{ review.nickname }}
                      </span>
                      <span class="writing-date">{{ review.time }}</span>
                    </div>
                    <!-- ######################################################## -->
                    <!-- 가성비 아이콘, 별점 아이콘  -->
                    <div class="review-rating-wrap">
                      <div class="star-rating">
                        <svg
                          class="star"
                          xmlns="http://www.w3.org/2000/svg"
                          width="24"
                          height="24"
                          viewBox="0 0 24 24"
                        >
                          <path
                            d="M12 .587l3.668 7.568 8.332 1.151-6.064 5.828 1.48 8.279-7.416-3.967-7.417 3.967 1.481-8.279-6.064-5.828 8.332-1.151z"
                          />
                        </svg>
                        <span style="font-weight: 600">{{ review.point }}</span>
                      </div>
                      <div v-show="review.cost == 1" class="cost-rating">
                        <div class="cost-rating-icon blue">
                          <img src="@/assets/images/smiley-1.png" />
                        </div>
                        <span>가성비</span>
                      </div>
                      <div v-show="review.cost == 2" class="cost-rating">
                        <div class="cost-rating-icon orange">
                          <img src="@/assets/images/smiley-2.png" />
                        </div>
                        <span>가성비</span>
                      </div>
                      <div v-show="review.cost == 3" class="cost-rating">
                        <div class="cost-rating-icon red">
                          <img src="@/assets/images/smiley-3.png" />
                        </div>
                        <span>가성비</span>
                      </div>
                    </div>
                  </div>
                </div>
              </v-col>
            </v-row>
            <!-- 무한스크롤 ##################################################### -->
            <infinite-loading
              @infinite="infiniteHandler"
              force-use-infinite-wrapper="true"
              spinner="waveDots"
              ref="infiniteLoading"
              class="infinite"
            >
              <div slot="no-more">목록의 끝입니다.</div>
              <div slot="no-results">요청 결과가 없습니다.</div>
              <div slot="error" slot-scope="{ trigger }">
                문제가 발생했습니다. 재시도 하려면
                <a href="javascript:;" @click="trigger">여기</a>를 누르십시오.
              </div>
            </infinite-loading>
            <!-- 리뷰 item end #############################################  -->
            <!-- #########################################################  -->
          </v-card-text>

          <div class="writeBtn-wrap"></div>
          <v-card-actions>
            <v-btn
              text
              id="writeBtn"
              class="writePop"
              @click="clickWriteBtn"
            ></v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <ReviewWrite :propsReview="propsReview" />
    </v-row>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import * as reviewApi from "@/api/review";
import InfiniteLoading from "vue-infinite-loading";
import ReviewWrite from "@/components/static/reviews/ReviewWrite.vue";

export default {
  name: "Reviews",
  components: {
    ReviewWrite,
    InfiniteLoading,
  },
  created() {},
  mounted() {
    this.readWineReviews(this.$refs.infiniteLoading.stateChanger);
  },
  watch: {
    reviewDialog: function () {
      this.page = 1;
      this.review = [];
      this.readWineReviews(this.$refs.infiniteLoading.stateChanger);
    },
    reviewWriteDialog: function () {
      this.page = 1;
      this.review = [];
      this.readWineReviews(this.$refs.infiniteLoading.stateChanger);
      if (this.reviewWriteDialog == false) {
        this.propsReview = {
          rating: 5,
          costRating: -1,
          reviewContent: "",
        };
      }
    },
  },
  computed: {
    ...mapState(["s3url_profile"]),
    ...mapState("userInfo", ["userInfo"]),
    ...mapState("reviewDialog", [
      "reviewDialog",
      "reviewWriteDialog",
      "reviewByWineId",
    ]),
  },
  data: () => ({
    page: 1,
    review: [],
    propsReview: {},
  }),
  methods: {
    ...mapMutations("reviewDialog", [
      "SET_REVIEW_TOGGLE",
      "SET_REVIEW_WRITE_TOGGLE",
    ]),
    // infiniteHandler, props 전달
    infiniteHandler($state) {
      this.readWineReviews($state);
    },

    async readWineReviews($state) {
      try {
        const response = await reviewApi.getWineReviewById(
          this.page,
          this.reviewByWineId
        );
        if (response) {
          console.log(response);
          if (response.data.length === 0) {
            // 더 이상 불러올 목록이 없는 경우
            console.log("리뷰 끝났다.");
            $state.complete();
          } else {
            console.log("리뷰 읽어왔다. ");
            this.review.push(...response.data);
            this.page += 1;
            setTimeout(() => {
              $state.loaded();
            }, 1000);
          }
        } else {
          $state.error(); //통신에러
          return;
        }
      } catch (error) {
        console.log(error);
      }
    },

    onModify(item) {
      if (window.confirm("리뷰룰 수정 하시겠습니까?")) {
        this.propsReview = {
          rating: item.point,
          costRating: item.cost,
          reviewContent: item.contents,
        };
        this.SET_REVIEW_WRITE_TOGGLE();
      }
    },
    async onDelete() {
      if (window.confirm("리뷰를 삭제 하시겠습니까?")) {
        const response = await reviewApi.deleteReview(this.reviewByWineId);
        this.readWineReviews();
      }
    },

    clickWriteBtn() {
      this.SET_REVIEW_WRITE_TOGGLE();
    },
    close() {
      this.page = 1;
      this.review = [];
      this.SET_REVIEW_TOGGLE();
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

.review-item-frame {
  width: 100%;
  min-height: 100px;
  display: flex;
  flex-direction: column;
  background-color: #f4f4f4;
  border-radius: 2em;
  overflow: hidden;
  position: relative;
}

.myreview-border {
  border: 1px solid var(--basic-color-key);
}
.myreview-border2 {
  border: 1px solid black;
}

.review-top {
  display: flex;
  flex-direction: row;
}
.review-icon {
  width: 100px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  position: absolute;
  right: 10px;
}
.review-icon-1 {
  width: 40px;
  height: 40px;
  background-image: url("../../../assets/images/pencil.png");
  background-size: contain;
}

.review-icon-2 {
  width: 40px;
  height: 40px;
  background-image: url("../../../assets/images/close.png");
  background-size: contain;
}
.review-content {
  width: 88%;
  margin-top: 10px;
  margin-left: 15px;
  margin-right: 15px;
  display: flex;
  flex-direction: row;
}
.review-content > span {
  color: black;
  font-size: 18px;
  line-height: 150%;
}
.review-content > img {
  width: 30px;
  height: 30px;
}
.review-writer {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}
.writer-info {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-left: 12px;
  margin-bottom: 5px;
}

.writer-img {
  width: 40px;
  height: 40px;
  border-radius: 70%;
  overflow: hidden;
  background-image: url("https://blog.kakaocdn.net/dn/c3vWTf/btqUuNfnDsf/VQMbJlQW4ywjeI8cUE91OK/img.jpg");
  background-size: contain;
}

.writer-img > img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.writer-nickname {
  font-size: 20px;
  font-weight: 550;
  margin-left: 3px;
  color: var(--basic-color-bg2);
}
.writing-date {
  margin-left: 3px;
  margin-top: 6px;
  font-size: 16px;
  color: #808080;
}

.review-rating-wrap {
  display: flex;
  position: relative;
  right: 30px;
  bottom: 3px;
}

/* ########################## */
/* 별점 아이콘!! */
.star-rating {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
  right: 5px;
}
.star {
  fill: #f9ba01;
  width: 38px;
  height: 38px;
}
/* ########################## */
/* 가성비!! */
.cost-rating {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.cost-rating > span {
  font-size: 13px;
  color: #626262;
  font-weight: 900;
  margin-top: 2px;
}

.cost-rating-icon {
  height: 38px;
  width: 38px;
  border-radius: 70%;
  overflow: hidden;
  /* background-color: #faad50; */
}

.blue {
  background-color: #4169e1;
}
.orange {
  background-color: #fda613;
}
.red {
  background-color: #eb3763;
}
.cost-rating-icon > img {
  height: 100%;
  width: 100%;
  object-fit: cover;
}

.writeBtn-wrap {
  height: 35px;
  position: relative;
}
#writeBtn {
  position: fixed;
  right: 25%;
  bottom: 8%;
  width: 64px;
  height: 64px;
  background-image: url(../../../assets/images/plus-icon.png);
  background-position: center;
  background-size: contain;
  opacity: 0;
}
#writeBtn:hover {
  transform: scale(1.11);
}
</style>