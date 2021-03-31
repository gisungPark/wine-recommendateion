import * as authApi from '../../api/auth';

// 유저 정보 #######################
const INIT_USER = () => {
  return {
    userInfo: {
      token: localStorage.getItem('token'),
      email: localStorage.getItem('email'),
      nickname: localStorage.getItem('nickname'),
      profile: localStorage.getItem('profile'),
    }
  };
};

const state = {
  userInfo: {
    email: '',
    nickname: '와인좋아!!',
    profile: 'https://t1.daumcdn.net/cfile/tistory/99DBE73359AAC5D224',
  }
};
// STATE 값 변경 X
const getters = {
  getUserInfo() {
    return state;
  },
};
// STATE 갑 변경 O + 동기
const mutations = {
  SET_USER_INFO(state, payload) {
    state.userInfo = payload;
    //로컬 저장
    localStorage.email = state.userInfo.email;
    localStorage.nickname = state.userInfo.nickname;
    localStorage.profile = state.userInfo.profile;
  },
  CLEAR_USER_INFO(state) {
    localStorage.clear();
    state.userInfo = INIT_USER();
  }

};
// STATE 값 변경 O + 비동기
  const actions = {
  // ######### 로그인 
  async login(context, { email, password }) {
    try {
      const response = await authApi.login(email, password);
      // 로그인 성공!!
      if (response.data.message === 'SUCCESS') {
        context.commit('SET_USER_INFO', {
          userInfo: {
            token: response.data.token,
            email: response.data.user.user_email,
            nickname: response.data.user.user_nickname,
            prifile: response.data.user.user_prifile,
          }
        });
      }
      return response;

    } catch (error) {
      alert('이메일 비밀번호를 확인하세요!!');
    return error;
    }
  },
};


export default {
namespaced: true,
state,
getters,
actions,
mutations,
};
