<template>
  <div id="wines">
    <transition name="slide">
      <div id="sidebar">
        <div class="sidebar-container">
          <span class="nav-title b-desc">Wines</span>
          <div class="nav-btn b-desc">
            <button
              :class="{ active: contentState === 1 }"
              @click="[clickedBtn(1), onPriceFillter()]"
            >
              <span>price</span>
            </button>
            <button
              :class="{ active: contentState === 2 }"
              @click="[clickedBtn(2), onScoreFillter()]"
            >
              <span>Point</span>
            </button>
            <button
              :class="{ active: contentState === 3 }"
              @click="clickedBtn(3)"
            >
              <span>Wine Kind</span>
            </button>
            <button
              :class="{ active: contentState === 4 }"
              @click="clickedBtn(4)"
            >
              <span>Grape</span>
            </button>
          </div>
        </div>
      </div>
    </transition>
    <div class="content">
      <div class="search">
        <input
          id="searchInput"
          type="text"
          placeholder="search your wine"
          v-model="keyword"
          @keyup.enter="onSearch"
        />
        <div class="search-icon-wrap" @click="onSearch">
          <svg
            id="search-icon"
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
          >
            <path
              d="M23.832 19.641l-6.821-6.821c2.834-5.878-1.45-12.82-8.065-12.82-4.932 0-8.946 4.014-8.946 8.947 0 6.508 6.739 10.798 12.601 8.166l6.879 6.879c1.957.164 4.52-2.326 4.352-4.351zm-14.886-4.721c-3.293 0-5.973-2.68-5.973-5.973s2.68-5.973 5.973-5.973c3.294 0 5.974 2.68 5.974 5.973s-2.68 5.973-5.974 5.973z"
            />
          </svg>
        </div>
      </div>
      <div id="select-box">
        <select v-model="selected" class="style-chooser">
          <option disabled value="">Sort</option>
          <option>낮은 가격순</option>
          <option>높은 가격순</option>
          <option>낮은 별점순</option>
          <option>높은 별점순</option>
        </select>
      </div>

      <transition-group name="slideup" mode="out-in">
        <!-- <Winelist class="winelist" key="0" :wines="wines0" v-if="contentState === 0" />
        <Winelist class="winelist" key="1" :wines="wines1" v-if="contentState === 1" />
        <Winelist class="winelist" key="2" :wines="wines1" v-if="contentState === 2" />
        <Winelist class="winelist" key="3" :wines="wines2" v-if="contentState === 3" /> -->
      </transition-group>
      <div
        v-for="(item, index) in 5"
        :key="index"
        style="
          width: 90%;
          height: 150px;
          background-color: #707070;
          margin: 5px;
        "
      ></div>

      <!-- ################################ -->
      <!-- 필터링 모달 ###################### -->

      <!-- 1. 가격 필터링!!!  -->
      <div v-show="this.priceFillter" id="priceModal">
        <div id="scoreModal-info">
          <span id="scoreModal-info-item1">Price?</span>
          <span id="scoreModal-info-item2">
            {{ this.priceRange[0] }} ~ {{ this.priceRange[1] }}
          </span>
        </div>
        <div id="price-slider">
          <v-range-slider
            v-model="priceRange"
            max="50000"
            min="0"
            step="10000"
            color="#821a33"
            track-fill-color="#821a33"
          ></v-range-slider>
        </div>
        <div id="price-slider-label">
          <span id="label-left">0</span>
          <span id="label-right">50000</span>
        </div>
      </div>

      <!-- 2. 별점 필터링!!!  -->
      <div v-show="this.scoreFillter" id="scoreModal">
        <div id="scoreModal-info">
          <span id="scoreModal-info-item1">Point?</span>
          <span id="scoreModal-info-item2">{{ this.rating }} 점대</span>
        </div>
        <v-rating
          v-model="rating"
          color="#F9DC14"
          background-color="grey darken-1"
          size="50"
          hover
        ></v-rating>
      </div>
      <!-- end 필터링 모달 ###################### -->
      <!-- ################################ -->
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import Winelist from "@/components/articles/Winelist.vue";

export default {
  name: "Wines",
  components: {
    Winelist,
  },

  data: () => ({
    contentState: 1,
    keyword: "",
    priceFillter: false,
    scoreFillter: false,
    priceRange: [0, 50000],
    rating: 5,
    selected: "",
  }),
  computed: {
    ...mapState("userInfo", ["userInfo"]),
    ...mapState("nav", ["navActive"]),
  },
  methods: {
    clickedBtn(index) {
      this.contentState = index;
    },
    onSearch() {
      alert(this.keyword + " 를 검색하셨습니다.");
    },
    onPriceFillter() {
      this.priceFillter = !this.priceFillter;
    },
    onScoreFillter() {
      this.scoreFillter = !this.scoreFillter;
    },
  },
};
</script>

