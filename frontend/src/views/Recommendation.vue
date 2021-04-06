<template>
  <div id="recommendation">
    <Sidebar class="sidebar" data-app :sidebar="sidebar" @clickedBtn="changeContent" />
    <section class="content">
      <div class="info">
        <p>
          <strong>{{ notice.title }}</strong>
          {{ notice.text }}
        </p>
        <div id="taste-slider" v-if="contentState == 0">
          <button class="slider-toggle-btn" @click="clickedSliderToggleBtn">filter</button>
          <div class="slider-group" :class="{ 'slider-group-active': filterToggleState }">
            <v-range-slider
              label="Sweet"
              :tick-labels="step"
              :value="sweet"
              v-model="sweet"
              min="1"
              max="5"
              ticks="always"
              tick-size="4"
              color="#821a33"
              track-fill-color="#821a33"
            >
            </v-range-slider>
            <v-range-slider
              label="Acidity"
              :tick-labels="step"
              :value="acidity"
              v-model="acidity"
              min="1"
              max="5"
              ticks="always"
              tick-size="4"
              color="#821a33"
              track-fill-color="#821a33"
            >
            </v-range-slider>
            <v-range-slider
              label="Tannin"
              :tick-labels="step"
              :value="tannin"
              v-model="tannin"
              min="1"
              max="5"
              ticks="always"
              tick-size="4"
              color="#821a33"
              track-fill-color="#821a33"
            >
            </v-range-slider>
            <v-range-slider
              label="Body"
              :tick-labels="step"
              :value="body"
              v-model="body"
              min="1"
              max="5"
              ticks="always"
              tick-size="4"
              color="#821a33"
              track-fill-color="#821a33"
            >
            </v-range-slider>
            <div class="group-btn">
              <button class="reset" @click="clickedFilterReset">초기화</button>
              <button class="submit" @click="clickedFilterSubmit">적용</button>
            </div>
          </div>
        </div>
      </div>

      <transition-group name="slideup" mode="out-in" class="winelist-wrap">
        <Winelist class="winelist" key="0" :wines="wines0" v-if="contentState === 0" />
        <Winelist class="winelist" key="1" :wines="wines1" v-if="contentState === 1" />
        <Winelist class="winelist" key="2" :wines="wines2" v-if="contentState === 2" />
        <Winelist class="winelist" key="3" :wines="wines3" v-if="contentState === 3" />
      </transition-group>
      <infinite-loading @infinite="infiniteHandler" force-use-infinite-wrapper="true" spinner="waveDots" ref="infiniteLoading" class="infinite">
        <div slot="no-more">목록의 끝입니다.</div>
        <div slot="no-results">요청 결과가 없습니다.</div>
        <div slot="error" slot-scope="{ trigger }">문제가 발생했습니다. 재시도 하려면 <a href="javascript:;" @click="trigger">여기</a>를 누르십시오.</div>
      </infinite-loading>
    </section>
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
      preferenceBased: {
        title: '님의 선호도를 바탕으로 와인을 추천해드립니다.\n아래의 맛필터를 사용하여 원하는 와인을 찾아보세요!',
        favorite: '님이 선호하시는 향은 ',
        favorite2: '님이 선호하지 않는 향은 선택되지 않았습니다.\n선호도 정보를 선택하면 더 자세한 추천을 받을 수 있습니다. 선호도 정보를 수정해보세요! >',
        both: '님이 선호하시는 향은 ',
        both2: '선호하지 않는 향은 ',
      },
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
    sweet: 0.0,
    page0: 1,
    wines1: [],
    page1: 1,
    wines2: [],
    page2: 1,
    wines3: [],
    page3: 1,
    wine4: [],
    // TODO: 품종 필터
    filterToggleState: false,
    filterActive: false,
    sweet: [1, 5],
    acidity: [1, 5],
    tannin: [1, 5],
    body: [1, 5],
    step: ['1', '2', '3', '4', '5'],
  }),
  created() {
    // vuex init, 선호도 설정 여부 확인
    console.log(this.checkPreference);
    this.actGetCheckPreperence();
  },
  mounted() {
    //선호도 기반 리스트 1회 요청
    this.infoTextMaker();
  },
  computed: {
    ...mapState('userInfo', ['userInfo']),
    ...mapState('nav', ['navActive']),
    ...mapState('recommendation', ['checkPreference', 'preferenceBasedRecom', 'ratingBasedRecom']),
  },
  watch: {
    contentState: function() {
      this.$refs.infiniteLoading.stateChanger.reset();
    },
    checkPreference: function() {
      console.log(this.checkPreference);
      if (this.checkPreference === null) return;
      if (!this.checkPreference) {
        alert(`해당 서비스는 선호도 설정이 필요합니다.\n'내정보' 페이지에서 선호도를 설정해주세요.`);
        $state.complete();
        return;
      }
      this.infoTextMaker();
      this.getWine0(this.$refs.infiniteLoading.stateChanger);
    },
  },
  methods: {
    ...mapActions('recommendation', ['actGetCheckPreperence', 'actGetPreferenceBasedRecom', 'actGetPreferenceBasedRecomFilter', 'actGetRatingBasedRecom']),

    changeContent(index) {
      this.contentState = index;
      window.scrollTo({ top: 0, behavior: 'smooth' });

      this.infoTextMaker();

      switch (index) {
        case 1:
          this.getWine1(this.$refs.infiniteLoading.stateChanger);
          break;
        default:
          break;
      }
    },

    // infiniteHandler, props 전달
    infiniteHandler($state) {
      switch (this.contentState) {
        // 선호도 기반
        case 0:
          console.log('선호도 기반 추천');
          // console.log(this.checkPreference);
          this.getWine0($state);
          break;
        // 음식 페어링
        case 2:
          console.log('음식 페어링 추천');
          break;
        // 선물용
        case 3:
          console.log('선물용 추천');
          break;
        default:
          break;
      }
    },

    infoTextMaker() {
      // 안내 문구 생성
      switch (this.contentState) {
        // 선호도기반
        case 0:
          this.notice.title = `${this.userInfo.nickname}${this.notice.preferenceBased.title}`;
          if (this.checkPreference && this.preferenceBasedRecom.dislikeFlavorList.length >= 1) {
            // 좋아하는 맛, 싫어하는 맛 모두 설정
            this.notice.text = `${this.userInfo.nickname}${this.notice.preferenceBased.both}`;
            this.preferenceBasedRecom.likeFlavorList.forEach((element) => {
              this.notice.text += `${element.name}, `;
            });
            this.notice.text = this.notice.text.slice(0, -2);
            this.notice.text += `입니다.\n${this.notice.preferenceBased.both2}`;
            this.preferenceBasedRecom.dislikeFlavorList.forEach((element) => {
              this.notice.text += `${element.name}, `;
            });
            this.notice.text = this.notice.text.slice(0, -2);
            this.notice.text += '입니다.';
            console.log(this.notice.text);
          }
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

    getWine0($state) {
      if (this.filterActive) {
        this.actGetPreferenceBasedRecomFilter({
          page: this.page0,
          filter: {
            endAcidity: this.acidity[1],
            endBody: this.body[1],
            endSweet: this.sweet[1],
            endTannin: this.tannin[1],
            startAcidity: this.acidity[0],
            startBody: this.body[0],
            startSweet: this.sweet[0],
            startTannin: this.tannin[0],
          },
        }).then((result) => {
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
            }, 1000);
          } else {
            $state.error(); //통신에러
            return;
          }
        });
      } else {
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
            }, 1000);
          } else {
            $state.error(); //통신에러
            return;
          }
        });
      }
    },
    getWine1($state) {
      this.actGetRatingBasedRecom().then((result) => {
        if (result) {
          if (this.ratingBasedRecom.wineDtoList.length) {
            this.wines1 = this.ratingBasedRecom.wineDtoList;
            this.page1 += 1;
          }
          0;
        } else {
          $state.error(); //통신에러
          return;
        }
        $state.loaded(); //정상적 종료
        $state.complete();
      });
    },

    clickedFilterReset() {
      this.filterToggleState = false;
      this.filterActive = false;
      this.sweet = [1, 5];
      this.acidity = [1, 5];
      this.tannin = [1, 5];
      this.body = [1, 5];
      this.$toast.open({
        message: '필터가 초기화 되었습니다.',
        type: 'info',
      });
      this.page0 = 1;
      this.wines0 = [];
      this.$refs.infiniteLoading.stateChanger.reset();
    },
    clickedFilterSubmit() {
      this.filterToggleState = false;
      this.filterActive = true;
      this.$toast.open({
        message: '필터가 설정되었습니다.',
        type: 'success',
      });
      this.page0 = 1;
      this.wines0 = [];
      this.$refs.infiniteLoading.stateChanger.reset();
    },
    clickedSliderToggleBtn() {
      this.filterToggleState = !this.filterToggleState;
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
  height: auto;
  overflow: auto;
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
  width: 100%;
  height: 100px;
}
.infinite div {
  font-size: 1.2rem;
  color: #fff;
}
.infinite a {
  color: var(--basic-color-key);
  font-weight: 900;
}

