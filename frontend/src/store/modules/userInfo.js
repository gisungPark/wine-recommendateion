const state = {
  userInfo: {
    userId: '',
    nickname: '와인좋아!!',
    profile: 'https://t1.daumcdn.net/cfile/tistory/99DBE73359AAC5D224',
  },
};
const getters = {
  getUserInfo() {
    return state;
  },
};
const actions = {};
const mutations = {};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
