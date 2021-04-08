const state = {
  loginDialog: false,
  joinDialog: false,
  nicknameDialog: false,
  findPwDialog: false,
  profileDialog: false,
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
  SET_NICKNAME_TOGGLE(state) {
    state.nicknameDialog = !state.nicknameDialog;
  },
  SET_FINDPW_TOGGLE(state) {
    state.findPwDialog = !state.findPwDialog;
  },
  SET_PROFILE_TOGGLE(state) {
    state.profileDialog = !state.profileDialog;
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
