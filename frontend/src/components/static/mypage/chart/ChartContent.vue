<template>
  <div class="tabs">
    <input id="preference" type="radio" name="tab_item" checked />
    <label class="tab_item" for="preference">Preference</label>
    <input id="taste" type="radio" name="tab_item" />
    <label class="tab_item" for="taste">Taste / Flavor</label>
    <input id="similar" type="radio" name="tab_item" />
    <label class="tab_item" for="similar">Similar</label>
    <!-- ################################################################################## -->
    <div class="tab_content" id="preference_content">
      <div v-show="this.writeFlag1 == false" class="review-write-div">
        <div>
          <span class="review-ment"> 리뷰를 먼저 작성해 주세요!</span>
        </div>
        <div class="review-write-btn" @click="onReviewWrite">
          <span class="review-ment">리뷰 쓰러가기</span
          ><svg
            :style="{ fill: '#d89f2e' }"
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
          >
            <path
              d="M14.078 4.232l-12.64 12.639-1.438 7.129 7.127-1.438 12.641-12.64-5.69-5.69zm-10.369 14.893l-.85-.85 11.141-11.125.849.849-11.14 11.126zm2.008 2.008l-.85-.85 11.141-11.125.85.85-11.141 11.125zm18.283-15.444l-2.816 2.818-5.691-5.691 2.816-2.816 5.691 5.689z"
            />
          </svg>
        </div>
      </div>
      <div class="review-true" v-show="this.writeFlag1 == true">
        <div class="item-title_small gray">선호하는 와인 종류</div>
        <div class="item-sub">
          <svg
            :style="{ fill: '#d89f2e' }"
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
          >
            <path
              d="M12 0c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm-.001 5.75c.69 0 1.251.56 1.251 1.25s-.561 1.25-1.251 1.25-1.249-.56-1.249-1.25.559-1.25 1.249-1.25zm2.001 12.25h-4v-1c.484-.179 1-.201 1-.735v-4.467c0-.534-.516-.618-1-.797v-1h3v6.265c0 .535.517.558 1 .735v.999z"
            />
          </svg>
          <span class="best"
            >{{ this.userInfo.nickname }}님이 지금까지 남긴 와인 리뷰를 바탕으로
            한 와인 종류 수치입니다.</span
          >
        </div>
        <div class="chart-wrap">
          <WinePie :basicList="basicList" />
        </div>
        <div style="height: 30px"></div>
        <div class="item-title_small gray">선호하는 품종 종류</div>
        <div class="item-sub">
          <svg
            :style="{ fill: '#d89f2e' }"
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
          >
            <path
              d="M12 0c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm-.001 5.75c.69 0 1.251.56 1.251 1.25s-.561 1.25-1.251 1.25-1.249-.56-1.249-1.25.559-1.25 1.249-1.25zm2.001 12.25h-4v-1c.484-.179 1-.201 1-.735v-4.467c0-.534-.516-.618-1-.797v-1h3v6.265c0 .535.517.558 1 .735v.999z"
            />
          </svg>
          <span class="best"
            >{{ this.userInfo.nickname }}님이 지금까지 남긴 와인 리뷰를 바탕으로
            한 와인 품종 수치입니다.</span
          >
        </div>
        <div class="chart-wrap2">
          <GrapePie :grapeList="grapeList" />
        </div>
      </div>
      <div style="height: 50px"></div>
    </div>
    <!-- ################################################################################## -->
    <div class="tab_content" id="taste_content">
      <div v-show="this.writeFlag2 == false" class="review-write-div">
        <div>
          <span class="review-ment"> 리뷰를 먼저 작성해 주세요!</span>
        </div>
        <div class="review-write-btn" @click="onReviewWrite">
          <span class="review-ment">리뷰 쓰러가기</span
          ><svg
            :style="{ fill: '#d89f2e' }"
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
          >
            <path
              d="M14.078 4.232l-12.64 12.639-1.438 7.129 7.127-1.438 12.641-12.64-5.69-5.69zm-10.369 14.893l-.85-.85 11.141-11.125.849.849-11.14 11.126zm2.008 2.008l-.85-.85 11.141-11.125.85.85-11.141 11.125zm18.283-15.444l-2.816 2.818-5.691-5.691 2.816-2.816 5.691 5.689z"
            />
          </svg>
        </div>
      </div>
      <div class="review-true" v-show="this.writeFlag2 == true">
        <div class="item-title_small gray">와인 향 통계</div>
        <div class="item-sub">
          <svg
            :style="{ fill: '#d89f2e' }"
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
          >
            <path
              d="M12 0c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm-.001 5.75c.69 0 1.251.56 1.251 1.25s-.561 1.25-1.251 1.25-1.249-.56-1.249-1.25.559-1.25 1.249-1.25zm2.001 12.25h-4v-1c.484-.179 1-.201 1-.735v-4.467c0-.534-.516-.618-1-.797v-1h3v6.265c0 .535.517.558 1 .735v.999z"
            />
          </svg>
          <span class="best"
            >{{ this.userInfo.nickname }}님이 지금까지 남긴 와인 리뷰를 바탕으로
            한 와인 향 통계 수치입니다.</span
          >
        </div>
        <div class="chart-wrap2">
          <FlavorDoughnut :flavorList="flavorList" />
        </div>
        <div style="height: 30px"></div>
        <div class="item-title_small gray">와인 맛 통계</div>
        <div class="item-sub">
          <svg
            :style="{ fill: '#d89f2e' }"
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
          >
            <path
              d="M12 0c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm-.001 5.75c.69 0 1.251.56 1.251 1.25s-.561 1.25-1.251 1.25-1.249-.56-1.249-1.25.559-1.25 1.249-1.25zm2.001 12.25h-4v-1c.484-.179 1-.201 1-.735v-4.467c0-.534-.516-.618-1-.797v-1h3v6.265c0 .535.517.558 1 .735v.999z"
            />
          </svg>
          <span class="best"
            >{{ this.userInfo.nickname }}님이 지금까지 남긴 와인 리뷰를 바탕으로
            한 와인 맛 통계 수치입니다.</span
          >
        </div>
        <div style="height: 20px"></div>
        <div class="chart-wrap">
          <TasteRadar :tasteList="tasteList" />
        </div>
      </div>
      <div style="height: 50px"></div>
    </div>
    <!-- ##########################################################x######################## -->
    <div class="tab_content" id="similar_content">
      <div v-show="this.writeFlag3 == false" class="review-write-div">
        <div>
          <span class="review-ment"> 리뷰를 먼저 작성해 주세요!</span>
        </div>
        <div class="review-write-btn" @click="onReviewWrite">
          <span class="review-ment">리뷰 쓰러가기</span
          ><svg
            :style="{ fill: '#d89f2e' }"
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
          >
            <path
              d="M14.078 4.232l-12.64 12.639-1.438 7.129 7.127-1.438 12.641-12.64-5.69-5.69zm-10.369 14.893l-.85-.85 11.141-11.125.849.849-11.14 11.126zm2.008 2.008l-.85-.85 11.141-11.125.85.85-11.141 11.125zm18.283-15.444l-2.816 2.818-5.691-5.691 2.816-2.816 5.691 5.689z"
            />
          </svg>
        </div>
      </div>
      <div class="review-true" v-show="this.writeFlag3 == true">
        <div class="content-item">
          <div class="item-title gray">
            {{ this.userInfo.nickname }}님의 입맛과 가장 유사한 유저가 뽑은
            <span class="best">BEST 5</span>
          </div>
          <div class="item-sub">
            <svg
              :style="{ fill: '#d89f2e' }"
              xmlns="http://www.w3.org/2000/svg"
              width="24"
              height="24"
              viewBox="0 0 24 24"
            >
              <path
                d="M12 0c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm-.001 5.75c.69 0 1.251.56 1.251 1.25s-.561 1.25-1.251 1.25-1.249-.56-1.249-1.25.559-1.25 1.249-1.25zm2.001 12.25h-4v-1c.484-.179 1-.201 1-.735v-4.467c0-.534-.516-.618-1-.797v-1h3v6.265c0 .535.517.558 1 .735v.999z"
              />
            </svg>
            <span class="best"
              >{{ this.userInfo.nickname }}님과 입맛이 가장 유사한 유저분들이
              평점을 높게 준 와인 리스트 기준입니다.</span
            >
          </div>
          <div style="height: 50px"></div>
          <div class="item-list">
            <div
              class="wine-item"
              v-for="(item, index) in similarHighList"
              :key="item + index"
            >
              <div class="wine-rank">
                <span class="best">BEST {{ index + 1 }}</span>
              </div>
              <WineItem :wine="item" />
            </div>
          </div>
        </div>
        <div class="content-item">
          <div class="item-title gray">
            {{ this.userInfo.nickname }}님의 입맛과 가장 유사한 유저가 뽑은
            <span class="worst">WORST 5</span>
          </div>
          <div class="item-sub">
            <svg
              :style="{ fill: '#d89f2e' }"
              xmlns="http://www.w3.org/2000/svg"
              width="24"
              height="24"
              viewBox="0 0 24 24"
            >
              <path
                d="M12 0c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm-.001 5.75c.69 0 1.251.56 1.251 1.25s-.561 1.25-1.251 1.25-1.249-.56-1.249-1.25.559-1.25 1.249-1.25zm2.001 12.25h-4v-1c.484-.179 1-.201 1-.735v-4.467c0-.534-.516-.618-1-.797v-1h3v6.265c0 .535.517.558 1 .735v.999z"
              /></svg
            ><span class="best"
              >{{ this.userInfo.nickname }}님과 입맛이 가장 유사한 유저분들이
              평점을 낮게 준 와인 리스트 기준입니다.</span
            >
          </div>
          <div style="height: 50px"></div>
          <div class="item-list">
            <div
              class="wine-item"
              v-for="(item, index) in similarLowList"
              :key="item + index"
            >
              <div class="wine-rank">
                <span class="worst">WORST {{ index + 1 }}</span>
              </div>
              <WineItem :wine="item" />
            </div>
          </div>
        </div>
      </div>
    </div>
    <div style="height: 50px"></div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from "vuex";
