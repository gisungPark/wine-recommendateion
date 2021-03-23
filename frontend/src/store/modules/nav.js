const state = {
  navActive: false,
};
const getters = {};
const actions = {};
const mutations = {
  SET_NAV_TOGGLE(state) {
    state.navActive = !state.navActive;
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
