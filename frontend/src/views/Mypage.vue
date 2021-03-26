<template>
  <!-- frame -> container -> wrap -->
  <div class="frame">
    <div class="item">
      <div class="left-wrap">
        <span class="nav-title">Mypage</span>
        <div class="nav-btn">
          <button
            class="nav-btn-item1"
            :class="{ 'nav-btn-active': isBtnClick(1) }"
            @click="onPreferenceSetting(1)"
          >
            Scrap
          </button>
          <button
            class="nav-btn-item2"
            :class="{ 'nav-btn-active': isBtnClick(2) }"
            @click="onPreferenceSetting(2)"
          >
            Review
          </button>
          <button
            class="nav-btn-item3"
            :class="{ 'nav-btn-active': isBtnClick(3) }"
            @click="onPreferenceSetting(3)"
          >
            Statistics
          </button>
          <button
            class="nav-btn-item4"
            :class="{ 'nav-btn-active': isBtnClick(4) }"
            @click="onPreferenceSetting(4)"
          >
            Favorite
          </button>
        </div>
      </div>
    </div>

    <div class="item">
      <div class="right-wrap">
        <div v-show="this.screenState != 4" class="content">
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
            <div class="content-item"></div>
          </div>
        </div>
        <div v-show="this.screenState == 4" class="content4">
          <PreferenceSetting :preferenceList="preferenceList" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import MyReviews from "@/components/static/mypage/MyReviews.vue";
import PreferenceSetting from "@/components/static/mypage/PreferenceSetting.vue";

const SCRAP = 1;
const REVIEW = 2;
const STATISTICS = 3;
const FRAVORITE = 4;

