<template>
  <!-- frame -> container -> wrap -->
  <div class="frame">
    <div class="item">
      <div class="left-wrap">
        <span class="nav-title">Mypage</span>
        <div class="nav-btn">
          <button class="nav-btn-item1" :class="{ 'nav-btn-active': isBtnClick(3) }" @click="setStage(3)">
            Scrap
          </button>
          <button class="nav-btn-item2" :class="{ 'nav-btn-active': isBtnClick(2) }" @click="setStage(2)">
            Review
          </button>
          <button class="nav-btn-item3" :class="{ 'nav-btn-active': isBtnClick(1) }" @click="setStage(1)">
            Statistics
          </button>
          <button class="nav-btn-item4" :class="{ 'nav-btn-active': isBtnClick(4) }" @click="setStage(4)">
            Favorite
          </button>
        </div>
      </div>
    </div>

    <div class="item">
      <div class="right-wrap">
        <div v-show="this.screenState == 3 || this.screenState == 2" class="content">
          <div class="imgBox">
            <img class="profile" @click="changeProfile" :src="`${s3url_profile}${userInfo.profile}.svg`" />
          </div>
          <span id="userId">{{ this.userInfo.nickname }}</span>
          <!-- 스크랩 페이지 ################################################ -->
          <div class="content-item" v-show="this.screenState == 3">
            <div class="item-title gray">스크랩한 와인</div>
            <div style="height: 50px"></div>
            <div class="item-list">
              <div class="wine-item" v-for="(item, index) in scarpList" :key="item + index">
                <WineItem v-on:deleteScrap="updateScrap" :wine="item" />
              </div>
            </div>
          </div>
          <!-- 리뷰 페이지 ################################################ -->
          <div class="content-item" v-show="this.screenState == 2">
            <div id="review-title" class="item-title gray">내가 쓴 리뷰</div>
            <div style="height: 50px"></div>
            <div id="review-wraps">
              <MyReviews
                v-for="(review, idx) in reviews"
                :key="idx"
                :review="review"
                :userInfo="userInfo"
              />
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
            </div>
            <Reviews />
            <div style="height: 10px"></div>
          </div>
        </div>
        <!-- 차트 페이지 ################################################ -->
        <div class="content3" v-show="this.screenState == 1">
          <ChartContent />
        </div>
        <!-- 선호도 페이지 ################################################ -->
        <div v-show="this.screenState == 4" class="content4">
          <PreferenceSetting />
        </div>
      </div>
    </div>
    <ImgUpdate />
  </div>
</template>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<script>
import { mapState, mapMutations, mapActions, mapGetters } from "vuex";
import * as mypageApi from "@/api/mypageApi";
import MyReviews from "@/components/static/mypage/MyReviews.vue";
import Reviews from "@/components/static/reviews/Reviews.vue";
import ImgUpdate from "@/components/static/user/ImgUpdate.vue";
import PreferenceSetting from "@/components/static/mypage/PreferenceSetting.vue";
import ChartContent from "@/components/static/mypage/chart/ChartContent.vue";
import Winelist from "@/components/articles/Winelist.vue";
import WineItem from "@/components/articles/ScrapWineItem.vue";
import InfiniteLoading from "vue-infinite-loading";

const SCRAP = 1;
const REVIEW = 2;
const STATISTICS = 3;
const FRAVORITE = 4;

export default {
  name: 'Mypage',
  components: {
    Winelist,
    MyReviews,
    Reviews,
    ImgUpdate,
    PreferenceSetting,
    ChartContent,
    WineItem,
    InfiniteLoading,
  },
  watch: {},
  data: () => ({
    cnt: 4,
    page: 1,
    reviews: [],
    preferenceList: [],
    scarpList: [],
  }),
  created() {
    this.readUserInfo();
    // 1. 향 정보 읽어오기!!
    this.getFlavor();

    // 3. 스크랩 정보!!
    this.getScrap();
  },
  mounted() {
    this.readUserInfo();
    // 2. 리뷰 정보
    this.mypageReview(this.$refs.infiniteLoading.stateChanger);
  },
  watch: {
    reviewDialog: function () {
      this.page = 1;
      this.reviews = [];
      this.mypageReview(this.$refs.infiniteLoading.stateChanger);
    },
    profileDialog: function () {
      this.readUserInfo();
    },
  },
  computed: {
    ...mapState(["s3url_profile"]),
    ...mapState("nav", ["navActive"]),
    ...mapState("userInfo", ["userInfo"]),
    ...mapState("mypage", ["screenState", "flavors", "scraps"]),
    ...mapState("reviewDialog", ["reviewDialog"]),
    ...mapState("loginDialog", ["profileDialog"]),
  },
  methods: {
    ...mapActions("mypage", ["getMyPreference", "actGetScrap"]),
    ...mapActions("userInfo", ["readUserInfo"]),
    ...mapMutations("loginDialog", ["SET_PROFILE_TOGGLE"]),
    ...mapMutations("mypage", ["SET_SCREEN_STATE"]),
    // infiniteHandler, props 전달
    infiniteHandler($state) {
      this.mypageReview($state);
    },

    isBtnClick(index) {
      if (index == this.screenState) return true;
      else return false;
    },
    setStage(index) {
      this.SET_SCREEN_STATE(index);
    },
    async mypageReview($state) {
      try {
        const response = await mypageApi.mypageReview(this.page);
        if (response) {
          if (response.data.length === 0) {
            $state.complete();
          } else {
            this.reviews.push(...response.data);
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

    async getFlavor() {
      this.preferenceList = [];
      const response = await mypageApi.mypageFlavor();
      this.preferenceList = response.data;
    },

    async getScrap() {
      const response = await mypageApi.mypageScrap();
      this.scarpList = response.data;
    },

    updateScrap() {
      this.getScrap();
    },
    changeProfile() {
      this.SET_PROFILE_TOGGLE();
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
  background-color: var(--basic-color-background);
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
  display: flex;
  justify-content: center;
  overflow: hidden;
  background-color: white;
  /* background-image: url("https://blog.kakaocdn.net/dn/bezjux/btqCX8fuOPX/6uq138en4osoKRq9rtbEG0/img.jpg"); */
  /* background-size: contain; */
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

#review-wraps {
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 600px;
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

/* 스크랩 페이지!!!! */
.item-list {
  width: 100%;
  min-height: 600px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  flex-wrap: wrap;
  padding-left: 22px;
}

.wine-item {
  margin-left: 2px;
  margin-right: 2px;
}

.item-title {
  width: 60%;
  min-height: 80px;
  font-size: 32px;
  font-weight: 700;
  text-align: center;
  padding-top: 20px;
  border-radius: 1rem;
  background-color: #dadada;
}
</style>
