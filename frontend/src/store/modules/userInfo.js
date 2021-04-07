import * as authApi from '../../api/auth';

// 유저 정보 #######################
const INIT_USER = () => {
  return {
    kakaoToken: localStorage.getItem('kakaoToken'),
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
  getToken() {
    return state.userInfo.token;
  }
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
    localStorage.setItem('kakaoToken', state.userInfo.kakaoToken);
    
  },
  SET_LOGOUT(state) {
    localStorage.clear();
    state.userInfo = INIT_USER();
  },
  SET_TEMP_TOKEN(state, token) {
    state.userInfo.token = token;
    localStorage.setItem('token', state.userInfo.token);
  },
  SET_KAKAO_TOKEN(state, token) {
    state.userInfo.kakaoToken = token;
    localStorage.setItem('kakaoToken', state.userInfo.kakaoToken);
  },
  SET_SWAP_TOKEN(state, token) {
    state.userInfo.token = state.userInfo.kakaoToken;
    localStorage.setItem('token', state.userInfo.token);
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
        context.commit('SET_TEMP_TOKEN', response.data.token);
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
      console.log("카카오 22222222222222222222222222222222222222222222");
      console.log(response);
      // 로그인 성공!! #############################
      if (response.data.code === 2) {
        // 토큰 저장!!
        context.commit('SET_KAKAO_TOKEN', response.data.token);
       // 최초 로그인 -> 회원가입 #############################
      } else if (response.data.code === 3) {
        console.log("카카오 3333333333333333333333333333333333333333333333");
        // 토큰 저장!!
        context.commit('SET_KAKAO_TOKEN', response.data.token);
        context.commit('loginDialog/SET_NICKNAME_TOGGLE', null, { root: true });
        alert("닉네임을 등록 해주세요!!")
      } else {
        alert("이메일 비밀번호를 확인해 주세요!!")
      }
        
      return response;
    } catch (error) {
      alert('이메일 비밀번호를 확인하세요!!');
      return error;
    }
  },
  /**
   * 카카오 회원가입 닉네임 추가설정!!
   */
  async kakaoJoin(context, { nickname }) {
    try {
      context.commit('SET_SWAP_TOKEN');
      const response = await authApi.kakaoJoin(nickname);
      console.log(response);
      if (response.data.code === 5) {
        console.log("카카오 로그인 닉네임 설정 성공!!!!!!!!!!!!!!!!!");
        // 토큰값 저장!!
        context.commit('SET_TEMP_TOKEN', response.data.token);
      } else {
        console.log("카카오 로그인 닉네임 설정 실패!!!!!!!!!!!!!!!!!");
      }
    } catch (error) {
      console.log(error);
    }
    return response;
  },

  /**
   * 토큰값으로 유저 정보 읽어오기!!!
   */
  async readUserInfo (context) {
    const info = await authApi.getUserInfo();
    console.log(context.getters.getToken);
    context.commit('SET_USER_INFO', {
      userInfo: {
        token: context.getters.getToken,
        nickname: info.data.nickname,
        provider: info.data.provider,
        profile: info.data.img,
        defaultProfile: "https://blog.kakaocdn.net/dn/bezjux/btqCX8fuOPX/6uq138en4osoKRq9rtbEG0/img.jpg",
      },
    });
    return info;
  }
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
