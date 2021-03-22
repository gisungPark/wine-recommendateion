<template>
  <div class="reviews-fram">
    <div class="reviews-item">
      <div class="reviews-Img">
        <img class="wineImg" src="@/assets/images/wine02.png" alt="" />
      </div>
    </div>
    <div class="reviews-item">
      <div class="wine-title">{{ review.wineTitle }}</div>
      <div class="review-title">
        <span>{{ review.reviewTitle }}</span>
        <img src="@/assets/images/star-rating.png" alt="" />
        <img src="@/assets/images/money-icons.png" alt="" />
      </div>
      <div class="review-writer" style="background-color: pink">
        <div class="writer-img">
          <img :src="userInfo.profile" alt="" />
        </div>
        <div class="addition-info">
          <span class="addition-info-usernickname">{{
            userInfo.nickname
          }}</span>
          <span class="addition-info-date">{{ review.date }}</span>
        </div>
      </div>
    </div>
    <div class="reviews-item">
      <div class="reviews-btn">
        <a href="#" class="button">와인으로 이동하기</a>
        <a href="#" class="button">리뷰로 이동하기</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["review", "userInfo"],
};
</script>

<style scoped>
.reviews-fram {
  width: 90%;
  height: 150px;
  display: flex;
  flex-direction: row;
  margin: 15px;
  border-radius: 2em;
  /* overflow: hidden; */
}
.reviews-item:nth-child(1) {
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
  position: relative;

  width: 150px;
  height: 170px;
  z-index: 5;
}

.wineImg {
  position: absolute;
  top: -15px;
  left: 10px;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

/* ###########  리뷰정보 중간지점!!  ################# */
/* ################################################# */
.wine-title {
  font-size: 32px;
  font-weight: bold;
}
.review-title {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-top: 8px;
}

.review-title > span {
  font-size: 16px;
}
.review-title > img {
  margin-left: 8px;
}

.review-writer {
  position: relative;
  top: 50px;
  display: flex;
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
.addition-info {
  margin-left: 5px;
  display: flex;
  flex-direction: column;
}
.addition-info-usernickname {
  font-size: 20px;
  font-weight: 550;
  color: var(--basic-color-bg2);
}
.addition-info-date {
  color: #808080;
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
