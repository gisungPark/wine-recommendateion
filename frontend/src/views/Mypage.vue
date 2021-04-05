<template>
  <!-- frame -> container -> wrap -->
  <div class="frame">
    <div class="item">
      <div class="left-wrap">
        <span class="nav-title">Mypage</span>
        <div class="nav-btn">
          <button
            class="nav-btn-item1"
            :class="{ 'nav-btn-active': isBtnClick(3) }"
            @click="onPreferenceSetting(3)"
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
            :class="{ 'nav-btn-active': isBtnClick(1) }"
            @click="onPreferenceSetting(1)"
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
        <div
          v-show="this.screenState == 3 || this.screenState == 2"
          class="content"
        >
          <div class="imgBox">
            <img
              class="profile"
              :src="this.userInfo.profile"
              alt="프로필 이미지"
            />
          </div>
          <span id="userId">{{ this.userInfo.nickname }}</span>
          <div class="content-item" v-show="this.screenState == 3"></div>
          <div class="content-item" v-show="this.screenState == 2">
            <MyReviews
              v-for="(review, idx) in reviews"
              :key="idx"
              :review="review"
              :userInfo="userInfo"
            />
            <Reviews />
          </div>
        </div>
        <div class="content3" v-show="this.screenState == 1">
          <ChartContent />
        </div>
        <div v-show="this.screenState == 4" class="content4">
          <PreferenceSetting :preferenceList="getDiviedFlavors" />
        </div>
      </div>
    </div>
  </div>
</template>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<script>
import { mapState, mapMutations, mapActions, mapGetters } from "vuex";
import * as mypageApi from "@/api/mypageApi";
import MyReviews from "@/components/static/mypage/MyReviews.vue";
import Reviews from "@/components/static/reviews/Reviews.vue";
import PreferenceSetting from "@/components/static/mypage/PreferenceSetting.vue";
// import LineChart from "@/components/articles/LineChart";
// import PieCharts from "@/components/static/mypage/chart/PieCharts.vue";
// import PieCharts2 from "@/components/static/mypage/chart/PieCharts2.vue";
// import Doughnut from "@/components/static/mypage/chart/Doughnut.vue";
// import Radar from "@/components/static/mypage/chart/Radar.vue";
import ChartContent from "@/components/static/mypage/chart/ChartContent.vue";

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
    ChartContent,
    // LineChart,
    // PieCharts,
    // PieCharts2,
    // Doughnut,
    // Radar,
  },
  data: () => ({
    cnt: 4,
    screenState: 1,
    reviews: [],
    preferenceList: [],
  }),
  created() {
    // 1. 향 정보 읽어오기!!
    // this.actGetFlavor();
    // this.getMyPreference();

    // 2. 리뷰 정보
    this.mypageReview(1);
    // 3. 스크랩 정보!!
    // this.actGetScrap();
  },

  computed: {
    ...mapState("nav", ["navActive"]),
    ...mapState("userInfo", ["userInfo"]),
    ...mapState("mypage", ["flavors", "scraps"]),
    ...mapGetters("mypage", ["initFlavors", "getDiviedFlavors"]),
  },
  methods: {
    ...mapActions("mypage", ["actGetFlavor", "getMyPreference", "actGetScrap"]),

    isBtnClick(index) {
      if (index == this.screenState) return true;
      else return false;
    },
    onPreferenceSetting(index) {
      this.screenState = index;
    },
    async mypageReview(num) {
      const response = await mypageApi.mypageReview(num);
      console.log(response.data);
      this.reviews = response.data;
    },
  },
};
</script>

<style scoped>
.frame {
  width: 100%;
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
  height: 100%;
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
  left: 80px;
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
.content3 {
  position: relative;
  width: 100%;
  height: 100%;
  margin-top: 150px;
  border-radius: 2em;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.content4 {
  position: relative;
  margin-top: 150px;
  width: 90%;
  height: auto;
}
/* ########################### */
.tabs {
  position: relative;
  margin: 35px auto;
  height: 1200px;
  width: 90%;
}

.tabs input {
  position: absolute;
  z-index: 1000;
  width: 120px;
  height: 35px;
  left: 0px;
  top: 0px;
  opacity: 0;
  -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
  filter: alpha(opacity=0);
}
.tabs input:not(:checked) {
  cursor: pointer;
}
.tabs input:not(:checked) + label {
  color: #fff;
}
.tabs input:checked + label {
  background: #fff;
  color: #a22;
  z-index: 6;
}
.tabs input:hover + label {
  background: #c44;
  color: #fff;
}
.tabs input#tab-2 {
  left: 600px;
}
.tabs input#tab-3 {
  left: 1200px;
}
.tabs input.tab-selector-1:checked ~ .tab-content .tab-content-1,
.tabs input.tab-selector-2:checked ~ .tab-content .tab-content-2,
.tabs input.tab-selector-3:checked ~ .tab-content .tab-content-3 {
  z-index: 100;
  filter: alpha(opacity=100);
  opacity: 1;
}

.tabs label {
  height: 55px;
  width: 500px;
  background: #a22;
  font-size: 30px;
  font-weight: 600;
  line-height: 35px;
  position: relative;
  padding: 4px 20px 0px 20px;
  float: left;
  display: block;
  letter-spacing: 0px;
  text-align: center;
  margin-left: 3px;
  margin-right: 3px;

  border-radius: 12px 12px 0 0;
  box-shadow: 2px 0 2px rgba(0, 0, 0, 0.1), -2px 0 2px rgba(0, 0, 0, 0.1);
}
.tabs label:after {
  content: "";
  background: #fff;
  position: absolute;
  bottom: -2px;
  left: 0;
  width: 100%;
  height: 2px;
  display: block;
}
.tabs label:first-of-type {
  z-index: 4;
  box-shadow: 1px 0 3px rgba(0, 0, 0, 0.1);
}

.tab-label-2 {
  z-index: 2;
}
.tab-label-3 {
  z-index: 3;
}
.tab-label-4 {
  z-index: 4;
}

.clear-shadow {
  clear: both;
}

.tab-content {
  background: #fff;
  position: relative;
  width: 100%;
  height: 900px;
  box-shadow: 0 -2px 3px -2px rgba(0, 0, 0, 0.2), 0 2px 2px rgba(0, 0, 0, 0.1);
  border-radius: 0 12px 3px 3px;
}
.tab-content div {
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
  padding: 10px 40px;
  z-index: 1;
  opacity: 0;
  box-sizing: border-box;
}
.tab-content div h3 {
  color: #398080;
  border-bottom: 1px solid rgba(63, 148, 148, 0.1);
}
.tab-content div h3:before {
  content: " - ";
}
.tab-content div p {
  font-size: 14px;
  line-height: 22px;
  text-align: left;
  margin: 0;
  color: #777;
  padding-left: 15px;
}
</style>
