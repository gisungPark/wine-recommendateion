import * as calendarApi from '@/api/calendar';
const INIT_EVENT = () => {
  return {
    //state 초기화
  };
};

const state = {
  event: INIT_EVENT(),
};
const getters = {
  getEventById: (state) => (id) => {
    return state.events.find((event) => event.id === id);
  },
};
const actions = {
  //async-await 사용 예시
  async actGetEvents({ commit }, boardName) {
    try {
      const response = await calendarApi.getCalendar(getBoardId(boardName));
      if (response.data.message === 'SUCCESS') {
        // console.log(response.data.calendars);
        let payload = getEventsTransfer(response.data.calendars);
        commit('SET_CALENDAR_LIST', payload);
      }
    } catch (error) {
      console.log(error);
      alert('일정 목록을 가져오는 도중 문제가 발생했습니다.');
    }
  },
};
const mutations = {
  SET_CALENDAR_LIST(state, payload) {
    state.events = [...payload];
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};

//부가적인 기능 함수
const getEventsTransfer = (arr) => {};
