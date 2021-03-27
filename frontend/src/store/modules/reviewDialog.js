const state = {
  reviewDialog: false,
};
const getters = {};
const actions = {};
const mutations = {
  SET_REVIEW_TOGGLE(state) {
    state.reviewDialog = !state.reviewDialog;
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
