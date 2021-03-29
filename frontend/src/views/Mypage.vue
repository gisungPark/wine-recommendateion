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
          <div class="content-item" v-show="this.screenState == 1"></div>
          <div class="content-item" v-show="this.screenState == 2">
            <MyReviews
              v-for="(review, idx) in reviews"
              :key="idx"
              :review="review"
              :userInfo="userInfo"
            />
            <Reviews />
          </div>
          <div class="content-item" v-show="this.screenState == 3"></div>
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
import Reviews from "@/components/static/reviews/Reviews.vue";
import PreferenceSetting from "@/components/static/mypage/PreferenceSetting.vue";

const SCRAP = 1;
const REVIEW = 2;
const STATISTICS = 3;
const FRAVORITE = 4;

export default {
  name: "Mypage",
  components: {
    MyReviews,
    Reviews,
    PreferenceSetting,
  },

  data: () => ({
    cnt: 4,
    screenState: 1,
    reviews: [
      {
        img: "https://img.maisonkorea.com/2020/04/msk_5e8d712726056.jpg",
        wineName: "까베르네 소비뇽",
        reviewTitle:
          "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
        reviewScore: 4,
        date: "2021.03.22",
        priceScore: 1,
      },
      {
        img: "https://img.maisonkorea.com/2020/04/msk_5e8d7129f1d53.jpg",
        wineName: "샤르도네",
        reviewTitle: `Nisl tincidunt eget nullam non. 
        Quis hendrerit dolor magna eget est lorem ipsum 
        dolor sit. Volutpat odio facilisis mauris sit amet massa. 
        Commodo odio aenean sed adipiscing diam donec adipiscing tristique. 
        Mi eget mauris pharetra et. Non tellus orci ac auctor augue. 
        Elit at imperdiet dui accumsan sit. Ornare arcu dui 
        vivamus arcu felis. Egestas integer eget aliquet nibh praesent. 
        In hac habitasse platea dictumst quisque sagittis purus. Pulvinar 
        elementum integer enim neque volutpat ac.`,
        reviewScore: 4,
        date: "2021.02.18",
        priceScore: 3,
      },
      {
        img:
          "http://www.sommeliertimes.com/news/photo/201905/13382_26893_4347.jpg",
        wineName: "피노 누아",
        reviewTitle: `Nisl tincidunt eget nullam non. 
        Quis hendrerit dolor magna eget est lorem ipsum 
        dolor sit. Volutpat odio facilisis mauris sit amet massa. 
        Commodo odio aenean sed adipiscing diam donec adipiscing tristique. 
        Mi eget mauris pharetra et. Non tellus orci ac auctor augue. 
        Elit at imperdiet dui accumsan sit. Ornare arcu dui 
        vivamus arcu felis. Egestas integer eget aliquet nibh praesent. 
        In hac habitasse platea dictumst quisque sagittis purus. Pulvinar 
        elementum integer enim neque volutpat ac.`,
        reviewScore: 4,
        date: "2021.01.29",
        priceScore: 3,
      },
      {
        img:
          "http://www.sommeliertimes.com/news/photo/201905/13382_26893_4347.jpg",
        wineName: "소비뇽 블랑",
        reviewTitle: `Nisl tincidunt eget nullam non. 
        Quis hendrerit dolor magna eget est lorem ipsum 
        dolor sit. Volutpat odio facilisis mauris sit amet massa. 
        Commodo odio aenean sed adipiscing diam donec adipiscing tristique. 
        Mi eget mauris pharetra et. Non tellus orci ac auctor augue. 
        Elit at imperdiet dui accumsan sit. Ornare arcu dui 
        vivamus arcu felis. Egestas integer eget aliquet nibh praesent. 
        In hac habitasse platea dictumst quisque sagittis purus. Pulvinar 
        elementum integer enim neque volutpat ac.`,
        date: "2020.12.08",
        priceScore: 2,
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
    isBtnClick(index) {
      if (index == this.screenState) return true;
      else return false;
    },
    onPreferenceSetting(index) {
      this.screenState = index;
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
  width: 172px;
  height: 100vh;
  border-right: 1px solid var(--basic-color-key);
  background-color: var(--basic-color-fill2);
  display: flex;
  flex-direction: column;
  position: fixed;
  top: 0;
  left: 0;
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
  font-size: 3rem;
  font-weight: bold;
  color: var(--basic-color-fill);
  position: absolute;
  bottom: 98px;
  right: 2px;
  transform: rotate(-90deg);
}
.nav-btn {
  display: flex;
  flex-flow: row nowrap;
}
.nav-btn > button {
  width: 120px;
  height: 2rem;
  line-height: 2rem;
  margin-right: 1rem;

  border-radius: 1rem;
  font-weight: bold;
  border: 1px solid var(--basic-color-bg);
  background: var(--basic-color-bg);
  color: #a3a1a1;
  left: 85px;
  transform: rotate(-90deg);
  /* width: 150px; */
}

.nav-btn > button:hover,
.nav-btn > button:active {
  background-color: #e1a95744;
  border: 1px solid var(--basic-color-key);
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
  min-height: 400px;
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
