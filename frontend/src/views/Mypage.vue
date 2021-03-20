<template>
  <!-- fram -> container -> wrap -->
  <div class="fram">
    <div class="item" style="background-color: #e84545">
      <div class="left-wrap">
        <div class="nav-title">
          <span>Mypage</span>
        </div>
        <div class="nav-btn">
          <button class="nav-btn-item1">스크랩한 와인</button>
          <button class="nav-btn-item2">내가 남긴 리뷰</button>
          <button class="nav-btn-item3">내 취향 통계</button>
        </div>
      </div>
    </div>
    <div class="item" style="background-color: #2b2e4a">
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

export default {
  name: "Mypage",
  components: {
    MyReviews,
  },

  data: () => ({
    cnt: 3,
    info: "",
    reviews: [
      {
        img:
          "https://www.lacasabodega.com/wp-content/uploads/2019/02/Botella_Sembro.jpg",
        reviewTitle: "1번 딸기맛이 나요",
        reviewScore: 4,
      },
      {
        img: "https://img.maisonkorea.com/2020/04/msk_5e8d7129f1d53.jpg",
        reviewTitle: "2번 바나나맛이 나요",
        reviewScore: 4,
      },
      {
        img:
          "http://www.sommeliertimes.com/news/photo/201905/13382_26893_4347.jpg",
        reviewTitle: "3번 사과맛이 나요",
        reviewScore: 4,
      },
    ],
  }),
  computed: {
    ...mapState("userInfo", ["userInfo"]),
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
.item:nth-child(1) {
  flex: 2 1 0;
  display: flex;
  flex-direction: column;
}
.item:nth-child(2) {
  flex-grow: 1;
  flex: 14 1 0;
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
  margin: 5px;
  position: absolute;
  bottom: 90px;
  left: 30px;
  transform: rotate(-90deg);
}
.nav-btn {
  display: flex;
  flex-flow: row nowrap;
}
.nav-btn > button {
  border: 1px solid black;
  background: #903749;
  border-radius: 20px;
  font-weight: bold;
  width: 120px;
  height: 25px;
  transform: rotate(-90deg);
  /* width: 150px; */
}
.nav-btn-item1 {
  position: absolute;
  bottom: 70px;
  left: 100px;
}
.nav-btn-item2 {
  position: absolute;
  bottom: 200px;
  left: 100px;
}
.nav-btn-item3 {
  position: absolute;
  bottom: 330px;
  left: 100px;
}

.right-wrap {
  display: flex;
  justify-content: center;
}
.content {
  position: relative;
  top: 150px;
  width: 90%;
  height: auto;
  background-color: white;
  border-radius: 2em;
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
  position: absolute;
  top: -40px;
  left: 210px;
}

.content-item {
  position: relative;
  top: 100px;
  display: flex;
  flex-direction: column;
}
</style>