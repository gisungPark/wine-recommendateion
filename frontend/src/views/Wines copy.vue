<template>
  <div id="wines">
    <transition name="slide">
      <div id="sidebar">
        <div class="sidebar-container">
          <span class="nav-title b-desc">Wines</span>
          <div class="nav-btn b-desc">
            <button @click="clickedBtn(1)">
              <span>price</span>
            </button>
            <button @click="clickedBtn(2)">
              <span>Point</span>
            </button>
            <button @click="clickedBtn(3)">
              <span>Wine Kind</span>
            </button>
            <button @click="clickedBtn(4)">
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
        <select v-model="sort" class="style-chooser">
          <option disabled value="0">Sort</option>
          <option value="0">낮은 가격순</option>
          <option value="1">높은 가격순</option>
          <option value="2">낮은 별점순</option>
          <option value="3">높은 별점순</option>
        </select>
      </div>
      <div style="width: 100%">
        <transition-group name="slideup" mode="out-in">
          <Winelist class="winelist" key="0" :wines="wines" />
        </transition-group>
      </div>

      <!-- ################################ -->
      <!-- 필터링 모달 ###################### -->
      <!-- 1. 가격 필터링!!!  -->
      <div
        v-show="this.contentState === 1"
        id="priceModal"
        class="fillter-modal"
      >
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
            @click="onSearch"
          ></v-range-slider>
        </div>
        <div id="price-slider-label">
          <span id="label-left">0</span>
          <span id="label-right">50000</span>
        </div>
      </div>

      <!-- 2. 별점 필터링!!!  -->
      <div
        v-show="this.contentState === 2"
        id="scoreModal"
        class="fillter-modal"
        @click="onSearch"
      >
        <div id="scoreModal-info">
          <span id="scoreModal-info-item1">Point?</span>
          <span id="scoreModal-info-item2">{{ this.pointFilter }} 점대</span>
        </div>
        <v-rating
          v-model="pointFilter"
          color="#F9DC14"
          background-color="grey darken-1"
          size="50"
          hover
        ></v-rating>
        <v-btn id="resetBtn" @click="onReset">Reset</v-btn>
      </div>

      <!-- 3. 와인 종류 필터링!!!  -->
      <div
        v-show="this.contentState === 3"
        id="kindModal"
        class="fillter-modal"
      >
        <div id="scoreModal-info">
          <span id="scoreModal-info-item1">What kind?</span>
          <span id="scoreModal-info-item2"></span>
        </div>
        <div id="kindModal-item">
          <div class="checks">
            <input type="radio" id="red" value="레드" v-model="typeFilter" />
            <label for="red">Red</label>
          </div>
          <div class="checks">
            <input
              type="radio"
              id="white"
              value="화이트"
              v-model="typeFilter"
            />
            <label for="white">White</label>
          </div>
          <div class="checks">
            <input
              type="radio"
              id="sparkling"
              value="스파클링"
              v-model="typeFilter"
            />
            <label for="sparkling">Sparkling</label>
          </div>
        </div>
      </div>

      <!-- 4. 품종 필터링!!!  -->
      <div
        v-show="this.contentState === 4"
        id="grapeModal"
        class="fillter-modal"
        @click="onSearch"
      >
        <div id="scoreModal-info">
          <span id="scoreModal-info-item1">Grape?</span>
          <span id="scoreModal-info-item2"></span>
        </div>
        <div style="height: 10px"></div>
        <select v-model="grapeFilter" class="grape-chooser">
          <option disabled value="전체">품종을 선택하세요</option>
          <option value="전체">전체</option>
          <option value="피노누아">피노누아</option>
          <option value="쉬라즈">쉬라즈</option>
          <option value="소비뇽블랑">소비뇽블랑</option>
          <option value="카베르네 소비뇽">카베르네 소비뇽</option>
          <option value="샤르도네">샤르도네</option>
          <option value="메를로">메를로</option>
          <option value="모스카토">모스카토</option>
          <option value="말벡">말벡</option>
          <option value="카르메네르">카르메네르</option>
        </select>
      </div>
      <!-- end 필터링 모달 ###################### -->
      <!-- ################################ -->
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import * as wineApi from "@/api/wine";
import Winelist from "@/components/articles/Winelist.vue";

