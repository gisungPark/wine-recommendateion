const state = {
  reviewDialog: false,
  reviewWriteDialog: false,
};
const getters = {};
const actions = {};
const mutations = {
  SET_REVIEW_TOGGLE(state) {
    state.reviewDialog = !state.reviewDialog;
  },
  SET_REVIEW_WRITE_TOGGLE(state) {
    state.reviewWriteDialog = !state.reviewWriteDialog;
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
