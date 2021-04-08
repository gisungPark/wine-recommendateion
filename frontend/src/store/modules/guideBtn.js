import router from '../../router';

const state = {
  guideMsg: '',
  guideBtn: false,
  guideBtnTip: false,
};
const getters = {};
const actions = {
  initGuideMsg(context) {
    if (context.rootState.userInfo.userInfo.token) {
      setGuideMsgMacker(context);
    } else {
      context.commit('SET_GUIDE_MSG', '로그인하여 다양한 서비스를 이용하세요.');
    }
    context.commit('SET_GUIDEBTNTIP_OPEN');
    setTimeout(() => {
      context.commit('SET_GUIDEBTNTIP_CLOSE');
    }, 8000);
  },

  setGuideMsg(context) {
    setInterval(() => {
      const sec = Math.floor(Math.random() * 4) + 1;
      setTimeout(() => {
        // 상태에 따라서 가이드 메시지 변경 및 guideMsg 상태 변환
        // 로그인 여부
        if (context.rootState.userInfo.userInfo.token) {
          setGuideMsgMacker(context);
        } else {
          context.commit('SET_GUIDE_MSG', '로그인하여 다양한 서비스를 이용하세요.');
        }
        // 추천 페이지
        // 디테일 페이지
      }, sec * 5000);
    }, 15000);
  },
};
const mutations = {
  SET_GUIDEBTN_TOGGLE(state) {
    state.guideBtn = !state.guideBtn;
  },
  SET_GUIDEBTNTIP_TOGGLE(state) {
    state.guideBtnTip = !state.guideBtnTip;
  },
  SET_GUIDEBTNTIP_OPEN(state) {
    state.guideBtnTip = true;
  },
  SET_GUIDEBTNTIP_CLOSE(state) {
    state.guideBtnTip = false;
  },
  SET_GUIDE_MSG(state, str) {
    state.guideMsg = str;
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};

const setGuideMsgMacker = (context) => {
  console.log(router.currentRoute.name);
  switch (router.currentRoute.name) {
    case 'Mypage':
      context.commit('SET_GUIDE_MSG', '회원님의 정보를 관리할 수 있습니다. 저희와 함께했던 기록은 chart를 참고해주십시오.');
      break;
    case 'Main':
      context.commit('SET_GUIDE_MSG', `오늘의 와인은 ${context.rootState.main.todaysWine.type} 와인입니다.`);
      break;
    case 'Recommendation':
      context.commit('SET_GUIDE_MSG', '선호도 설정, 리뷰를 남겨주시면 와인을 추천해드립니다.');
      break;
    case 'Wines':
      context.commit('SET_GUIDE_MSG', `'YOUR WINE'에서 취급하는 모든 와인에 대해 알려드립니다.`);
      break;
    case 'WineDetials':
      context.commit('SET_GUIDE_MSG', `'${context.rootState.wineDetail.detail.wineDto.kname}' 와인이군요!`);
      setTimeout(() => {
        context.commit('SET_GUIDE_MSG', `어울리는 음식은 ${context.rootState.wineDetail.detail.foodList[0].name} 입니다.`);
      }, 2000);
      setTimeout(() => {
        context.commit('SET_GUIDE_MSG', `이 와인과 비슷한 와인을 아래쪽에서 알려드리겠습니다.`);
      }, 4000);
      break;
    case 'Topic':
      context.commit('SET_GUIDE_MSG', '와인과 관련된 이야기를 들려드리겠습니다.');
      break;
    default:
      context.commit('SET_GUIDE_MSG', '이곳은 관계자외 출입금지입니다. 로고를 눌러 이동해주세요.');
      break;
  }
  context.commit('SET_GUIDEBTNTIP_OPEN');
  setTimeout(() => {
    context.commit('SET_GUIDEBTNTIP_CLOSE');
  }, 8000);
};
