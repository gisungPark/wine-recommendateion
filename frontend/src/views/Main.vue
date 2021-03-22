<template>
  <div id="wrap">
    <img src="../assets/images/wine.png" alt="오늘의 와인 이미지" id="main-wine-img" />
    <section id="main-section1">
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
      <transition :name="breath">
        <span class="title wine-title-sub b-desc-e tilt" ref="titleSub">RODA</span>
      </transition>
    </section>
    <section id="main-section2">
      <div class="main-message wine-top">와인종류?아니면 지역 국기</div>
      <div class="main-message wine-name">
        <h2 class="ename b-desc-e">{{ wine.ename }}</h2>
        <hr />
        <p class="kname">{{ wine.kname }}</p>
      </div>
      <div class="main-message wine-desc">
        <P class="r-desc">
          <strong>{{ wine.year }}</strong>
          {{ wine.detail }}
        </P>
      </div>
      <div class="main-message wine-info">
        <div class="wine-info-item">
          <span>지역</span><span>{{ wine.area }}</span>
        </div>
        <hr />
        <div class="wine-info-item">
          <span>품종</span><span>{{ wine.grape_id }}</span>
        </div>
        <hr />
        <div class="wine-info-item">
          <span>종류</span><span>{{ wine.type }}</span>
        </div>
        <hr />
        <div class="wine-info-item">
          <span>알코올 도수</span><span>{{ wine.alchol }}</span>
        </div>
        <hr />
        <div class="wine-info-item">
          <span>음용 온도</span><span>{{ wine.temper }}</span>
        </div>
        <hr />
        <div class="wine-info-item">
          <span>가격</span><span>{{ wine.price | currency }} 원</span>
        </div>
        <hr />
      </div>
      <div>맛 수치 값 차트?</div>
    </section>
    <section id="main-section3">
      <div class="main-message wine-review">
        <h1>{{ avg }}</h1>
      </div>
    </section>
    <section id="main-section4"></section>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'Main',
  filters: {
    currency(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
  },
  data() {
    return {
      title: `TODAY'S WINE`,
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
    };
  },
  mounted() {
    breath(this.$refs.titleSub);
  },
};

const breath = (target) => {
  console.log(target);
  target
    .animate(
      {
        textShadow: '0 0 1px rgba(225, 170, 87, 0), 0 0 2px rgba(225, 170, 87, 0), 0 0 4px rgba(225, 170, 87, 0), 0 0 12px rgba(225, 170, 87, 0)',
      },
      150
    )
    .animate(
      {
        textShadow:
          '0 0 1px rgba(225, 169, 87, 0.644), 0 0 2px rgba(225, 169, 87, 0.747), 0 0 4px rgba(225, 169, 87, 0.548), 0 0 12px rgba(225, 169, 87, 0.541)',
      },
      150,
      function() {
        var nextIn = Math.floor(Math.random() * 1000);
        setTimeout('breath()', nextIn);
      }
    );
};
</script>

<style scoped>
#main-section1 {
  background-color: var(--basic-color-bg);
}
#main-section2 {
  background-color: var(--basic-color-bg2);
}
#main-section3 {
  background-color: var(--basic-color-bg3);
}
#main-section4 {
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
  filter: drop-shadow(5px 5px 12px rgb(0, 0, 0));
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: auto;
  height: auto;
  max-height: 80vh;
}
.stop {
}

/* TODO: section 1 영역 */
/* FIXME:  */
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
}
.wine-title-sub {
  position: relative;
  top: 55%;
  left: 50%;
  margin-left: 30px;
  font-size: 10vh;
  text-shadow: 0 0 1px rgba(225, 169, 87, 0.644), 0 0 2px rgba(225, 169, 87, 0.747), 0 0 4px rgba(225, 169, 87, 0.548), 0 0 12px rgba(225, 169, 87, 0.541);
}
.tilt {
  transform: rotate(3deg);
}

/* TODO: section 2 영역 */
/* FIXME:  */
#main-section2 {
  color: var(--basic-color-fill);
}
.main-message {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 1rem 0.5rem;
  background-color: #ffffffaa;
  margin: 5px auto;
}
.wine-name {
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
.kname {
  font-size: 1.4vw;
}
.wine-name hr {
  width: 1rem;
  margin-bottom: 10px;
}

.wine-desc {
  width: 20%;
  margin: 0 0;
}
.wine-desc p {
  font-size: 1rem;
  line-height: 1.2rem;
}
.wine-desc strong {
  float: left;
  font-size: 3rem;
  color: var(--basic-color-key);
  line-height: 3rem;
  margin: 0 0.2em 0.2rem 0;
}

.wine-info {
  width: 70%;
}
.wine-info-item {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
span {
  font-family: 'Noto Sans KR', sans-serif;
}
.wine-info-item:nth-child(1) {
  font-weight: 400;
}
.wine-info hr {
  width: 100%;
}
</style>