<style scoped>
/* 트랜지션 */
.slideup-leave-active {
  transition: transform 0.5s cubic-bezier(0, 1, 0.65, 1);
}
.slideup-enter-active {
  transition: transform 0.5s cubic-bezier(0, 1, 0.65, 1) 0.5s;
}
.slideup-enter,
.slideup-leave-to {
  transform: translate(0, 100%);
}
.slideup-enter-to,
.slideup-leave {
  transform: translate(0, 0);
}

#wines {
  width: 100%;
  height: 100vh;
  display: flex;
  flex-direction: row;
}
.content {
  width: 100%;
  padding-left: 172px;
  padding-top: 100px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.search {
  width: 75%;
  display: flex;
  justify-content: center;
  background-color: rgba(0, 0, 0, 0.488);
}
#searchInput {
  width: 85%;
  font-size: 22px;
  color: white;
  border: 1px solid white;
  border-top-width: 0px;
  border-left-width: 0px;
  border-right-width: 0px;
  border-bottom-width: 1px;
}
.search-icon-wrap {
  display: flex;
  align-items: flex-end;
}

#search-icon {
  fill: white;
  width: 50px;
  height: 50px;
  margin-bottom: 5px;
}
/* #################################################### */
/* 사이드 바 */
#sidebar {
  width: 172px;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  border-right: 1px solid var(--basic-color-key);
  background-color: var(--basic-color-fill2);
}

.sidebar-container {
  position: relative;
  width: 95vh;
  height: 58px;
  transform: rotate(-90deg) translate(-102%, 100%);
  transform-origin: top left;
}
.nav-title {
  color: var(--basic-color-fill);
  font-size: 3rem;
}
.nav-btn {
  padding-top: 1rem;
}
.nav-btn button {
  width: 120px;
  height: 2rem;
  line-height: 2rem;
  font-size: 1rem;
  margin-right: 1rem;
  color: #a3a1a1;
  border-radius: 1rem;
  border: 1px solid var(--basic-color-bg);
  background: var(--basic-color-bg);
  transition: all 0.3s ease;
}
.nav-btn button:hover,
.nav-btn button:active {
  background-color: #e1a95744;
  border: 1px solid var(--basic-color-key);
}
.nav-btn button > span {
  position: relative;
  top: -0.15em;
}
.active {
  border: 1px solid var(--basic-color-key) !important;
}

#btnLogo {
  width: 12px;
  height: 12px;
  background-color: var(--basic-color-bg2);
  border-radius: 70%;
  position: relative;
  top: 10px;
  left: 1px;
}

#select-box {
  position: relative;
  left: 32%;
  top: 5px;
  margin-bottom: 30px;
}

select {
  width: 180px; /* 원하는 너비설정 */
  height: 45px;
  font-size: 18px;
  /* padding: 0.8em 0.5em; 여백으로 높이 설정 */
  padding-left: 5px;
  background: url(../assets/images/arrow.png) no-repeat 95% 50%; /* 네이티브 화살표 대체 */
  color: white;
  background-color: var(--basic-color-bg);
  border: 1px solid var(--basic-color-bg);
  border-radius: 3px; /* iOS 둥근모서리 제거 */
  -webkit-appearance: none; /* 네이티브 외형 감추기 */
  -moz-appearance: none;
  appearance: none;
}

/* #################################################### */
/* 각종 필터 */
#priceModal {
  width: 350px;
  height: 180px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: fixed;
  left: 160px;
  bottom: 75px;
  background-color: #f4f4f4;
  border-radius: 2em;
  opacity: 0.7;
}
#price-slider {
  width: 85%;
  display: flex;
  flex-direction: row;
}
#price-slider-label {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: flex-end;
}
#label-left {
  position: relative;
  top: -20px;
  font-size: 15px;
  margin-left: 30px;
  color: var(--basic-color-fill);
}
#label-right {
  position: relative;
  top: -20px;
  font-size: 15x;
  margin-right: 10px;
  color: var(--basic-color-fill);
}

#scoreModal {
  width: 350px;
  height: 180px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: fixed;
  left: 160px;
  bottom: 210px;
  background-color: #f4f4f4;
  border-radius: 2em;
  opacity: 0.7;
}
#scoreModal-info {
  width: 100%;
  margin-bottom: 30px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: flex-end;
}
#scoreModal-info-item1 {
  font-size: 32px;
  font-weight: 700;
  margin-left: 25px;
  color: var(--basic-color-bg2);
}
#scoreModal-info-item2 {
  font-size: 23px;
  margin-right: 10px;
  color: var(--basic-color-fill);
}
</style>
