import * as recommendationApi from '@/api/recommendation';

const INIT_RATING_BASED_RECOM = () => {
  return {
    writeReview: false,
    batchData: false,
    wineDtoList: [
      {
        wineId: 0,
        kname: '',
        ename: '',
        alcohol: '',
        type: '',
        temper: '',
        avg: 0,
        price: 0,
        year: '2020',
        detail: '',
        shop: null,
        sweet: 0,
        acidity: 0,
        body: 0,
        tannin: 0,
        area: '호주 ',
        grape: {
          grapeId: 0,
          kname: '',
          ename: '',
          detail: '',
        },
      },
    ],
  };
};
const INIT_PREFERENCE_BASED_RECOM = () => {
  return {
    likeFlavorList: [
      {
        flavorId: 1,
        name: '0',
        isLike: false,
        isHate: false,
      },
    ],
    dislikeFlavorList: [
      {
        flavorId: 1,
        name: '0',
        isLike: false,
        isHate: false,
      },
    ],
    wineList: [
      {
        wineId: 0,
        kname: '',
        ename: '',
        alcohol: '',
        type: '',
        temper: '',
        avg: 0,
        price: 0,
        year: '2020',
        detail: '',
        shop: null,
        sweet: 0,
        acidity: 0,
        body: 0,
        tannin: 0,
        area: '호주 ',
        grape: {
          grapeId: 0,
          kname: '',
          ename: '',
          detail: '',
        },
      },
    ],
  };
};

const state = {
  ratingBasedRecom: INIT_RATING_BASED_RECOM(),
  checkPreference: false,
  preferenceBasedRecom: INIT_PREFERENCE_BASED_RECOM(),
};
const getters = {};
const actions = {
  // 평점 기반 와인 추천 목록 요청
  // 5개의 와인 리스트 제공
  async actGetRatingBasedRecom({ commit }) {
    try {
      const response = await recommendationApi.getRatingBasedRecom();
      if (response.status === 200) {
        commit('SET_RATING_BASED_RECOM', response.data);
        return true;
      }
    } catch (error) {
      console.log('평점기반 추천 리스트를 가져오는 도중 문제 발생!');
      console.log(error);
      return false;
    }
  },

  // 선호도 설정 여부 확인
  // response 값으로 선호도 추천 탭 누를 때, info 메시지 조절
  async actGetCheckPreperence({ commit }) {
    try {
      const response = await recommendationApi.getCheckPreference();
      if (response.status === 200) {
        commit('SET_CHECK_PREFERENCE', response.data);
      }
    } catch (error) {
      console.log('선호도 설정 여부 확인 중 문제 발생!');
      console.log(error);
    }
  },

  // 선호도 기반 와인 추천 목록 요청
  async actGetPreferenceBasedRecom({ commit }, page) {
    try {
      const response = await recommendationApi.getPreferenceBasedRecom(page);
      if (response.status === 200) {
        commit('SET_PREFERENCE_BASED_RECOM', response.data);
        return true;
      }
    } catch (error) {
      console.log('선호도 기반 추천 리스트를 가져오는 도중 문제 발생!');
      console.log(error);
      return false;
    }
  },
};

const mutations = {
  SET_RATING_BASED_RECOM(state, payload) {
    state.ratingBasedRecom = payload;
  },
  SET_CHECK_PREFERENCE(state, payload) {
    state.checkPreference = payload;
  },
  SET_PREFERENCE_BASED_RECOM(state, payload) {
    state.preferenceBasedRecom = payload;
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
