import * as authApi from '../../api/auth';

// 유저 정보 #######################
const INIT_USER = () => {
  return {
    token: localStorage.getItem('token'),
    nickname: localStorage.getItem('nickname'),
    provider: localStorage.getItem('provider'),
    profile: localStorage.getItem('profile'),
    defaultProfile: localStorage.getItem('defaultProfile'),
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
    localStorage.setItem('token', state.userInfo.token);
    localStorage.setItem('nickname', state.userInfo.nickname);
    localStorage.setItem('provider', state.userInfo.provider);
    localStorage.setItem('profile', state.userInfo.profile);
    localStorage.setItem('defaultProfile', state.userInfo.defaultProfile);
  },
  SET_LOGOUT(state) {
    localStorage.clear();
    state.userInfo = INIT_USER();
  },
  SET_KAKAO_TOKEN(state, payload) {
    state.token = payload;
  }
};
// STATE 값 변경 O + 비동기
const actions = {
  // ######### 로그인
  async login(context, { email, password }) {
    try {
      const response = await authApi.login(email, password);
      console.log("첫번째~~~~~~~~~");
      console.log(response);
      // 로그인 성공!!
      if (response.data.code === 0) {
        const userinfo = await authApi.getUserInfo();
        console.log("두번째~~~~~~~~~");
        console.log(userinfo);

        context.commit('SET_USER_INFO', {
          userInfo: {
            token: response.data.token,
            nickname: response.data.nickname,
            provider: userinfo.data.provider,
            profile: userinfo.data.img,
            defaultProfile: "https://blog.kakaocdn.net/dn/bezjux/btqCX8fuOPX/6uq138en4osoKRq9rtbEG0/img.jpg",
          },
        });
      }
      return response;
    } catch (error) {
      alert('이메일 비밀번호를 확인하세요!!');
      return error;
    }
  },

  async kakaoLogin(context, { data }) {
    try {
      const response = await authApi.kakaoLogin(data);
      console.log("카카오 로그인11111111111111111111111111111");
      console.log(response);
      console.log("카카오 로그인 끝!!!!");
      // 로그인 성공!!
      if (response.data.code === 0) {
        context.commit('SET_USER_INFO', {
          userInfo: {
            token: response.data.token,
            nickname: response.data.nickname,
            profile: 'https://t1.daumcdn.net/cfile/tistory/99DBE73359AAC5D224',
          },
        });
      } else if ( response.data.code === 3) {
        alert("닉네임 추가 입력을 해주세요!!")
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
