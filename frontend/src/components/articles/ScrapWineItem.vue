<template>
  <div
    class="wineitem fadeup"
    :style="{ marginTop: wine.marginTop }"
    :class="{
      red: wine.type === '레드',
      white: wine.type === '화이트',
      rose: wine.type === '스파클링',
    }"
  >
    <div class="review-icon">
      <div class="review-icon-1" @click="onDelete(wine.wineId)"></div>
    </div>
    <div @click="clickedWine(wine.wineId)">
      <img
        class="wineitem-img"
        :src="`${this.s3url}${wine.wineId}.png`"
        alt="와인 이미지"
      />
      <div class="wineitem-info">
        <div class="info-title">
          <p>{{ wine.kname }}</p>
          <hr />
          <p>{{ wine.ename }}</p>
        </div>
        <div class="info-sub">
          <span>종류</span><span>{{ wine.type }}</span>
        </div>
        <div class="info-sub">
          <span>연도</span><span>{{ wine.year }}</span>
        </div>
        <div class="info-sub">
          <span>평점</span><span>{{ wine.avg }}</span>
        </div>
        <div class="info-sub">
          <span>가격</span><span>{{ wine.price }}</span>
        </div>
      </div>
      <div class="info-thumb m-desc">
        <div class="info-thumb-part">
          <div class="info-thumb-type">
            <div class="circle"></div>
            <span>{{ wineType }}</span>
          </div>
          <p class="info-thumb-subtitle">{{ wineSubtitle }}</p>
        </div>
        <div class="info-thumb-part2">
          <div class="info-thumb-ave">
            <svg
              class="star"
              xmlns="http://www.w3.org/2000/svg"
              width="24"
              height="24"
              viewBox="0 0 24 24"
            >
              <path
                d="M12 .587l3.668 7.568 8.332 1.151-6.064 5.828 1.48 8.279-7.416-3.967-7.417 3.967 1.481-8.279-6.064-5.828 8.332-1.151z"
              />
            </svg>
            <span>{{ wine.avg }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import common from "@/assets/js/common.js";
import * as mypageApi from "@/api/mypageApi";
export default {
  name: "WineItem",
  filters: {
    currency(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  props: {
    wine: {
      type: Object,
      default: function () {
        return;
      },
    },
  },
  data() {
    return {
      wineListType: true,
      wineSubtitle: "",
      wineType: "",
      itemCountInLine: 0,
    };
  },
  computed: {
    ...mapState(["s3url"]),
  },
  watch: {},
  created() {
    // subtitle 생성, enmae에서 쉼표까지 텍스트 자르기
    let index = -1;
    index = this.wine.ename.indexOf(",");
    if (index !== -1) {
      this.wineSubtitle = this.wine.ename.substring(0, index);
    } else {
      this.wineSubtitle = this.wine.ename;
    }
    // 와인 type 영문화
    this.wineType = common.getEType(this.wine.type);
  },
  mounted() {},
  destroyed() {},
  methods: {
    /**
     * 해당 와인 상세페이지로 이동
     */
    clickedWine(wineId) {
      this.$router.push(`/detail/${wineId}`);
    },
    async onDelete(windId) {
      if (window.confirm("와인을 삭제 하시겠습니까?")) {
        const response = await mypageApi.deleteScrap(windId);
        if (response.status == 200) {
          this.$emit("deleteScrap");
          console.log("성공적으로 스크랩을 삭제했습니다.");
        }
      }
    },
  },
};
</script>

<style scoped>
/* 와인 리스트가 홀수일 경우 적용 */
.wineitem {
  display: flex;
  justify-content: center;
  width: 300px;
  border-radius: 150px;
  height: 500px;
  border: 1px solid var(--basic-color-key);
  transition: border 0.3s ease;
  position: relative;
}
.margin-top {
  margin-top: -250px;
}

.red {
  background-color: var(--basic-color-bg2);
}
.white {
  background-color: var(--basic-color-bg3);
}
.rose {
  background-color: var(--basic-color-bg4);
}

.wineitem-img {
  width: auto;
  height: 436px;
  padding: 32px;
  margin: 0px auto;
  filter: drop-shadow(5px 5px 20px rgba(0, 0, 0, 0.7));
  transition: opacity 0.5s ease;
}
.wineitem:hover .wineitem-img {
  opacity: 0.5;
}
/* hover시 표시되는 상세정보 */
.wineitem-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 300px;
  margin-top: -2px;
  height: 497px;
  border-radius: 150px;
  background-color: rgba(0, 0, 0, 0.3);
  opacity: 0;
  border: 4px solid var(--basic-color-key);
  transition: opacity 0.5s ease;
}
.wineitem:hover .wineitem-info {
  opacity: 1;
}
.info-title,
.info-sub {
  width: 90%;
  margin: 0.5rem;
  font-family: "Noto Sans KR", sans-serif;
  font-weight: 400;
  color: #fff;
}
.info-title p:nth-child(1) {
  font-size: 1.2rem;
}
.info-title hr {
  margin-top: 6px;
}
.info-sub {
  display: flex;
  padding-bottom: 5px;
  justify-content: space-between;
  border-bottom: 1px solid #fff;
}
/* 이미지와 함께 보여지는 요약 정보(이름, 평점, 가격) */
.info-thumb {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  position: absolute;
  top: 0px;
  right: -70px;
  width: 460px;
  height: 250px;
  padding: 1.5rem;
  cursor: pointer;
  transform: rotate(-90deg) translate(-500px, 100px);
  transform-origin: top left;
  transition: opacity 0.5s ease;
}
.info-thumb-part {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  color: white;
  text-shadow: 1px 1px 10px #000;
}
.info-thumb-part2 {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  color: white;
  text-shadow: 1px 1px 10px rgb(223, 181, 181);
}
/* top 영역 */
.info-thumb-type {
  display: flex;
  justify-content: flex-end;
  position: absolute;
  top: 100px;
  left: 350px;
  width: 110px;
  font-size: 1rem;
}
.info-thumb-type span {
  position: relative;
  top: -1px;
}
.circle {
  display: inline-block;
  width: 1rem;
  height: 1rem;
  margin-right: 5px;
  border-radius: 0.5rem;
  backdrop-filter: saturate(180%);
  border: 1px solid var(--basic-color-key);
}
.info-thumb-subtitle {
  margin-left: 60px;
  margin-top: 10px;
  font-size: 1.3rem;
  padding-right: 30px;
  word-break: break-all;
}
/* bottom 영역 */
.info-thumb-ave {
  display: flex;
  justify-content: flex-end;
  position: absolute;
  top: 180px;
  left: 350px;
  width: 110px;
}
.star {
  fill: #f9ba01;
  width: 1rem;
  height: 1rem;
}
.info-thumb-price {
  font-size: 1.1rem;
  margin-left: 60px;
  margin-right: 32px;
  margin-bottom: 40px;
  align-self: flex-end;
}

.wineitem:hover .info-thumb {
  opacity: 0;
}

.review-icon {
  width: 40px;
  height: 40px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  position: absolute;
  float: right;
  right: 10px;
  z-index: 10;
}
.review-icon-1 {
  width: 100%;
  height: 100%;
  background-image: url("../../assets/images/close_black.png");
  background-size: contain;
}
</style>
