sudo find . -type f -name .DS_Store -print -delete<template>
  <div id="recommendation">
    <button class="temp" @click="temp">add test</button>
    <Sidebar class="sidebar" :sidebar="sidebar" @clickedBtn="changeContent" />
    <section class="content">
      <div class="info">
        <p>
          <strong>{{ notice.title }}</strong>
          {{ notice.text }}
        </p>
      </div>

      <transition-group name="slideup" mode="out-in" class="winelist-wrap">
        <Winelist class="winelist" key="0" :wines="wines0" v-if="contentState === 0" />
        <Winelist class="winelist" key="1" :wines="wines1" v-if="contentState === 1" />
        <Winelist class="winelist" key="2" :wines="wines2" v-if="contentState === 2" />
        <Winelist class="winelist" key="3" :wines="wines3" v-if="contentState === 3" />
      </transition-group>
    </section>
    <infinite-loading @infinite="infiniteHandler" spinner="waveDots" ref="infiniteLoading" class="infinite">
      <div slot="no-more">목록의 끝입니다.</div>
      <div slot="no-results">요청 결과가 없습니다.</div>
      <div slot="error" slot-scope="{ trigger }">문제가 발생했습니다. 재시도 하려면 <a href="javascript:;" @click="trigger">여기</a>를 누르십시오.</div>
    </infinite-loading>
  </div>
</template>
<script src="/path/to/vue-infinite-loading/dist/vue-infinite-loading.js"></script>
<script>
import { mapState, mapMutations, mapActions } from 'vuex';
import Sidebar from '@/components/articles/Sidebar.vue';
import Winelist from '@/components/articles/Winelist.vue';
import InfiniteLoading from 'vue-infinite-loading';

import axios from 'axios';

const api = '//hn.algolia.com/api/v1/search_by_date?tags=story';

