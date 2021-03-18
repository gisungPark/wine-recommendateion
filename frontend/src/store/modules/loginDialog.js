const state = {
  loginDialog: false,
};
const getters = {};
const actions = {};
const mutations = {
  SET_LOGIN_TOGGLE(state) {
    state.loginDialog = !state.loginDialog;
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
