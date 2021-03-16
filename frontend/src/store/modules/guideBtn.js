const state = {
  guideBtn: false,
  guideBtnTip: true,
};
const getters = {};
const actions = {};
const mutations = {
  SET_GUIDEBTN_TOGGLE(state) {
    state.guideBtn = !state.guideBtn;
  },
  SET_GUIDEBTNTIP_TOGGLE(state) {
    state.guideBtnTip = !state.guideBtnTip;
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
