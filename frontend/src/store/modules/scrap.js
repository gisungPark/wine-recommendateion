import * as scrapApi from '@/api/scrap';

const state = {};
const getters = {};
const actions = {
  // 스크랩 추가 요청
  async actAddWineScrap(context, wineId) {
    try {
      const response = await scrapApi.postWineScrap(wineId);
      console.log(response);
      if (response.status === 200) {
        return true;
      }
      return false;
    } catch (error) {
      console.log('스크랩을 요청하는 도중 문제가 발생했습니다.');
      return false;
    }
  },

  async actRemoveWineScrap(context, wineId) {
    try {
      const response = await scrapApi.deleteWineScrap(wineId);
      console.log(response);
      if (response.status === 200) {
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
