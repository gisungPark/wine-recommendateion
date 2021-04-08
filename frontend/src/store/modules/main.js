import * as mainApi from '@/api/main';

const INIT_TODAYSWINE = () => {
  return {
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
  };
};
const INIT_TOP10 = () => {
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
  todaysWine: INIT_TODAYSWINE(),
  top10: INIT_TOP10(),
};
const getters = {
  getEventById: (state) => (id) => {
    return state.events.find((event) => event.id === id);
  },
};

const actions = {
  // 오늘의 와인 정보 요청
  async actGetTodaysWine({ commit }) {
    try {
      const response = await mainApi.getTodaysWine();
      if (response.status === 200) {
        commit('SET_TODAYSWINE', response.data);
      }
    } catch (error) {
      console.log(error);
      alert('오늘의 와인 정보를 가져오는 도중 문제가 발생했습니다.');
    }
  },
  // top10 리스트 정보 요청
  async actGetTop10({ commit }) {
    try {
      const response = await mainApi.getWineTop10();
      if (response.status === 200) {
        commit('SET_TOP10', response.data);
      }
    } catch (error) {
      console.log(erro);
      alert('top10 정보를 가져오는 도중 문제가 발생했습니다.');
    }
  },
};

const mutations = {
  SET_TODAYSWINE(state, payload) {
    state.todaysWine = payload;
  },
  SET_TOP10(state, payload) {
    state.top10 = payload;
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};

//부가적인 기능 함수
const getEventsTransfer = (arr) => {};
