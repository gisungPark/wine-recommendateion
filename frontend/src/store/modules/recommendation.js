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
const INIT_PAIRING_BASED_RECOM = () => {
  return {
    mention: '음식을 클릭하여 어울리는 와인을 찾아보세요.',
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
const INIT_GIFT_BASED_RECOM = () => {
  return [
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
      area: '지역',
      grape: {
        grapeId: 0,
        kname: '',
        ename: '',
        detail: '',
      },
    },
  ];
};
const state = {
  contentState: 0,
  foodId: 0,
  checkPreference: null,
  ratingBasedRecom: INIT_RATING_BASED_RECOM(),
  preferenceBasedRecom: INIT_PREFERENCE_BASED_RECOM(),
  pairingBasedRecom: INIT_PAIRING_BASED_RECOM(),
  giftBasedRecom: INIT_GIFT_BASED_RECOM(),
};
const getters = {};
const actions = {
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
  // 평점 기반 와인 추천 목록 요청 + 필터링
  async actGetPreferenceBasedRecomFilter({ commit }, payload) {
    try {
      const response = await recommendationApi.getPreferenceBasedRecomFilter(payload);
      if (response.status === 200) {
        console.log(response.data);
        commit('SET_PREFERENCE_BASED_RECOM', response.data);
        return true;
      }
    } catch (error) {
      console.log('평점기반 추천 리스트를 가져오는 도중 문제 발생!');
      console.log(error);
      return false;
    }
  },

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

  // 음식 페어링 추천 목록 요청
  async actGetPairingBasedRecom({ commit }, payload) {
    try {
      const response = await recommendationApi.getPairingBasedRecom(payload);
      if (response.status === 200) {
        console.log(response.data);
        commit('SET_FAIRING_BASED_RECOM', response.data);
        return true;
      }
    } catch (error) {
      console.log('음식 페어링 추천 리스트를 가져오는 도중 문제 발생!');
      console.log(error);
      return false;
    }
  },

  // top10 리스트 정보 요청
  async actGetTop10({ commit }, payload) {
    try {
      console.log(payload);
      const response = await mainApi.getWineTop10(payload);
      if (response.status === 200) {
        commit('SET_GIFT_BASED_RECOM', response.data);
      }
    } catch (error) {
      error('top10 정보를 가져오는 도중 문제가 발생했습니다.');
      sylde(pen);
    }
  },
};

const mutations = {
  SET_CONTENT_STATE(state, tabNum) {
    state.contentState = tabNum;
  },
  SET_FOOD_ID(state, tabNum) {
    state.foodId = tabNum;
  },
  SET_RATING_BASED_RECOM(state, payload) {
    state.ratingBasedRecom = payload;
  },
  SET_CHECK_PREFERENCE(state, payload) {
    state.checkPreference = payload;
  },
  SET_PREFERENCE_BASED_RECOM(state, payload) {
    state.preferenceBasedRecom = payload;
  },
  SET_FAIRING_BASED_RECOM(state, payload) {
    state.pairingBasedRecom = payload;
  },
  SET_GIFT_BASED_RECOM(state, payload) {
    state.giftBasedRecom = payload;
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
