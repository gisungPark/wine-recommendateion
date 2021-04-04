<template>
  <div id="wrap">
    <img src="../assets/images/wine.png" alt="오늘의 와인 이미지" id="main-wine-img" />
    <section id="main-section-0">
      <div class="marquee-container tilt">
        <div class="marquee">
          <span class="title wine-title b-desc-e">
            {{ title }}
          </span>
          <span class="title wine-title b-desc-e">
            {{ title }}
          </span>
        </div>
      </div>
      <span class="title wine-title-sub b-desc-e tilt" ref="titleSub">{{ subtitle }}</span>
    </section>
    <section id="main-section-1">
      <div class="main-message wine-top sticky-elem"></div>
      <div class="main-message wine-name sticky-elem">
        <h2 class="ename b-desc-e real-shadow-text">{{ todaysWine.ename }}</h2>
      </div>
      <div class="main-message wine-name-sub sticky-elem">
        <hr />
        <p class="kname">{{ todaysWine.kname }}</p>
      </div>
      <div class="main-message wine-desc sticky-elem">
        <div class="pin"></div>
        <p class="r-desc">
          <strong>{{ todaysWine.year }}</strong>
          {{ todaysWine.detail }}
        </p>
      </div>
      <div class="main-message wine-info sticky-elem">
        <div class="wine-info-item a">
          <span>지역</span><span>{{ todaysWine.area }}</span>
        </div>
        <hr />
        <div class="wine-info-item b">
          <span>품종</span><span>{{ todaysWine.grape.kname }}</span>
        </div>
        <hr />
        <div class="wine-info-item c">
          <span>종류</span><span>{{ todaysWine.type }}</span>
        </div>
        <hr />
        <div class="wine-info-item d">
          <span>알코올 도수</span><span>{{ todaysWine.alcohol }}</span>
        </div>
        <hr />
        <div class="wine-info-item e">
          <span>음용 온도</span><span>{{ todaysWine.temper }}</span>
        </div>
        <hr />
        <div class="wine-info-item f">
          <span>가격</span><span>{{ todaysWine.price | currency }} 원</span>
        </div>
        <hr />
      </div>
    </section>
    <section id="main-section-2">
      <div class="main-message wine-review sticky-elem">
        <div class="frame">
          <p class="b-desc">{{ todaysWine.avg.toFixed(1) }}</p>
          <Scrap class="scrap-btn" :scraped="true" />
        </div>
        <div class="star-rate" :style="{ width: starRate + 'vw' }"></div>
      </div>
      <div class="gradient-bg"></div>
    </section>
    <section id="main-section-3">
      <p class="topten-title b-title">Top 10</p>
      <swiper class="swiper" :options="swiperOption">
        <swiper-slide class="topten-item" v-for="(item, index) in top10" :key="item.wineId + index"><Topten :wine="item"/></swiper-slide>
        <div class="swiper-button-prev swiper-button-white" slot="button-prev"></div>
        <div class="swiper-button-next swiper-button-white" slot="button-next"></div>
        <div class="swiper-pagination" slot="pagination"></div>
      </swiper>
    </section>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import Scrap from '@/components/button/Scrap.vue';
import * as interaction from '@/assets/js/interaction.js';
import { Swiper, SwiperSlide } from 'vue-awesome-swiper';
import 'swiper/css/swiper.css';
import Topten from '@/components/main/Topten.vue';

import * as scroll from '@/assets/js/scroll.js';
export default {
  name: 'Main',
  components: {
    Scrap,
    Swiper,
    SwiperSlide,
    Topten,
  },
  filters: {
    currency(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
  },
  data() {
    return {
      show: false,
      title: `TODAY'S WINE`,
      subtitle: '',
      starRate: 10,
      //swiper
      swiperOption: {
        slidesPerView: 'auto',
        spaceBetween: 30,
        freeMode: true,
        pagination: {
          el: '.swiper-pagination',
          dynamicBullets: true,
        },
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev',
        },
      },
    };
  },
  created() {
    // vuex
    this.actGetTodaysWine().then(() => {
      // subtitle 생성, enmae에서 쉼표까지 텍스트 자르기
      let index = -1;
      index = this.todaysWine.ename.indexOf(',');
      if (index !== -1) {
        this.subtitle = this.todaysWine.ename.substring(0, index);
      } else {
        this.subtitle = this.todaysWine.ename;
      }

      // 별점에 따라 별 개수 동적 표시를 위해 width값 계산
      this.starRate = this.starRate * this.todaysWine.avg;

      interaction.main();
    });
    this.actGetTop10().then(() => {});
  },
  mounted() {
    // interaction section 제어
    // breath(this.$refs.titleSub);
    // scroll.smoothScroll(this.$refs.main);
  },
  computed: {
    ...mapState('main', ['todaysWine', 'top10']),
  },
  methods: {
    ...mapActions('main', ['actGetTodaysWine', 'actGetTop10']),
  },
};

