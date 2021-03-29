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
        <span>{{ review.reviewTitle }} </span>
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
        <div>
          <!-- <img class="review-rating" src="@/assets/images/soso.png" /> -->
          <!-- <img class="review-rating" src="@/assets/images/good.png" /> -->
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
  width: 35px;
  height: 35px;
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
.review-rating {
  height: 80px;
  widows: 80px;
}
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
