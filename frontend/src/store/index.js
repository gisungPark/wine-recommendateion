import Vue from 'vue';
import Vuex from 'vuex';

// import data from './modules/@temp';
import guideBtn from './modules/guideBtn';
import nav from './modules/nav';
import loginDialog from './modules/loginDialog';
import reviewDialog from './modules/reviewDialog';
import userInfo from './modules/userInfo';
import wineDetail from './modules/wineDetail';
import main from './modules/main';
import mypage from './modules/mypage';
import recommendation from './modules/recommendation';
import scrap from './modules/scrap';

Vue.use(Vuex);

const state = {
  // s3
  s3url: 'https://s3.ap-northeast-2.amazonaws.com/www.yourwine.io/img/wine/',
  s3url_flavor: 'https://s3.ap-northeast-2.amazonaws.com/www.yourwine.io/img/flavor/',
  s3url_topic: 'https://s3.ap-northeast-2.amazonaws.com/www.yourwine.io/img/topic/',
  s3url_profile: 'https://s3.ap-northeast-2.amazonaws.com/www.yourwine.io/img/profile/',
};
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
    main,
    loginDialog,
    userInfo,
    reviewDialog,
    wineDetail,
    mypage,
    recommendation,
    scrap,
  },
});
