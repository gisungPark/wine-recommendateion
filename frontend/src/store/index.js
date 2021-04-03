import Vue from 'vue';
import Vuex from 'vuex';

// import data from './modules/@temp';
import guideBtn from './modules/guideBtn';
import nav from './modules/nav';
import loginDialog from './modules/loginDialog';
import reviewDialog from './modules/reviewDialog';
import userInfo from './modules/userInfo';
import mypage from './modules/mypage';

Vue.use(Vuex);

const state = {};
const getters = {
  //state에서 추가적인 작업 후 return
};
const actions = {
  //async await 사용
};
const mutations = {};

export default new Vuex.Store({
  state,
  getters,
  actions,
  mutations,
  modules: {
    guideBtn,
    nav,
    loginDialog,
    userInfo,
    reviewDialog,
    mypage,
  },
});
