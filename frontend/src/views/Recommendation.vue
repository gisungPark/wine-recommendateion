<template>
  <div id="recommendation">
    <Sidebar class="sidebar" data-app :sidebar="sidebar" @clickedBtn="changeContent" />
    <section class="content">
      <div class="info">
        <p>
          <strong>{{ notice.title }}</strong>
          {{ notice.text }}
        </p>

        <div class="button-group">
          <button v-if="contentState == 0" class="slider-toggle-btn" @click="clickedSliderToggleBtn">filter</button>
          <button v-if="preferenceBasedRecom.dislikeFlavorList.length == 0" class="go-mypage-btn" @click="clickedGoMyapge">내정보 페이지 이동 ></button>
        </div>
        <div id="taste-slider" v-if="contentState == 0">
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
            <div class="slider-btn-group">
              <button class="reset" @click="clickedFilterReset">초기화</button>
              <button class="submit" @click="clickedFilterSubmit">적용</button>
            </div>
          </div>
        </div>
        <div v-if="contentState == 2" id="food-svg">
          <FoodSvgGroup @clickedSvg="setFoodId" />
          <p><span>"</span>{{ pairingBasedRecom.mention }}</p>
        </div>

        <div class="gifts-btn-group" v-if="contentState == 3">
          <button class="gifts-filter-toggle-btn" @click="clickedGiftsFilterToggleBtn">filter</button>
          <div class="gifts-slider-group" :class="{ 'gifts-slider-group-active': giftsFilterState }">
            <v-range-slider label="Price" :value="price" v-model="price" min="0" max="999999" step="10000" color="#821a33" track-fill-color="#821a33">
            </v-range-slider>
            <p class="tick-label">{{ price[0] | currency }}원 ~ {{ price[1] | currency }}원</p>
            <div class="slider-btn-group">
              <button class="reset" @click="clickedFilterReset2">초기화</button>
              <button class="submit" @click="clickedFilterSubmit2">적용</button>
            </div>
          </div>

          <button class="gifts-info-toggle-btn" @click="clickedGiftsInfoToggleBtn">?</button>
          <div class="gifts-info-list" :class="{ 'gifts-info-list-active': giftsInfoState }">
            <p>{{ notice.giftsBased.sub1 }}</p>
            <p>{{ notice.giftsBased.sub2 }}</p>
            <p>{{ notice.giftsBased.sub3 }}</p>
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
import FoodSvgGroup from '@/components/articles/FoodSvgGroup.vue';

import axios from 'axios';

