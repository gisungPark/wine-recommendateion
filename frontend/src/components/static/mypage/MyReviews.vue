<template>
  <div class="reviews-fram">
    <div class="reviews-item">
      <div class="reviews-Img">
        <img class="wineImg" src="@/assets/images/wine02.png" alt="" />
      </div>
    </div>
    <div class="reviews-item">
      <div class="wine-name">{{ review.wineName }}</div>
      <div class="review-content">
        <span>{{ review.contents }} </span>
      </div>
      <div style="height: 20px"></div>

      <!-- ######################################################## -->
      <div class="review-writer">
        <div class="writer-info">
          <div class="writer-img">
            <img :src="userInfo.profile" alt="" />
          </div>
          <span class="writer-nickname">
            {{ userInfo.nickname }}
          </span>
          <span class="writing-date">{{ review.date }}</span>
        </div>
        <!-- ######################################################## -->
        <!-- 가성비 아이콘, 별점 아이콘  -->
        <div style="display: flex; margin-bottom: 5px">
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
            <span style="color: #626262">{{review.point}}</span>
          </div>
          <div v-show="this.review.cost == 1" class="cost-rating">
            <div class="cost-rating-icon blue">
              <img src="@/assets/images/smiley-1.png" />
            </div>
            <span>가성비</span>
          </div>
          <div v-show="this.review.cost == 2" class="cost-rating">
            <div class="cost-rating-icon orange">
              <img src="@/assets/images/smiley-2.png" />
            </div>
            <span>가성비</span>
          </div>
          <div v-show="this.review.cost == 3" class="cost-rating">
            <div class="cost-rating-icon red">
              <img src="@/assets/images/smiley-3.png" />
            </div>
            <span>가성비</span>
          </div>
        </div>
      </div>
      <!-- ######################################################## -->
    </div>
    <div class="reviews-item">
      <div class="reviews-btn">
        <a href="#" class="button">와인으로 이동하기</a>
        <a href="#" class="button" @click="goToReview">리뷰로 이동하기</a>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";

export default {
  name: "MyReviews",
  props: ["review", "userInfo"],
  components: {},
  methods: {
    ...mapMutations("reviewDialog", ["SET_REVIEW_TOGGLE"]),
    goToReview() {
      this.SET_REVIEW_TOGGLE();
    },
  },
};
</script>

<style scoped>
.reviews-fram {
  width: 90%;
  min-height: 130px;
  display: flex;
  flex-direction: row;
  margin: 15px;
  border-radius: 2em;
  overflow: hidden;
}
.reviews-item:nth-child(1) {
  background-color: #f4f4f4;
  width: 150px;
}
.reviews-item:nth-child(2) {
  flex: 2 1 0;
  display: flex;
  flex-direction: column;
  background-color: #f4f4f4;
  padding-left: 5px;
}
.reviews-item:nth-child(3) {
  width: 250px;
  background-color: #f4f4f4;
  display: flex;
  align-items: center;
}
.reviews-Img {
  position: absolute;
  width: 180px;
  height: 180px;
}

.wineImg {
  position: absolute;
  top: -15px;
  left: 0px;
  width: 100%;
  height: 100%;
  object-fit: contain;
}

/* ###########  리뷰정보 중간지점!!  ################# */
/* ################################################# */
.wine-name {
  margin-top: 5px;
  font-size: 32px;
  font-weight: bold;
}
.review-content {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-top: 8px;
}

.review-content > span {
  font-size: 18px;
  line-height: 150%;
}
.writer-info {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-bottom: 2px;
}

.review-writer {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}

.writer-img {
  width: 40px;
  height: 40px;
  border-radius: 70%;
  overflow: hidden;
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
/* ########################## */
/* 별점 아이콘!! */
.star-rating {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
  right: 8px;
}
.star {
  fill: #f9ba01;
  width: 33px;
  height: 33px;
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
  height: 33px;
  width: 33px;
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
  width: 100%;
  height: 100%;
  margin-top: 0.3px;
  object-fit: cover;
  transform: scale(1.5) !important;
}
/* 가성비 end */
/* ########################## */

/* ###########  리뷰정보 마지막 지점!!  ################# */
/* ##################################################### */
.reviews-btn {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.reviews-btn > a {
  border: none;
  padding: 15px 0;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 15px;
  margin: 4px;
  cursor: pointer;
  flex: 1 1 0;
}
a.button {
  color: black;
  font-weight: 600;
  box-shadow: rgba(250, 250, 250, 1) 0 0px 0px 0px inset;
}

a.button:hover {
  color: rgba(238, 236, 243, 1);
  box-shadow: rgba(53, 51, 59, 0.7) 0 0px 0px 40px inset;
}
</style>
