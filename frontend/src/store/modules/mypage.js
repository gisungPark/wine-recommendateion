import * as mypageApi from '@/api/mypageApi';

const INIT_FLAVOR = () => {
  return [
    {
      flavorId: '',
      name: '',
    },
  ];
};

const state = {
  flavors: INIT_FLAVOR(),
};
const getters = {
  getEventById: (state) => (id) => {
    return state.events.find((event) => event.id === id);
  },
  getDiviedFlavors(state) {
    let diviedFlavors = {
      list1: [],
      list2: [],
      list3: [],
      list4: [],
      list5: [],
    };
    for (var i = 0; i < state.flavors.length; i++) {
      const item = {
        flavor_id: state.flavors[i].flavorId,
        name: state.flavors[i].name,
        img: state.flavors[i].img,
        isLike: false,
        isHate: false,
      };

      if (i % 5 == 0) diviedFlavors.list1.push(item);
      else if (i % 5 == 1) diviedFlavors.list2.push(item);
      else if (i % 5 == 2) diviedFlavors.list3.push(item);
      else if (i % 5 == 3) diviedFlavors.list4.push(item);
      else diviedFlavors.list5.push(item);
    }
    return diviedFlavors;
  },
};
const actions = {
  async actGetFlavor({ commit }) {//context를 쓰면 다른 모듈에 있는 것도 쓸수 있음
    //context쓰고 밑에 log 찍어보기
    try {
      const response = await mypageApi.mypageFlavor();
      if (response.status === 200) {
        // console.log(response);
        commit('SET_FLAVORS', response.data);
      }
    } catch (error) {
      console.log(error);
      alert('Flavor 카드를 불러오는 도중 문제가 발생했습니다.');
    }
  },
  async actGetScrap({ commit }) {
    try {
      const response = await mypageApi.mypageScrap();
      if (response.status === 200) {
        // console.log(response);
        commit('SET_SCRAPS', response.data);
      }
    } catch (error) {
      console.log(error);
      alert('Scrap을 불러오는 도중 문제가 발생했습니다.');
    }
  }
};
const mutations = {
  SET_FLAVORS(state, payload) {
    state.flavors = payload;
  },
  SET_SCRAPS(state, payload) {
    state.scraps = payload;
  }
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
