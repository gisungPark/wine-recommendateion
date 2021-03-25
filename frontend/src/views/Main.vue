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
        <h2 class="ename b-desc-e real-shadow-text">{{ wine.ename }}</h2>
      </div>
      <div class="main-message wine-name-sub sticky-elem">
        <hr />
        <p class="kname">{{ wine.kname }}</p>
      </div>
      <div class="main-message wine-desc sticky-elem">
        <div class="pin"></div>
        <p class="r-desc">
          <strong>{{ wine.year }}</strong>
          {{ wine.detail }}
        </p>
      </div>
      <div class="main-message wine-info sticky-elem">
        <div class="wine-info-item a">
          <span>지역</span><span>{{ wine.area }}</span>
        </div>
        <hr />
        <div class="wine-info-item b">
          <span>품종</span><span>{{ wine.grape_id }}</span>
        </div>
        <hr />
        <div class="wine-info-item c">
          <span>종류</span><span>{{ wine.type }}</span>
        </div>
        <hr />
        <div class="wine-info-item d">
          <span>알코올 도수</span><span>{{ wine.alchol }}</span>
        </div>
        <hr />
        <div class="wine-info-item e">
          <span>음용 온도</span><span>{{ wine.temper }}</span>
        </div>
        <hr />
        <div class="wine-info-item f">
          <span>가격</span><span>{{ wine.price | currency }} 원</span>
        </div>
        <hr />
      </div>
    </section>
    <section id="main-section-2">
      <div class="main-message wine-review sticky-elem">
        <div class="frame">
          <p class="b-desc">{{ avg }}</p>
          <Scrap class="scrap-btn" :scraped="true" />
        </div>
        <div class="star-rate" :style="{ width: starRate + 'vw' }"></div>
      </div>
    </section>
    <section id="main-section-3">
      <swiper class="swiper" :options="swiperOption">
        <swiper-slide>Slide 1</swiper-slide>
        <swiper-slide>Slide 2</swiper-slide>
        <swiper-slide>Slide 3</swiper-slide>
        <swiper-slide>Slide 4</swiper-slide>
        <swiper-slide>Slide 5</swiper-slide>
        <swiper-slide>Slide 6</swiper-slide>
        <swiper-slide>Slide 7</swiper-slide>
        <swiper-slide>Slide 8</swiper-slide>
        <swiper-slide>Slide 9</swiper-slide>
        <swiper-slide>Slide 10</swiper-slide>
        <div class="swiper-pagination" slot="pagination"></div>
      </swiper>
    </section>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import Scrap from '@/components/button/Scrap.vue';
import * as interaction from '@/assets/js/interaction.js';
import { Swiper, SwiperSlide, directive } from 'vue-awesome-swiper';
import 'swiper/swiper-bundle.css';

export default {
  name: 'Main',
  components: {
    Scrap,
    Swiper,
    SwiperSlide,
  },
  filters: {
    currency(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
  },
  data() {
    return {
      title: `TODAY'S WINE`,
      subtitle: '',
      wine: {
        wine_id: `0`,
        img: ``,
        ename: `Morande, Pionero Rose`,
        kname: `모란데, 피오네로 로제`,
        type: `로제`,
        area: `칠레`,
        alchol: `13~14도`,
        grape_id: `카베르네 소비뇽`,
        temper: `11~14℃`,
        price: `5100000`,
        year: `2013`,
        detail: `딸기, 블랙 베리의 향이 신선하게 다가오며 달콤한 맛이 적절한 산도와 상쾌한 과일 느낌과 함께 잘 어우러지는 와인이다.`,
        sweet: 2,
        acidity: 3,
        body: 3,
        tannin: 1,
      },
      avg: 4.5,
      starRate: 10,
      //swiper
      swiperOption: {
        slidesPerView: 3,
        spaceBetween: 30,
        freeMode: true,
        pagination: {
          el: '.swiper-pagination',
          clickable: true,
        },
      },
    };
  },
  created() {
    // 별점에 따라 별 개수 동적 표시를 위해 width값 계산
    this.starRate = this.starRate * this.avg;
    // subtitle 생성, enmae에서 쉼표까지 텍스트 자르기
    let index = null;
    index = this.wine.ename.indexOf(',');
    this.subtitle = this.wine.ename.substring(0, index);
  },
  mounted() {
    // interaction section 제어
    interaction.main();
    // breath(this.$refs.titleSub);
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
  background-color: var(--basic-color-bg4);
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
  min-height: 650px;
}
.stop {
}

/* TODO: section 1 영역 */
/* FIXME:  */
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
  border: 1px dotted var(--basic-color-fill);
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
  background-image: url(../assets/images/start.png);
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

@media (min-width: 1024px) {
  .wine-title-sub {
    top: 58%;
  }
}

/* TODO: swiper */
/* FIXME:  */
.swiper {
  top: 10%;
  width: 100%;
  height: 80%;
}
</style>