// const breath = (target) => {
//   console.log(target);
//   target
//     .animate(
//       {
//         textShadow: '0 0 1px rgba(225, 170, 87, 0), 0 0 2px rgba(225, 170, 87, 0), 0 0 4px rgba(225, 170, 87, 0), 0 0 12px rgba(225, 170, 87, 0)',
//       },
//       150
//     )
//     .animate(
//       {
//         textShadow:
//           '0 0 1px rgba(225, 169, 87, 0.644), 0 0 2px rgba(225, 169, 87, 0.747), 0 0 4px rgba(225, 169, 87, 0.548), 0 0 12px rgba(225, 169, 87, 0.541)',
//       },
//       150,
//       function() {
//         var nextIn = Math.floor(Math.random() * 1000);
//         setTimeout('breath()', nextIn);
//       }
//     );0
// };
</script>

<style scoped>
#wrap {
  overflow-x: hidden;
}

.sticky-elem {
  display: none !important;
  position: fixed;
  top: 0;
  left: 0;
  /* transition: all 0.3s ease; */
}

#show-scene-0 #main-section-0 .sticky-elem,
#show-scene-1 #main-section-1 .sticky-elem,
#show-scene-2 #main-section-2 .sticky-elem,
#show-scene-3 #main-section-3 .sticky-elem {
  opacity: 1;
  display: block !important;
}

#main-section-0 {
  background-color: var(--basic-color-bg);
}
#main-section-1 {
  background-color: var(--basic-color-bg);
}
#main-section-2 {
  background-color: var(--basic-color-bg);
}
#main-section-3 {
  background-color: #000;
}

section {
  width: 100vw;
  height: 100vh;
}

/* TODO: section 1 영역 */
/* FIXME:  */
#main-wine-img {
  position: fixed;
  z-index: 5;
  filter: drop-shadow(15px 15px 30px rgb(0, 0, 0));
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: auto;
  height: 60vh;
  min-height: 550px;
}
.stop {
}
/* *************************************************************************** */
/* TODO: section 1 영역 */
/* FIXME:  */
/* **************************************************************************** */
#main-section-0 {
  overflow: hidden;
}
.marquee-container {
  position: absolute;
  top: 38vh;
  opacity: 0.8;
  white-space: nowrap;
}
.title {
  display: inline-block;
  color: var(--basic-color-key);
  white-space: nowrap;
}
.wine-title {
  /* transform: translate(0, -23vh); */
  font-size: 30vh;
  line-height: 30vh;
  padding-left: 200px;
  transform-origin: center center;
}
.wine-title-sub {
  position: relative;
  top: 55%;
  left: 50%;
  margin-left: 30px;
  font-size: 10vh;
  text-shadow: 0 0 1px rgba(225, 169, 87, 0.644), 0 0 2px rgba(225, 169, 87, 0.747), 0 0 4px rgba(225, 169, 87, 0.548), 0 0 12px rgba(225, 169, 87, 0.541);
  transform-origin: top left;
}
.tilt {
  transform: rotate(3deg);
}