export default {
  name: 'Recommendation',
  filters: {
    currency(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
  },
  components: {
    Sidebar,
    Winelist,
    InfiniteLoading,
    FoodSvgGroup,
  },
  data: () => ({
    // contentState: 0,
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
        favorite2: '님이 선호하지 않는 향은 선택되지 않았습니다.\n선호도 정보를 선택하면 더 자세한 추천을 받을 수 있습니다. 선호도 정보를 수정해보세요!',
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
      giftsBased: {
        title: 'Your Wine 유저가 선택한 0원 ~ 999999원 대의 선물용 TOP10 가지 와인을 추천해드립니다.',
        sub1: '선물용 와인은 YourWine 유저가 남긴 리뷰 평점 데이터를 바탕으로 호불호가 낮은 top 10가지 와인을 추천해드립니다.',
        sub2: '와인별 리뷰 평점 평균과 평점의 표준편차를 다음과 같은 계산식을 사용해 상위 10가지 데이터를 추출합니다.',
        sub3: '계산식, 평균/(a+표준편차^n) a:a가 커지면 표준편차 영향 줄어들고, n이 커지면 표준편차의 숫자 변화에 대한 차이가 커진다.',
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
    // TODO: 품종 필터
    filterToggleState: false,
    filterActive: false,
    sweet: [1, 5],
    acidity: [1, 5],
    tannin: [1, 5],
    body: [1, 5],
    step: ['1', '2', '3', '4', '5'],
    // 선물용 추천
    price: [0, 999999],
    giftsInfoState: false,
    giftsFilterState: false,
  }),
  created() {
    // vuex init, 선호도 설정 여부 확인
    this.actGetCheckPreperence().then(() => {
      this.getWine0(this.$refs.infiniteLoading.stateChanger);
    });
  },
  mounted() {
    this.changeContent(this.contentState);
  },
  computed: {
    ...mapState('userInfo', ['userInfo']),
    ...mapState('nav', ['navActive']),
    ...mapState('recommendation', [
      'contentState',
      'foodId',
      'checkPreference',
      'preferenceBasedRecom',
      'ratingBasedRecom',
      'pairingBasedRecom',
      'giftBasedRecom',
    ]),
  },
  watch: {
    contentState: function() {
      this.$refs.infiniteLoading.stateChanger.reset();
    },
    checkPreference: function() {
      if (this.checkPreference === null) return;
      if (!this.checkPreference) {
        alert(`해당 서비스는 선호도 설정이 필요합니다.\n'내정보' 페이지에서 선호도를 설정해주세요.`);
        $state.complete();
        return;
      }
    },
  },
  methods: {
    ...mapActions('recommendation', [
      'actGetCheckPreperence',
      'actGetPreferenceBasedRecom',
      'actGetPreferenceBasedRecomFilter',
      'actGetRatingBasedRecom',
      'actGetPairingBasedRecom',
      'actGetTop10',
    ]),

    changeContent(index) {
      // this.contentState = index;
      console.log(this.contentState);
      this.$store.commit('recommendation/SET_CONTENT_STATE', index);
      console.log(this.contentState);
      window.scrollTo({ top: 0, behavior: 'smooth' });
      switch (index) {
        case 0:
          this.infoTextMaker();
          break;
        case 1:
          this.getWine1(this.$refs.infiniteLoading.stateChanger);
          this.infoTextMaker();
          break;
        case 2:
          this.notice.title = '';
          this.notice.text = '';
          break;
        case 3:
          this.getWine3(this.$refs.infiniteLoading.stateChanger);
          this.infoTextMaker();
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
          this.getWine0($state);
          break;
        case 1:
          // 평점 기반 추천
          this.getWine1(this.$refs.infiniteLoading.stateChanger);
          break;
        // 음식 페어링
        case 2:
          this.getWine2($state);
          break;
        // 선물용
        case 3:
          this.getWine3(this.$refs.infiniteLoading.stateChanger);
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
          // 미로그인 서비스 불가
          if (this.userInfo.token == null) {
            this.notice.title = '해당 서비스는 로그인 후 사용하실 수 있습니다.';
            this.notice.text = '';
            return;
          }
          this.notice.title = `${this.userInfo.nickname}${this.notice.preferenceBased.title}`;
          if (this.checkPreference && this.preferenceBasedRecom.dislikeFlavorList.length >= 1) {
            // 좋아하는 맛, 싫어하는 맛 모두 설정
            this.notice.text = `${this.userInfo.nickname}${this.notice.preferenceBased.both}`;
            this.preferenceBasedRecom.likeFlavorList.forEach((element) => {
              this.notice.text += `${element.name}, `;
            });
            this.notice.text += `입니다.\n${this.notice.preferenceBased.both2}`;
            this.preferenceBasedRecom.dislikeFlavorList.forEach((element) => {
              this.notice.text += `${element.name}, `;
            });
            this.notice.text = this.notice.text.slice(0, -2);
            this.notice.text += '입니다.';
            console.log(this.notice.text);
          } else if (this.checkPreference && this.preferenceBasedRecom.dislikeFlavorList.length == 0) {
            // 싫어하는 맛을 설정하지 않은 경우
            this.notice.text = `${this.userInfo.nickname}${this.notice.preferenceBased.favorite}`;
            this.preferenceBasedRecom.likeFlavorList.forEach((element) => {
              this.notice.text += `${element.name}, `;
            });
            this.notice.text += `입니다.\n${this.userInfo.nickname}${this.notice.preferenceBased.favorite2}`;
          }
          break;
        // 평점 기반
        case 1:
          // 미로그인 서비스 불가
          if (this.userInfo.token == null) {
            this.notice.title = '해당 서비스는 로그인 후 사용하실 수 있습니다.';
            this.notice.text = '';
            return;
          }
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
        // 선물용
        case 3:
          this.notice.title = this.notice.giftsBased.title;
          this.notice.text = '';
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
            this.wines0.push(...this.preferenceBasedRecom.wineList);
            this.page0 += 1;
            this.infoTextMaker();
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
            this.wines0.push(...this.preferenceBasedRecom.wineList);
            this.page0 += 1;
            this.infoTextMaker();
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
            this.infoTextMaker();
          }
        } else {
          $state.error(); //통신에러
          return;
        }
        $state.loaded(); //정상적 종료
        $state.complete();
      });
    },
    getWine2($state) {
      if (this.foodId == 0) {
        return;
      }
      this.actGetPairingBasedRecom({
        page: this.page2,
        foodId: this.foodId,
      }).then((result) => {
        if (result) {
          if (this.pairingBasedRecom.wineList.length === 0) {
            // 더 이상 불러올 목록이 없는 경우
            $state.complete();
          }
          this.wines2.push(...this.pairingBasedRecom.wineList);
          this.page2 += 1;
          setTimeout(() => {
            $state.loaded();
          }, 1000);
        } else {
          $state.error(); //통신에러
          return;
        }
      });
      console.log(this.page2);
    },
    getWine3($state) {
      this.actGetTop10({
        min: this.price[0],
        max: this.price[1],
      }).then((result) => {
        if (result) {
          if (this.giftBasedRecom.length) {
            this.wines3 = this.giftBasedRecom;
            this.page3 += 1;
            this.infoTextMaker();
          }
        } else {
          $state.error(); //통신에러
          return;
        }
        $state.loaded(); //정상적 종료
        $state.complete();
      });
    },

    // 추가 기능 버튼
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
    clickedGoMyapge() {
      this.$router.push({ name: 'Mypage' });
    },
    // 페어링 기반 버튼
    setFoodId(foodId) {
      this.wines2 = [];
      this.page2 = 1;
      this.$store.commit('recommendation/SET_FOOD_ID', foodId);
      setTimeout(() => {
        this.$refs.infiniteLoading.stateChanger.reset();
      }, 600);
    },
    clickedGiftsInfoToggleBtn() {
      this.giftsInfoState = !this.giftsInfoState;
    },
    clickedGiftsFilterToggleBtn() {
      this.giftsFilterState = !this.giftsFilterState;
    },
    clickedFilterReset2() {
      this.price = [0, 999999];
    },
    clickedFilterSubmit2() {
      this.getWine3();
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

/* 추가 버튼 */
.button-group {
  display: flex;
  justify-content: space-between;
}
.slider-toggle-btn,
.go-mypage-btn,
.gifts-info-toggle-btn,
.gifts-filter-toggle-btn {
  display: inline-block;
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
.slider-toggle-btn:hover,
.go-mypage-btn:hover,
.gifts-info-toggle-btn:hover,
.gifts-filter-toggle-btn:hover {
  background-color: #e1aa5786;
}
.go-mypage-btn {
  display: inline-block;
  border: 1px solid var(--basic-color-key);
  width: 200px;
  border-radius: 40px;
}
.gifts-btn-group {
  position: relative;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.gifts-slider-group,
.gifts-info-list {
  top: -60px;
  display: flex;
  flex-direction: column;
  position: absolute;
  z-index: 3;
  padding: 3rem;
  color: #000;
  border-radius: 2rem;
  background-color: rgba(255, 255, 255, 0.6);
  backdrop-filter: saturate(180%) blur(30px);
  transition: all 0.3s ease;
  transform: scale(0);
  visibility: hidden;
  opacity: 0;
}
.tick-label {
  align-self: flex-end;
  margin-bottom: 20px;
}
.gifts-slider-group {
  width: 600px;
  height: 240px;
  left: -300px;
}
.gifts-info-list {
  width: 600px;
  height: auto;
  right: -300px;
}
.gifts-info-list p {
  margin: 0.5rem;
}
.gifts-info-list p::before {
  content: '-';
  margin-right: 0.5rem;
}
.gifts-slider-group-active,
.gifts-info-list-active {
  top: 2rem;
  visibility: visible;
  opacity: 1;
  transform: scale(1);
}
.gifts-slider-group-active {
  left: 6rem;
}
.gifts-info-list-active {
  right: 6rem;
}

/* 맛 필터 */
#taste-slider {
  position: relative;
}
.slider-group {
  top: -280px;
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
  top: -80px;
  left: 6rem;
  visibility: visible;
  opacity: 1;
  transform: scale(1);
}
.slider-btn-group {
  display: flex;
  flex-direction: row;
  justify-content: center;
  width: 100%;
}
.slider-btn-group button {
  width: 150px;
  height: 50px;
  margin: 0 1rem;
  border-radius: 25px;
  background-color: rgba(255, 255, 255, 0.5);
  border: solid 1px var(--basic-color-key);
  color: black;
  font-size: 1.2rem;
}

/* 음식 필터 */
#food-svg {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
#food-svg p {
  margin-top: 1rem;
  padding: 0.2em;
  font-size: 1.2rem;
  font-weight: 700;
  color: #fff;
  border-bottom: 1px solid var(--basic-color-key);
}
#food-svg span {
  font-weight: 900;
  color: var(--basic-color-key);
  font-size: 3rem;
}
</style>

<style>
#taste-slider .v-label {
  width: 60px;
}
#taste-slider .v-slider__tick-label {
  margin: 5px 0px 5px -2px;
}
</style>