export default {
  name: "Mypage",
  components: {
    MyReviews,
    PreferenceSetting,
  },

  data: () => ({
    cnt: 4,
    screenState: 1,
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
    preferenceList: [
      {
        flavor_id: 1,
        name: "1111",
        img:
          "https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F9966263E5BE5397C2C",
      },
      {
        flavor_id: 2,
        name: "2222",
        img:
          "https://blog.kakaocdn.net/dn/berilh/btqGVwR4KHe/pyu7QGH81CoeCdNhFAdx01/img.jpg",
      },
      {
        flavor_id: 3,
        name: "3333",
        img:
          "https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F26286D4C590089210C",
      },
      {
        flavor_id: 4,
        name: "4444",
        img:
          "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6OE8la_DKabhGQmoJZjYtf2RqEU4p5NeNMQ&usqp=CAU",
      },
      {
        flavor_id: 5,
        name: "55555",
        img:
          "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEjEnTU125CEs7FttDpQcldEX_pJ0_Ck0GIQ&usqp=CAU",
      },
      {
        flavor_id: 6,
        name: "66666",
        img:
          "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFJEabRKkN_8GLvMHApb3-ZjA8CB_fV4t6GA&usqp=CAU",
      },
      {
        flavor_id: 7,
        name: "77777",
        img:
          "https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F9966263E5BE5397C2C",
      },
      {
        flavor_id: 8,
        name: "88888",
        img:
          "https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F9979FB445BE9264118",
      },
      {
        flavor_id: 9,
        name: "99999",
        img:
          "https://lh3.googleusercontent.com/proxy/hBdn90FtERmFWFvtQQKpQsQCAh9hBisfEdowMWShDwlByR5R-wxL3-wRF31WfScmTZnGL99NJqeivj_pHQKVfkWajj8qrhbTIr-zL2UBb5D14lbU0LvCZeAFJ3yjPTgRVZN0TGhUTMRoZJQL4a-477bM0C6pMgkcBGsWj2Y5lI-Oad7O6LVr09c",
      },
      {
        flavor_id: 10,
        name: "10",
        img:
          "https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F9966263E5BE5397C2C",
      },
      {
        flavor_id: 11,
        name: "11",
        img:
          "https://blog.kakaocdn.net/dn/berilh/btqGVwR4KHe/pyu7QGH81CoeCdNhFAdx01/img.jpg",
      },
      {
        flavor_id: 12,
        name: "12",
        img:
          "https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F26286D4C590089210C",
      },
      {
        flavor_id: 13,
        name: "13",
        img:
          "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6OE8la_DKabhGQmoJZjYtf2RqEU4p5NeNMQ&usqp=CAU",
      },
      {
        flavor_id: 14,
        name: "14",
        img:
          "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEjEnTU125CEs7FttDpQcldEX_pJ0_Ck0GIQ&usqp=CAU",
      },
      {
        flavor_id: 15,
        name: "15",
        img:
          "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFJEabRKkN_8GLvMHApb3-ZjA8CB_fV4t6GA&usqp=CAU",
      },
      {
        flavor_id: 16,
        name: "16",
        img:
          "https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F9966263E5BE5397C2C",
      },
      {
        flavor_id: 17,
        name: "17",
        img:
          "https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F9979FB445BE9264118",
      },
      {
        flavor_id: 0,
        name: "사과",
        img:
          "https://lh3.googleusercontent.com/proxy/hBdn90FtERmFWFvtQQKpQsQCAh9hBisfEdowMWShDwlByR5R-wxL3-wRF31WfScmTZnGL99NJqeivj_pHQKVfkWajj8qrhbTIr-zL2UBb5D14lbU0LvCZeAFJ3yjPTgRVZN0TGhUTMRoZJQL4a-477bM0C6pMgkcBGsWj2Y5lI-Oad7O6LVr09c",
      },
      {
        flavor_id: 0,
        name: "사과",
        img:
          "https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F9966263E5BE5397C2C",
      },
      {
        flavor_id: 0,
        name: "사과",
        img:
          "https://blog.kakaocdn.net/dn/berilh/btqGVwR4KHe/pyu7QGH81CoeCdNhFAdx01/img.jpg",
      },
      {
        flavor_id: 0,
        name: "사과",
        img:
          "https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F26286D4C590089210C",
      },
      {
        flavor_id: 0,
        name: "사과",
        img:
          "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6OE8la_DKabhGQmoJZjYtf2RqEU4p5NeNMQ&usqp=CAU",
      },
      {
        flavor_id: 0,
        name: "사과",
        img:
          "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEjEnTU125CEs7FttDpQcldEX_pJ0_Ck0GIQ&usqp=CAU",
      },
      {
        flavor_id: 0,
        name: "사과",
        img:
          "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFJEabRKkN_8GLvMHApb3-ZjA8CB_fV4t6GA&usqp=CAU",
      },
      {
        flavor_id: 0,
        name: "사과",
        img:
          "https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F9966263E5BE5397C2C",
      },
      {
        flavor_id: 0,
        name: "사과",
        img:
          "https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F9979FB445BE9264118",
      },
      {
        flavor_id: 0,
        name: "사과",
        img:
          "https://lh3.googleusercontent.com/proxy/hBdn90FtERmFWFvtQQKpQsQCAh9hBisfEdowMWShDwlByR5R-wxL3-wRF31WfScmTZnGL99NJqeivj_pHQKVfkWajj8qrhbTIr-zL2UBb5D14lbU0LvCZeAFJ3yjPTgRVZN0TGhUTMRoZJQL4a-477bM0C6pMgkcBGsWj2Y5lI-Oad7O6LVr09c",
      },
    ],
  }),
  computed: {
    ...mapState("userInfo", ["userInfo"]),
    ...mapState("nav", ["navActive"]),
  },
  methods: {
    isBtnClick(pageNum) {
      if (pageNum == this.screenState) return true;
      else return false;
    },
    onPreferenceSetting(pageNum) {
      this.screenState = pageNum;
    },
  },
};
</script>

<style scoped>
.frame {
  width: 100vw;
  background-color: var(--basic-color-bg);
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
.nav-btn > button:hover {
  transform: rotate(-90deg) scale(1.2) !important;
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

.nav-btn-item4 {
  position: absolute;
  bottom: 460px;
}
.nav-btn-active {
  border: 1px solid var(--basic-color-key) !important;
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

.content4 {
  position: relative;
  margin-top: 150px;
  width: 90%;
  height: auto;
}
</style>
