<template>
  <!-- fram -> container -> wrap -->
  <div class="fram">
    <div class="item">
      <div class="left-wrap">
        <span class="nav-title">Mypage</span>
        <div class="nav-btn">
          <button class="nav-btn-item1">스크랩한 와인</button>
          <button class="nav-btn-item2">내가 남긴 리뷰</button>
          <button class="nav-btn-item3">내 취향 통계</button>
        </div>
      </div>
    </div>

    <div class="item" style="background-color: #0f0f0f">
      <div class="right-wrap">
        <div class="content">
          <div class="imgBox">
            <img
              class="profile"
              :src="this.userInfo.profile"
              alt="프로필 이미지"
            />
          </div>
          <span id="userId">{{ this.userInfo.nickname }}</span>
          <div class="content-item">
            <MyReviews
              v-for="(review, idx) in reviews"
              :key="idx"
              :review="review"
              :userInfo="userInfo"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import MyReviews from "@/components/static/mypage/MyReviews.vue";

// const aa = "@/assets/images/wine01.png";

export default {
  name: "Mypage",
  components: {
    MyReviews,
  },

  data: () => ({
    cnt: 4,
    info: "",
    reviews: [
      {
        img: "https://img.maisonkorea.com/2020/04/msk_5e8d712726056.jpg",
        wineTitle: "까베르네 소비뇽",
        reviewTitle: "딸기맛이 나요",
        reviewScore: 4,
        date: "2021.03.22",
      },
      {
        img: "https://img.maisonkorea.com/2020/04/msk_5e8d7129f1d53.jpg",
        wineTitle: "샤르도네",
        reviewTitle: "바나나맛이 나요",
        reviewScore: 4,
        date: "2021.02.18",
      },
      {
        img:
          "http://www.sommeliertimes.com/news/photo/201905/13382_26893_4347.jpg",
        wineTitle: "피노 누아",
        reviewTitle: "사과맛이 나요",
        reviewScore: 4,
        date: "2021.01.29",
      },
      {
        img:
          "http://www.sommeliertimes.com/news/photo/201905/13382_26893_4347.jpg",
        wineTitle: "소비뇽 블랑",
        reviewTitle: "사과맛이 나요",
        date: "2020.12.08",
      },
    ],
  }),
  computed: {
    ...mapState("userInfo", ["userInfo"]),
    ...mapState("nav", ["navActive"]),
  },
};
</script>

<style scoped>
.fram {
  height: 100vh;
  width: 100vw;
  background-color: white;
  display: flex;
  z-index: 0;
}
/* .item:nth-child(1) {
  flex: 2 1 0;
  display: flex;
  flex-direction: column;
}
.item:nth-child(2) {
  flex-grow: 1;
  flex: 14 1 0;
} */
.item:nth-child(1) {
  width: 173px;
  display: flex;
  flex-direction: column;
  background-color: #242323;
  position: fixed;
  top: 0;
  left: 0;
  height: 100vh;
}
.item:nth-child(2) {
  flex-grow: 1;
  flex: 14 1 0;
  margin-left: 172px;
  /* padding-bottom: 100px; */
}
.left-wrap {
  display: flex;
  flex-direction: column;
  flex-basis: auto;
  /* transform: rotate(-90deg); */
}

.nav-title {
  font-size: 40px;
  font-weight: bold;
  color: #9b9191;
  margin: 5px;
  position: absolute;
  bottom: 75px;
  left: 10px;
  transform: rotate(-90deg);
}
.nav-btn {
  display: flex;
  flex-flow: row nowrap;
}
.nav-btn > button {
  border: 1px solid black;
  background: black;
  border-radius: 20px;
  font-weight: bold;
  color: #a3a1a1;
  width: 120px;
  height: 25px;
  left: 80px;
  transform: rotate(-90deg);
  /* width: 150px; */
}
.nav-btn-item1 {
  position: absolute;
  bottom: 70px;
}
.nav-btn-item2 {
  position: absolute;
  bottom: 200px;
}
.nav-btn-item3 {
  position: absolute;
  bottom: 330px;
}

.right-wrap {
  display: flex;
  justify-content: center;
  padding-bottom: 100px;
}
.content {
  position: relative;
  margin-top: 150px;
  width: 90%;
  height: auto;
  background-color: #ffffff;
  border-radius: 2em;
  display: flex;
  justify-content: center;
}
.imgBox {
  position: absolute;
  top: -90px;
  left: 25px;
  width: 180px;
  height: 180px;
  border-radius: 70%;
  overflow: hidden;
}
.profile {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

#userId {
  font-size: 32px;
  font-weight: bold;
  color: var(--basic-color-bg2);
  position: absolute;
  top: -40px;
  left: 210px;
}

.content-item {
  width: 100%;
  margin-top: 100px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
