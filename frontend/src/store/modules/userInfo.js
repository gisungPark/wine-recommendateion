const state = {
    userInfo: {
        userId: '',
        nickname: '와인좋아!!',
        profile: 'https://newsimg.hankookilbo.com/cms/articlerelease/2020/10/02/f18cb31b-168f-4c4e-8fac-86a9864350ef.jpg',
    }
  };
const getters = {
    getUserInfo() {
        return state;
    }  

  };
  const actions = {};
  const mutations = {
  };
  
  export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations,
  };
  