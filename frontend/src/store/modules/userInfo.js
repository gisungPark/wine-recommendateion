import * as authApi from '../../api/auth';

// 유저 정보 #######################
const INIT_USER = () => {
  return {
    userInfo: {
      token: localStorage.getItem('token'),
      nickname: localStorage.getItem('nickname'),
      profile: 'https://t1.daumcdn.net/cfile/tistory/99DBE73359AAC5D224',
    }
  };
};

const state = {
  userInfo: INIT_USER(),
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
    state.userInfo = payload.userInfo;
    //로컬 저장
    localStorage.setItem("token", state.userInfo.token);
    localStorage.setItem("nickname", state.userInfo.nickname);
    localStorage.setItem("profile", state.userInfo.profile);
  },
  SET_LOGOUT(state) {
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
      if (response.data.code === 0) {
        context.commit('SET_USER_INFO', {
          userInfo: {
            token: response.data.token,
            nickname: response.data.nickname,
            profile: 'https://t1.daumcdn.net/cfile/tistory/99DBE73359AAC5D224'
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
