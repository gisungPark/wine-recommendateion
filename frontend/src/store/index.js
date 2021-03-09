import Vue from 'vue';
import Vuex from 'vuex';

// import data from './modules/@temp';

Vue.use(Vuex);

const state = {
  spinnerActive: false,
};
const getters = {
  //state에서 추가적인 작업 후 return
};
const actions = {
  //async await 사용
};
const mutations = {
  SET_SPINNER_TOGGLE(state) {
    state.spinnerActive = !state.spinnerActive;
  },
};

export default new Vuex.Store({
  state,
  getters,
  actions,
  mutations,
  modules: {},
});
