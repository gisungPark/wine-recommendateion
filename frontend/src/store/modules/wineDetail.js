import * as wineDetailApi from '@/api/wineDetail';

const INIT_DETAIL = () => {
  return {
    wineDto: {
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
    reviewList: [
      {
        wineId: 0,
        wineName: '',
        nickname: '',
        userImg: null,
        contents: '',
        point: 0,
        cost: 0,
        time: '2021-01-01T00:00:00.000+00:00',
      },
    ],
    foodList: [
      {
        foodId: 0,
        name: '',
        detail: '',
      },
    ],
    moreWineList: [
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
    ],
    costArray: [0, 0, 0],
    flavorList: [
      {
        flavorId: 0,
        name: '',
      },
    ],
    scrap: false,
    writeReview: false,
  };
};

const state = {
  detail: INIT_DETAIL(),
};
const getters = {
  getEventById: (state) => (id) => {
    return state.events.find((event) => event.id === id);
  },
};
const actions = {
  // 와인 id로 와인의 상세정보 요청
  async actGetWineDetail({ commit }, wineId) {
    try {
      const response = await wineDetailApi.getWineDetail(wineId);
      if (response.status === 200) {
        commit('SET_DETAIL', response.data);
      }
    } catch (error) {
      console.log(error);
      alert(`와인 정보를 가져오는 도중 문제가 발생했습니다.`);
    }
  },
};
const mutations = {
  SET_DETAIL(state, payload) {
    state.detail = payload;
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