export default {
  name: 'Recommendation',
  components: {
    Sidebar,
    Winelist,
    InfiniteLoading,
  },
  data: () => ({
    contentState: 0,
    sidebar: {
      title: 'Recommendation',
      names: ['Preferences', 'Ratings', 'Pairings', 'Gifts'],
      descBtn: [
        '향과 맛 기반의 선호도 추천입니다.',
        '리뷰 데이터 기반의 평점 추천입니다.',
        '와인의 종류와 맛에 따른 음식 페어링 입니다',
        '호불호가 적은, 만족도 높은 와인을 찾아드립니다.',
      ],
    },
    notice: {
      text: '',
      title: '',
      ratingBased: {
        title: '님께서 작성하신 리뷰 평점을 분석하여 추천해드리는 와인 리스트입니다.',
        writeTrue: `작성하신 리뷰 중 평점이 높은 와인 Top3개를 골라,`,
        writeTrue2: `님과 비슷한 입맛을 가진 가장 유사한 회원분이 높게 평가한 와인리스트 5개를 추천해드립니다.`,
        writeFalse: '리뷰를 작성한 뒤 해당 서비스를 이용해주세요.',
        batchFalse: '데이터 분석 중...(최대 1시간)',
      },
    },
    // 와인 정보
    wines0: [],
    page0: 1,
    wines1: [],
    page1: 1,
    wines2: [],
    page2: 1,
    wines3: [],
    page3: 1,

    // TODO: 품종 필터
  }),
  created() {
    // vuex
    this.actGetCheckPreperence();
  },
  mounted() {
    // scroll.linear();
  },
  computed: {
    ...mapState('userInfo', ['userInfo']),
    ...mapState('nav', ['navActive']),
    ...mapState('recommendation', ['ratingBasedRecom', 'checkPreference', 'preferenceBasedRecom']),
  },
  watch: {
    contentState: function() {
      console.log(this.$refs.infiniteLoading);
      this.$refs.infiniteLoading.stateChanger.reset();
    },
  },
  methods: {
    ...mapActions('recommendation', ['actGetRatingBasedRecom', 'actGetCheckPreperence', 'actGetPreferenceBasedRecom']),
    changeContent(index) {
      this.contentState = index;
      window.scrollTo({ top: 0, behavior: 'smooth' });
    },

    // infiniteHandler, props 전달
    infiniteHandler($state) {
      switch (this.contentState) {
        // 선호도 기반
        case 0:
          console.log('선호도 기반 추천');
          console.log(this.checkPreference);
          // if (!this.checkPreference) {
          //   alert(`해당 서비스는 선호도 설정이 필요합니다.\n'내정보' 페이지에서 선호도를 설정해주세요.`);
          //   $state.complete();
          // }
          this.actGetPreferenceBasedRecom(this.page0).then((result) => {
            if (result) {
              if (this.preferenceBasedRecom.wineList.length === 0) {
                // 더 이상 불러올 목록이 없는 경우
                $state.complete();
              }
              console.log(this.preferenceBasedRecom);
              this.wines0.push(...this.preferenceBasedRecom.wineList);
              this.page0 += 1;
              setTimeout(() => {
                $state.loaded();
              }, 1600);
            } else {
              $state.error(); //통신에러
              return;
            }
          });
          break;
        // 평점 기반
        case 1:
          console.log('평점 기반 추천은 5개의 와인만을 추천해드립니다.');
          this.actGetRatingBasedRecom().then((result) => {
            if (result) {
              if (this.ratingBasedRecom.wineDtoList.length) {
                this.infoTextMaker();
                this.wines1 = this.ratingBasedRecom.wineDtoList;
              }
            } else {
              $state.error(); //통신에러
              return;
            }
            $state.loaded(); //정상적 종료
            $state.complete();
          });
          break;
        // 음식 페어링
        case 2:
          console.log('음식 페어링 추천');
          break;
        // 선물용
        case 3:
          console.log('선물용 추천');
          break;
      }
    },

    infoTextMaker() {
      // 안내 문구 생성
      switch (this.contentState) {
        // 선호도기반
        case 0:
          break;
        // 평점 기반
        case 1:
          this.notice.title = `${this.userInfo.nickname}${this.notice.ratingBased.title}`;
          this.notice.text = '';
          // notice 문구 완성
          if (this.ratingBasedRecom.writeReview) {
            this.notice.text = `${this.notice.ratingBased.writeTrue} ${this.userInfo.nickname}${this.notice.ratingBased.writeTrue2}`;
          } else if (!this.ratingBasedRecom.writeReview) {
            this.notice.text = this.notice.ratingBased.writeFalse;
          } else if (this.ratingBasedRecom.batchData) {
            this.notice.text = this.notice.ratingBased.batchFalse;
          }
          break;
        // 음식 페어링
        case 2:
          break;
        // 선물용
        case 3:
          break;
      }
    },

    temp() {
      this.wines0.push({
        type: '레드',
        ename: '`Morande, Pionero Rose`',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
        img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
      });
    },
  },
};
</script>

<style scoped>
/* 트랜지션 */
.slideup-leave-active {
  transition: transform 0.5s, opacity 0.5s cubic-bezier(0, 1, 0.65, 1);
}
.slideup-enter-active {
  transition: transform 0.5s cubic-bezier(0, 1, 0.65, 1) 0.5s, opacity 0.5s cubic-bezier(0, 1, 0.65, 1) 0.5s;
}
.slideup-enter,
.slideup-leave-to {
  transform: translate(0, 100%);
  opacity: 0;
}
.slideup-enter-to,
.slideup-leave {
  transform: translate(0, 0);
  opacity: 1;
}

.temp {
  position: fixed;
  left: 200px;
  top: 200px;
  width: 100px;
  height: 100px;
  background-color: #fff;
  z-index: 100;
}

#recommendation {
  display: flex;
  flex-direction: row;
}
.sidebar {
  z-index: 5;
  position: fixed;
}
.content {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  z-index: 0;
  padding-left: 172px;
  padding-top: 100px;
}
.info {
  padding: 1rem;
  width: 90%;
  text-align: left;
  color: #fff;
  border: 1px solid var(--basic-color-key);
  border-radius: 2rem;
  background-color: rgba(0, 0, 0, 0.233);
}
.info p {
  font-size: 1.2rem;
  line-height: 1.5rem;
}
.info strong {
  display: block;
  font-size: 1.6rem;
  line-height: 2rem;
}
.winelist-wrap {
  width: 100%;
}
.infinite {
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  width: auto;
}
.infinite div {
  font-size: 1.2rem;
  color: #fff;
}
.infinite a {
  color: var(--basic-color-key);
  font-weight: 900;
}
</style>
