<template>
  <div class="tabs">
    <input id="all" type="radio" name="tab_item" checked />
    <label class="tab_item" for="all">Tab 1</label>
    <input id="programming" type="radio" name="tab_item" />
    <label class="tab_item" for="programming">Tab 2</label>
    <input id="design" type="radio" name="tab_item" />
    <label class="tab_item" for="design">Tab 3</label>
    <div class="tab_content" id="all_content">
      <div class="chart-wrap">
        <WinePie :basicList="basicList" />
      </div>
      <div class="chart-wrap">
        <GrapePie :grapeList="grapeList" />
      </div>
    </div>
    <div class="tab_content" id="programming_content">
      <div class="chart-wrap">
        <FlavorDoughnut :flavorList="flavorList" />
      </div>
      <div class="chart-wrap">
        <TasteRadar :tasteList="tasteList" />
      </div>
    </div>
    <div class="tab_content" id="design_content">
      <div class="content-item">
        <div class="item-title">
          {{ this.userInfo.nickname }}님의 입맛과 가장 유사한 유저가 뽑은
          <span>BEST 5</span>
        </div>
        <div class="item-sub"></div>
        <div class="item-list"></div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from "vuex";
import WinePie from "@/components/static/mypage/chart/WinePie.vue";
import GrapePie from "@/components/static/mypage/chart/GrapePie.vue";
import FlavorDoughnut from "@/components/static/mypage/chart/FlavorDoughnut.vue";
import TasteRadar from "@/components/static/mypage/chart/TasteRadar.vue";
import * as chartApi from "@/api/chart.js";

export default {
  name: "ChartContent",
  components: {
    WinePie,
    GrapePie,
    FlavorDoughnut,
    TasteRadar,
  },
  data: () => ({
    state: false,
    basicList: [],
    grapeList: [],
    flavorList: [],
    tasteList: [],
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
    async getBasic() {
      const response = await chartApi.getBasicStatistic();
      this.basicList = new Array(
        response.data.redCnt,
        response.data.whiteCnt,
        response.data.spaclingCnt
      );
      this.grapeList = response.data.grapeCntList;
      this.grapeList.shift();
      console.log(this.grapeList);
    },

    async getFlavor() {
      const response = await chartApi.getflavorStatistic();
      this.flavorList = response.data.flavorCntList;
      this.tasteList = new Array(
        response.data.acidityAvg,
        response.data.bodyAvg,
        response.data.sweetAvg,
        response.data.tanninAvg
      );
    },

    async getSimilar() {
      const response = await chartApi.getSimilarStatistic();
      console.log("#############");
      console.log(response);
      console.log("#############");
    },
  },
};
</script>

<style scpoped>
#frame {
  width: 90%;
  min-height: 900px;
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

.content-item {
  width: 100%;
  height: 100px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.item-title {
  width: 60%;
  height: 70px;
  background-color: darkgray;
  font-size: 32px;
  font-weight: 700;
  text-align: center;
  padding-top: 17px;
  border-radius: 1rem;
}
.item-title > span {
  color: #d89f2e;
}

/* 탭 전체 스타일 */
.tabs {
  width: 90%;
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
  color: #ffffff;
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
  min-height: 600px;
  display: none;
  clear: both;
  padding: 40px 40px 0;
  background-color: #ffffff;
  overflow: hidden;
}

/* 선택 된 탭 콘텐츠를 표시 */
#all:checked ~ #all_content,
#programming:checked ~ #programming_content,
#design:checked ~ #design_content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

/* 선택된 탭 스타일 */
.tabs input:checked + .tab_item {
  background-color: #fff;
  color: #8c0a0a;
}
</style>