/* TODO: section 2 영역 */
/* FIXME:  */
#main-section-1 {
  color: var(--basic-color-fill);
}
.main-message {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 1rem 0.5rem;
  /* background-color: #ffffffaa; */
  margin: 5px auto;
}
.wine-top {
  width: 84px;
  height: 84px;
  top: 10% !important;
  left: calc(50% - 42px);
  background-image: url(../assets/images/sprite_area.png);
  background-repeat: no-repeat;
  background-size: 1360px 864px;
  background-position: -824px -80px;
}
.wine-name,
.wine-name-sub {
  display: none;
  top: 25%;
  z-index: 6;
  width: 100%;
  font-size: 5vw;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.ename {
  color: var(--basic-color-key);
  text-align: center;
  font-size: 5vw;
}
.wine-name-sub {
  z-index: 4;
  padding-top: 6.5vw !important;
}
.kname {
  z-index: 3;
  font-size: 1.4vw;
  margin-left: 30%;
}
.wine-name-sub hr {
  z-index: 3;
  width: 40%;
  border: 1px solid var(--basic-color-fill);
  margin: 0 auto 10px;
}

.wine-desc {
  display: none;
  margin: 0 0;
  top: 50% !important;
  left: 5vw !important;
}
.wine-desc p {
  width: 20vw;
  font-size: 1rem;
  line-height: 1.2rem;
  word-break: break-all;
}
.wine-desc strong {
  float: left;
  font-size: 3rem;
  color: var(--basic-color-key);
  line-height: 3rem;
  margin: 0 0.2em 0.2rem 0;
}
.wine-desc .pin {
  position: relative;
  top: 5px;
  left: 20vw;
  width: 25avw;
  height: 1px;
  background-color: var(--basic-color-fill);
}

.wine-info {
  width: 100%;
  top: 50% !important;
  left: 50% !important;
  transform: translateX(-50%);
}
.wine-info-item {
  width: 40%;
  margin: 0.5rem auto 0.1rem;
  display: flex;
  white-space: nowrap;
  overflow: hidden;
  flex-direction: row;
  justify-content: space-between;
  transition: width 0.15s ease;
}
span {
  font-family: 'Noto Sans KR', sans-serif;
  font-size: 1.2rem;
}
.wine-info-item span:nth-child(1) {
  font-weight: 600;
}
.wine-info-item span:nth-child(2) {
  color: var(--basic-color-key);
}
.wine-info hr {
  width: 40%;
  margin: 0 auto;
  border-top: none;
  border-bottom: 1px dotted var(--basic-color-fill);
  transition: width 0.15s ease;
}

#main-section-2 {
  display: flex;
  align-items: center;
}
.wine-review {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  top: 50vh;
  transform: translateY(-50%);
  left: 30vw;
  /* width: 100%; */
}
.wine-review .frame {
  display: flex;
}
.wine-review .frame p {
  display: inline-block;
  font-size: 25vw;
  color: var(--basic-color-key);
  line-height: 25vw;
}
.star-rate {
  width: 50vw;
  height: 10vw;
  background-image: url(../assets/images/star.png);
  background-size: contain;
  background-repeat: repeat-x;
}
.scrap-btn {
  display: inline-block;
  align-self: flex-end;
  margin-left: 1rem;
  margin-bottom: 2rem;
  transform: scale(1.5);
}
.gradient-bg {
  width: 100%;
  height: 100%;
  background: linear-gradient(180deg, rgba(22, 22, 22, 1) 0%, rgba(0, 0, 0, 1) 100%);
}

@media (min-width: 1024px) {
  .wine-title-sub {
    top: 58%;
  }
}

/* TODO: swiper */
/* FIXME: 1. 웹 사이즈 크기에 따른 예기치 못한 뷰 형태 수정
          2. type과 와인 명을 하나의 wrap으로 감싼 뒤 처리하면 될 것 같음.*/
.topten-title {
  width: 100%;
  position: absolute;
  margin: 5% auto;
  color: var(--basic-color-key);
  text-align: center;
  font-size: 5vw;
}
.swiper {
  top: 20%;
  width: 100%;
  height: 80%;
}
.topten-item {
  width: 380px;
}
.swiper-pagination {
  position: absolute;
  left: 50%;
  top: 0;
  pointer-events: none;
  background-color: transparent;
  transform: translateX(-50%);
}
.swiper-button-prev,
.swiper-button-next {
  position: absolute;
  top: 0;
}
.swiper-button-prev {
  left: 42%;
}
.swiper-button-next {
  right: 42%;
}
.swiper-button-prev::after,
.swiper-button-next::after {
  transform: scale(0.5);
  margin-top: 30px;
}
</style>

<style>
.swiper-pagination-bullet {
  border-radius: 1rem;
  width: 2rem;
  height: 2rem;
  margin: 0 10rem;
  display: inline-block;
  background-color: #e4c18c;
  opacity: 0.5;
  transition: all 0.3s ease;
}
.swiper-pagination-bullet-active {
  background-color: var(--basic-color-key) !important;
  /* width: 2rem; */
  opacity: 1;
}
.swiper-pagination-bullet {
  margin: 0 1rem;
}
</style>
