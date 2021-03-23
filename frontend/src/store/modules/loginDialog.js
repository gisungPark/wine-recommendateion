const state = {
  loginDialog: false,
  joinDialog: false,
};
const getters = {};
const actions = {};
const mutations = {
  SET_LOGIN_TOGGLE(state) {
    state.loginDialog = !state.loginDialog;
  },
  SET_JOIN_TOGGLE(state) {
    state.joinDialog = !state.joinDialog;
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
