<template>
  <div id="wine-details" :style="{ color: fontColor }">
    <img :src="wine.img" :alt="`${wine.kname} 와인의 이미지`" id="detail-wine-img" />
    <section id="detail-section-0" :style="{ backgroundColor: backgroundColor }">
      <!-- 왼쪽 -->
      <div class="sec-0-part">
        <p class="ename">
          <span>{{ wine.year }}</span>
          {{ wine.ename }}
        </p>
        <div class="bar" :style="{ backgroundColor: fontColor }"></div>
        <p class="kname">{{ wine.kname }}</p>
        <div class="sub-info">
          <div class="icon"></div>
          <div class="data">
            <p class="key"></p>
            <p class="value"></p>
          </div>
        </div>
      </div>
      <!-- 가운데 -->
      <div class="wine-blank">
        <div class="info-thumb-type">
          <div class="circle"></div>
          <span>{{ type }}</span>
        </div>
      </div>
      <!-- 오른쪽 -->
      <div class="sec-0-part">
        <p class="price">₩ {{ wine.price | currency }}</p>
        <div class="bar" :style="{ backgroundColor: fontColor }"></div>
        <div class="avg">
          <span>{{ wine.avg }}</span>
          <div class="star-rate" :style="{ width: starRate + 'rem' }"></div>
        </div>
        <div class="bar" :style="{ backgroundColor: fontColor }"></div>
        <Scrap class="scrap-btn" :scraped="true" />
      </div>
    </section>
    <section id="detail-section-1" :style="{ backgroundColor: backgroundColor }"></section>
    <section id="detail-section-2" :style="{ backgroundColor: backgroundColor }"></section>
    <section id="detail-section-3" :style="{ backgroundColor: backgroundColor }"></section>
    <section id="detail-section-4" :style="{ backgroundColor: backgroundColor }"></section>
  </div>
</template>

<script>
import common from '@/assets/js/common.js';
import Scrap from '@/components/button/Scrap.vue';
export default {
  name: 'WineDetails',
  filters: {
    currency(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
  },
  components: {
    Scrap,
  },
  data() {
    return {
      id: '',
      backgroundColor: '',
      fontColor: '',
      type: 'none',
      starRate: 3,
      scrap: true,
      wine: {
        wine_id: `1`,
        img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
        ename: `Morande, Pionero Rose`,
        kname: `모란데, 피오네로 로제`,
        type: `레드`,
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
        avg: '4.5',
      },
    };
  },
  created() {
    // url에서 와인 아이디 추출
    this.id = this.$route.params.id;
    //TODO: 와인 데이터 요청

    // 한글 와인 타입 영문화
    this.type = common.getEType(this.wine.type);
    // 별점에 따라 별 개수 동적 표시를 위해 width값 계산
    this.starRate = this.starRate * this.wine.avg;
  },
  mounted() {
    // 배경색 동적 할당
    this.backgroundColor = common.getTypeColor(this.wine.type);
    // 텍스트 색 설정
    switch (this.wine.type) {
      case '레드':
        this.fontColor = '#EDDB4B';
        break;
      case '화이트':
      case '스파클링':
        this.fontColor = '#9A040C';
        break;
      default:
        console.log('wine type error!');
        this.fontColor = '#fff';
        break;
    }

    //TODO: 마운트 되었을 때 스크롤 살짝 내려서 애니메이션 동작하도록 설정
    //TODO: 그냥 등장 시키자....
  },

  //
};
</script>

<style scoped>
section {
  display: flex;
  width: 100vw;
  height: 100vh;
  border-bottom: 5px solid #cdc169;
  background-color: rgba(255, 255, 255, 0.427);
  font-family: 'Noto Sans KR', sans-serif;
  font-size: 1rem;
}
#detail-wine-img {
  position: fixed;
  z-index: 5;
  filter: drop-shadow(15px 15px 30px rgb(0, 0, 0));
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: auto;
  height: 60%;
  min-height: 550px;
}
.wine-blank {
  width: 30vh;
  height: 60vh;
  min-width: 300px;
  min-height: 550px;
}
.bar {
  width: 100%;
  height: 1px;
  margin: 1rem 0;
}

/* section 1 */
#detail-section-0 {
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.sec-0-part {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 30%;
  max-width: 960px;
}
.sec-0-part span {
  display: block;
  font-size: 2rem;
}
.sec-0-part .ename {
  font-size: 3rem;
  font-weight: 700;
  margin-right: auto;
}
.sec-0-part .kname {
  font-size: 1rem;
  font-weight: 400;
  margin-left: auto;
}

.info-thumb-type {
  display: flex;
  justify-content: flex-end;
  position: relative;
  margin-top: 124px;
  margin-left: 100px;
  z-index: 100;
  width: 110px;
  font-size: 1rem;
  transform: rotate(-90deg);
  transform-origin: top left;
}
.info-thumb-type span {
  position: relative;
  top: -1px;
  font-weight: 400;
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

.sec-0-part .price,
.sec-0-part .avg {
  display: flex;
  font-size: 3rem;
  font-weight: 700;
  margin-left: auto;
}
.sec-0-part .avg span {
  font-size: 3rem;
  font-weight: 700;
  margin-left: auto;
  margin-right: 1rem;
}
.star-rate {
  width: 3rem;
  height: 3rem;
  background-image: url(../assets/images/star_yellow.png);
  background-size: contain;
  background-repeat: repeat-x;
}
.scrap-btn {
  margin: 30px auto 0 20px;
  transform: scale(1.4);
}
/* section 2 */
/* section 3 */
/* section 4 */
</style>
