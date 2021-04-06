<template>
  <div id="winelist" ref="winelist">
    <div
      v-for="(wine, index) in wines"
      :key="index + wine.ename + wine.marginTop"
      :style="{ marginTop: wine.marginTop }"
      class="wineitem fadeup"
      :class="{ 'margin-top': wineStyles[index], red: wine.type === '레드', white: wine.type === '화이트', rose: wine.type === '스파클링' }"
      @click="clickedWine(index)"
    >
      <img class="wineitem-img" v-if="wine.wineId != undefined && wine.wineId != 0" :src="`${s3url}${wine.wineId}.png`" alt="와인 이미지" />
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
            <div class="circle" />
            <span>{{ wineType[index] }}</span>
          </div>
          <p class="info-thumb-subtitle">{{ wineSubtitle[index] }}</p>
        </div>
        <div class="info-thumb-part">
          <p class="info-thumb-price">{{ wine.price | currency }} 원</p>
          <div class="info-thumb-ave">
            <svg class="star" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
              <path d="M12 .587l3.668 7.568 8.332 1.151-6.064 5.828 1.48 8.279-7.416-3.967-7.417 3.967 1.481-8.279-6.064-5.828 8.332-1.151z" />
            </svg>
            <span>{{ wine.avg }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import common from '@/assets/js/common.js';
import { mapState } from 'vuex';

export default {
  name: 'Winelist',
  filters: {
    currency(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
  },
  props: {
    wines: {
      type: Array,
      default: function() {
        return;
      },
    },
  },

  data() {
    return {
      wineListType: true,
      wineStyles: [],
      wineType: [],
      wineSubtitle: [],
      itemCountInLine: 0,
    };
  },
  computed: {
    ...mapState(['s3url']),
  },
  watch: {
    wines: {
      deep: true,
      handler() {
        const beforeSize = this.wineStyles.length;
        this.pushMarginTop(beforeSize);
      },
    },
  },
  created() {
    window.addEventListener('resize', this.setMarginTop);

    // subtitle 생성, enmae에서 쉼표까지 텍스트 자르기
    for (let i = 0; i < this.wines.length; i++) {
      let index = -1;
      index = this.wines[i].ename.indexOf(',');
      if (index !== -1) {
        this.wineSubtitle.push(this.wines[i].ename.substring(0, index));
      } else {
        this.wineSubtitle.push(this.wines[i].ename);
      }
    }
  },
  destroyed() {
    window.removeEventListener('resize', this.setMarginTop);
  },
  mounted() {
    console.log(this.s3url);
    this.setMarginTop();
  },
  methods: {
    setMarginTop() {
      // item 위치에 따른 margin-top 설정
      const winelistWidth = this.$refs.winelist.offsetWidth;
      this.itemCountInLine = Math.floor(winelistWidth / 300);
      this.wineStyles = [];
      5;
      for (let i = 0; i < this.wines.length; i++) {
        // 엇갈려 배치하기
        let nth = (i % this.itemCountInLine) + 1;
        if (nth % 2 === 0) {
          this.wineStyles.push(false);
        } else {
          this.wineStyles.push(true);
        }
        // 와인 type 영문화
        this.wineType.push(common.getEType(this.wines[i].type));
      }
      // wrap의 padding 동적 계산
      const blank = winelistWidth - this.itemCountInLine * 300;
      this.$refs.winelist.style.paddingLeft = `${blank / 2}px`;
    },
    pushMarginTop(index) {
      const winelistWidth = this.$refs.winelist.offsetWidth;
      this.itemCountInLine = Math.floor(winelistWidth / 300);
      for (let i = index; i < this.wines.length; i++) {
        let nth = (i % this.itemCountInLine) + 1;
        if (nth % 2 === 0) {
          this.wineStyles.push(false);
        } else {
          this.wineStyles.push(true);
        }
      }
    },
    clickedWine(index) {
      this.$router.push(`/detail/${this.wines[index].id}`);
    },
  },
};
</script>

<style scoped>
#winelist {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  margin: 300px auto 100px;
  width: 90%;
  margin-top: 300px;
}

/* 와인 리스트가 홀수일 경우 적용 */
.wineitem {
  display: flex;
  justify-content: center;
  width: 300px;
  border-radius: 150px;
  height: 500px;
  border: 1px solid var(--basic-color-key);
  transition: border 0.3s ease;
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
  height: 500px;
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
  width: 300px;
  height: 500px;
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
  font-family: 'Noto Sans KR', sans-serif;
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
  width: 500px;
  height: 295px;
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
  margin: 10px 60px;
  font-size: 1.3rem;
  padding-right: 30px;
  word-break: break-all;
}
/* bottom 영역 */
.info-thumb-ave {
  display: flex;
  justify-content: flex-end;
  position: absolute;
  top: 185px;
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
</style>