import * as chartApi from "@/api/chart.js";
import WinePie from "@/components/static/mypage/chart/WinePie.vue";
import GrapePie from "@/components/static/mypage/chart/GrapePie.vue";
import FlavorDoughnut from "@/components/static/mypage/chart/FlavorDoughnut.vue";
import TasteRadar from "@/components/static/mypage/chart/TasteRadar.vue";
import WineItem from "@/components/articles/WineItemRec.vue";

export default {
  name: "ChartContent",
  components: {
    WinePie,
    GrapePie,
    FlavorDoughnut,
    TasteRadar,
    WineItem,
  },
  data: () => ({
    state: false,
    basicList: [],
    grapeList: [],
    flavorList: [],
    tasteList: [],
    similarHighList: [],
    similarLowList: [],
    writeFlag1: false,
    writeFlag2: false,
    writeFlag3: false,
  }),
  created() {
    this.getBasic();
    this.getFlavor();
    this.getSimilar();
  },
  computed: {
    ...mapState("userInfo", ["userInfo"]),
  },
  methods: {
    onClick() {
      this.state = !this.state;
    },
    onReviewWrite() {
      this.$router.push({ name: "Wines" });
    },
    async getBasic() {
      const response = await chartApi.getBasicStatistic();
      this.writeFlag1 = response.data.writeReview;
      this.basicList = new Array(
        response.data.redCnt,
        response.data.whiteCnt,
        response.data.spaclingCnt
      );
      this.grapeList = response.data.grapeCntList;
      if (this.grapeList !== null && this.grapeList !== undefined) {
        this.grapeList.shift();
      }
    },

    async getFlavor() {
      const response = await chartApi.getflavorStatistic();
      this.writeFlag2 = response.data.writeReview;
      this.flavorList = response.data.flavorCntList;
      if (this.flavorList !== null && this.flavorList !== undefined) {
        this.flavorList.shift();
      }
      this.tasteList = new Array(
        response.data.acidityAvg,
        response.data.bodyAvg,
        response.data.sweetAvg,
        response.data.tanninAvg
      );
    },

    async getSimilar() {
      const response = await chartApi.getSimilarStatistic();
      this.writeFlag3 = response.data.writeReview;
      this.similarHighList = response.data.similarHighList;
      this.similarLowList = response.data.similarLowList;
    },
  },
};
</script>