/* 맛 필터 */
#taste-slider {
  position: relative;
}
.slider-toggle-btn {
  width: 80px;
  height: 80px;
  position: relative;
  border: 1px solid var(--basic-color-key);
  border-radius: 50%;
  color: var(--basic-color-key);
  font-size: 1.2rem;
  transition: all 0.5s ease;
  margin-top: 2rem;
}
.slider-toggle-btn:hover {
  background-color: #e1aa5786;
}
.slider-group {
  top: -180px;
  left: -260px;
  display: flex;
  flex-direction: column;
  position: absolute;
  z-index: 3;
  padding: 3rem;
  width: 600px;
  height: 500px;
  border-radius: 2rem;
  background-color: rgba(255, 255, 255, 0.6);
  backdrop-filter: saturate(180%) blur(30px);
  transition: all 0.3s ease;
  transform: scale(0);
  visibility: hidden;
  opacity: 0;
}
.slider-group-active {
  top: 2rem;
  left: 6rem;
  visibility: visible;
  opacity: 1;
  transform: scale(1);
}
.group-btn {
  display: flex;
  flex-direction: row;
  justify-content: center;
  width: 100%;
}
.group-btn button {
  width: 150px;
  height: 50px;
  margin: 0 1rem;
  border-radius: 25px;
  background-color: rgba(255, 255, 255, 0.5);
  border: solid 1px var(--basic-color-key);
  color: black;
  font-size: 1.2rem;
}
</style>

<style>
#taste-slider .v-label {
  width: 60px;
}
#taste-slider .v-slider__tick-label {
  margin: 5px 0px 5px -2px;
}
#taste-slider .v-slider-value {
  /* visibility: hidden;
  width: 0;
  height: 0;
  font-size: 0px; */
}
</style>