export default {
  name: "Wines",
  components: {
    Winelist,
  },
  created() {
    this.onSearch();
  },
  watch: {
    typeFilter: function () {
      this.onSearch();
    },
  },
  data: () => ({
    contentState: -1,
    page: 1,
    keyword: "", // 검색어
    sort: 0, // 정렬 조건
    pointFilter: 0, // 별점 조건
    priceRange: [0, 50000], //startPrice, endPrice
    grapeFilter: "전체", //품종 조건
    typeFilter: "전체", // 와인 종류 조건
    wines: [],
  }),
  computed: {
    ...mapState("userInfo", ["userInfo"]),
    ...mapState("nav", ["navActive"]),
  },
  methods: {
    clickedBtn(index) {
      if (this.contentState === index) this.contentState = -1;
      else this.contentState = index;
    },
    onReset() {
      this.pointFilter = 0;
    },
    async onSearch() {
      const response = await wineApi.search(
        this.page,
        this.keyword,
        this.sort,
        this.pointFilter,
        this.typeFilter,
        this.grapeFilter,
        this.priceRange[0],
        this.priceRange[1]
      );
      console.log(response);
      if (response.status == 200) {
        this.wines = response.data;
        for (var i = 0; i < this.wines.length; i++) {
          console.log(
            this.wines[i].wineId +
              "/ " +
              this.wines[i].kname +
              "/ " +
              this.wines[i].grape.kname +
              "/ " +
              this.wines[i].avg +
              "/ " +
              this.wines[i].price
          );
        }
        console.log("###########################");
      }
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
  height: 100%;
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

#select-box {
  position: relative;
  margin-left: 65%;
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

/* end 사이드 바 */
/* #################################################### */
.style-chooser {
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

.fillter-modal {
  width: 350px;
  height: 180px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: #f4f4f4;
  border-radius: 2em;
  opacity: 0.7;
}

#priceModal {
  position: fixed;
  left: 160px;
  bottom: 75px;
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
/* 점수 모달 #################################### */
#scoreModal {
  position: fixed;
  left: 160px;
  bottom: 210px;
}
#scoreModal-info {
  width: 100%;
  margin-bottom: 20px;
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

#resetBtn {
  position: relative;
  left: 120px;
  width: 50px;
  margin-top: 8px;
  color: white;
  background-color: var(--basic-color-bg2);
}
/* 와인 종류 모달 #################################### */
#kindModal {
  position: fixed;
  left: 160px;
  bottom: 345px;
}
#kindModal-item {
  width: 100%;
  height: 55px;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
}
.checks {
  position: relative;
}
.checks input[type="radio"] {
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  border: 0;
}
.checks input[type="radio"] + label {
  display: inline-block;
  position: relative;
  padding-left: 30px;
  cursor: pointer;
  font-size: 18px;
  font-weight: 520;
}
.checks input[type="radio"] + label:before {
  content: "";
  position: absolute;
  left: 0;
  top: -4px;
  width: 21px;
  height: 21px;
  text-align: center;
  background: #fafafa;
  border: 1px solid #cacece;
  border-radius: 100%;
  box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.05),
    inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05);
}
.checks input[type="radio"] + label:active:before,
.checks input[type="radio"]:checked + label:active:before {
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05),
    inset 0px 1px 3px rgba(0, 0, 0, 0.1);
}
.checks input[type="radio"]:checked + label:before {
  background: #e9ecee;
  border-color: #adb8c0;
}
.checks input[type="radio"]:checked + label:after {
  content: "";
  position: absolute;
  left: 4px;
  width: 13px;
  height: 13px;
  /* background: #99a1a7; */
  /* background: var(--basic-color-bg2); */
  border-radius: 100%;
  box-shadow: inset 0px 0px 10px rgba(0, 0, 0, 0.3);
}

#red:checked + label:after {
  background: var(--basic-color-bg2);
}

#white:checked + label:after {
  background: var(--basic-color-bg3);
}
#sparkling:checked + label:after {
  background: var(--basic-color-bg4);
}

#grapeModal {
  position: fixed;
  left: 160px;
  bottom: 480px;
}

.grape-chooser {
  width: 85%; /* 원하는 너비설정 */
  height: 45px;
  font-size: 18px;
  /* padding: 0.8em 0.5em; 여백으로 높이 설정 */
  padding-left: 5px;
  background: url(../assets/images/arrow.png) no-repeat 95% 50%; /* 네이티브 화살표 대체 */
  color: white;
  background-color: var(--basic-color-bg);
  border: 1px solid var(--basic-color-bg);
  border-radius: 5px; /* iOS 둥근모서리 제거 */
  -webkit-appearance: none; /* 네이티브 외형 감추기 */
  -moz-appearance: none;
  appearance: none;
}
</style>
