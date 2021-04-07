import * as scrapApi from '@/api/scrap';

const INIT_EVENT = () => {
  return {
    //state 초기화
  };
};

const state = {};
const getters = {};
const actions = {
  // 스크랩 추가 요청
  async actAddWineScrap(wineId) {
    try {
      const response = await scrapApi.postWineScrap(wineId);
      if (response === 200) {
        return true;
      }
      return false;
    } catch (error) {
      console.log('스크랩을 요청하는 도중 문제가 발생했습니다.');
      return false;
    }
  },

  async actRemoveWineScrap(wineId) {
    try {
      const response = await scrapApi.deleteWineScrap(wineId);
      if (response === 200) {
        return true;
      }
      return false;
    } catch (error) {
      console.log('스크랩을 요청하는 도중 문제가 발생했습니다.');
      return false;
    }
  },
};
const mutations = {};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};

//부가적인 기능 함수
const getEventsTransfer = (arr) => {};