<style scpoped>
#frame {
  width: 90%;
  height: 100%;
  border-radius: 2em;
  background-color: white;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.content-wrap {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.chart-wrap {
  width: 600px;
  height: 600px;
}
.chart-wrap2 {
  width: 650px;
  height: 650px;
}
.content-item {
  width: 100%;
  min-height: 600px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.item-title {
  width: 60%;
  min-height: 80px;
  font-size: 32px;
  font-weight: 700;
  text-align: center;
  padding-top: 20px;
  border-radius: 1rem;
}
.item-title > span {
  font-weight: 800;
}
.item-title_small {
  width: 60%;
  min-height: 80px;
  font-size: 32px;
  font-weight: 700;
  text-align: center;
  padding-top: 20px;
  border-radius: 1rem;
}
.item-sub {
  margin-top: 15px;
}
.item-sub > span {
  font-size: 21px;
  text-align: center;
}
.review-write-div {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.review-true {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.review-ment {
  font-size: 32px;
  font-weight: 700;
  color: #d89f2e;
}
.review-write-btn :hover {
  cursor: pointer;
}

.item-list {
  width: 100%;
  min-height: 600px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.wine-item {
  margin-left: 2px;
  margin-right: 2px;
}
.wine-rank {
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-bottom: 20px;
}
.wine-rank > span {
  font-size: 32px;
  font-weight: 700;
}
.best {
  color: #d89f2e;
}
.worst {
  color: #6b6767;
}
.gray {
  background-color: #dadada;
}

/* 탭 전체 스타일 */
.tabs {
  width: 90%;
  height: 100%;
  margin-top: 50px;
  padding-bottom: 40px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  margin: 0 auto;
}

/* 탭 스타일 */
.tab_item {
  width: calc(100% / 3);
  height: 55px;
  background-color: #8c0a0a;
  line-height: 50px;
  font-size: 30px;
  text-align: center;
  color: var(--basic-color-background);
  display: block;
  float: left;
  text-align: center;
  font-weight: bold;
  transition: all 0.2s ease;
  border-left: 2px solid black;
  border-right: 2px solid black;
  border-radius: 12px 12px 0 0;
  box-shadow: 2px 0 2px rgba(0, 0, 0, 0.1), -2px 0 2px rgba(0, 0, 0, 0.1);
}
.tab_item:hover {
  opacity: 0.75;
}

/* 라디오 버튼 UI삭제*/
input[name="tab_item"] {
  display: none;
}

/* 탭 컨텐츠 스타일 */
.tab_content {
  display: none;
  clear: both;
  padding: 40px 40px 0;
  background-color: var(--basic-color-background);
  overflow: hidden;
}
/* 선택 된 탭 콘텐츠를 표시 */
#preference:checked ~ #preference_content,
#taste:checked ~ #taste_content,
#similar:checked ~ #similar_content {
  min-height: 900px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

/* 선택된 탭 스타일 */
.tabs input:checked + .tab_item {
  background-color: var(--basic-color-background);
  color: #8c0a0a;
}
</style>