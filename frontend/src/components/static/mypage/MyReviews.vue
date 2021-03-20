<template>
  <div class="reviews-fram">
    <div class="reviews-item">
      <div class="reviews-Img">
        <img class="wineImg" :src="review.img" alt="" />
      </div>
    </div>
    <div class="reviews-item">
      <div class="wine-title">{{ review.reviewTitle }}</div>
    </div>
    <div class="reviews-item">
      <div class="reviews-btn">
        <button>와인으로 이동하기</button>
        <button>리뷰로 이동하기</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["review"],
};
</script>

<style scoped>
.reviews-fram {
  width: 98%;
  height: 150px;
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  margin: 15px;
}
.reviews-item:nth-child(1) {
  flex: 1 1 0;
  background-color: #91091e;
}
.reviews-item:nth-child(2) {
  flex: 2 1 0;
  background-color: #da723c;
}
.reviews-item:nth-child(3) {
  flex: 1 1 0;
  background-color: #c39e5c;
  display: flex;
  align-items: center;
}
.reviews-Img {
  width: 150px;
  height: 150px;
  border-radius: 70%;
  overflow: hidden;
}

.wineImg {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.wine-title {
  font-size: 32px;
  font-weight: bold;
}
.reviews-btn {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.reviews-btn > button {
  background-color: #7c7878;
  border: none;
  padding: 15px 0;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 15px;
  margin: 4px;
  cursor: pointer;
}
</style>