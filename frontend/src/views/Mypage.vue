<template>
  <!-- frame -> container -> wrap -->
  <div class="frame">
    <div class="item">
      <div class="left-wrap">
        <span class="nav-title">Mypage</span>
        <div class="nav-btn">
          <button class="nav-btn-item1" :class="{ 'nav-btn-active': isBtnClick(1) }" @click="onPreferenceSetting(1)">
            Scrap
          </button>
          <button class="nav-btn-item2" :class="{ 'nav-btn-active': isBtnClick(2) }" @click="onPreferenceSetting(2)">
            Review
          </button>
          <button class="nav-btn-item3" :class="{ 'nav-btn-active': isBtnClick(3) }" @click="onPreferenceSetting(3)">
            Statistics
          </button>
          <button class="nav-btn-item4" :class="{ 'nav-btn-active': isBtnClick(4) }" @click="onPreferenceSetting(4)">
            Favorite
          </button>
        </div>
      </div>
    </div>

    <div class="item">
      <div class="right-wrap">
        <div v-show="this.screenState != 4" class="content">
          <div class="imgBox">
            <img class="profile" :src="this.userInfo.profile" alt="프로필 이미지" />
          </div>
          <span id="userId">{{ this.userInfo.nickname }}</span>
          <div class="content-item" v-show="this.screenState == 1"></div>
          <div class="content-item" v-show="this.screenState == 2">
            <MyReviews v-for="(review, idx) in reviews" :key="idx" :review="review" :userInfo="userInfo" />
            <Reviews />
          </div>
          <div class="content-item" v-show="this.screenState == 3"></div>
        </div>
        <div v-show="this.screenState == 4" class="content4">
          <PreferenceSetting :preferenceList="getDiviedFlavors" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex';
import * as mypageApi from '@/api/mypageApi';
import MyReviews from '@/components/static/mypage/MyReviews.vue';
import Reviews from '@/components/static/reviews/Reviews.vue';
import PreferenceSetting from '@/components/static/mypage/PreferenceSetting.vue';

const SCRAP = 1;
const REVIEW = 2;
const STATISTICS = 3;
const FRAVORITE = 4;

export default {
  name: 'Mypage',
  components: {
    MyReviews,
    Reviews,
    PreferenceSetting,
  },
  data: () => ({
    cnt: 4,
    screenState: 1,
    reviews: [],
    preferenceList: [],
  }),
  computed: {
    ...mapState('userInfo', ['userInfo']),
    ...mapState('nav', ['navActive']),
    ...mapState('mypage', ['flavors','scraps']),
    ...mapGetters('mypage', ['getDiviedFlavors']),
  },
  methods: {
    ...mapActions('mypage', ['actGetFlavor','actGetScrap']),
    isBtnClick(index) {
      if (index == this.screenState) return true;
      else return false;
    },
    onPreferenceSetting(index) {
      this.screenState = index;
    },
    mypageReview(num) {
      return mypageApi.mypageReview(num);
    },
  },
  created() {
    this.actGetFlavor().then(() => {
      console.log(this.getDiviedFlavors);
    });
    
    this.actGetScrap();

    const mypageReviews = this.mypageReview(1);

    mypageReviews.then((response) => {
      this.reviews = response.data;
    });
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

.content4 {
  position: relative;
  margin-top: 150px;
  width: 90%;
  height: auto;
}
</style>