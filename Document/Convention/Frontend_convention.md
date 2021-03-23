# Frontend Convention

## Naming

- .vue 파일은 PascalCase로 명명
- 네이밍이 길더라도 Abbreviation(축약어) 사용 지양

## vue convention

- 기본적인 규약은 공식 문서를 따를 것 [Style Guide — Vue.js](https://kr.vuejs.org/v2/style-guide/)

- Props default 정의

  - REST API 통신 에러 시 예외처리를 위한 default 값 정의

    ```vue
    Vue.component('my-component', {
      props: {
        // 기본 타입 체크 (`Null`이나 `undefinded`는 모든 타입을 허용합니다.)
        propA: Number,
        // 여러 타입 허용
        propB: [String, Number],
        // 필수 문자열
        propC: {
          type: String,
          required: true
        },
        // 기본값이 있는 숫자
        propD: {
          type: Number,
          default: 100
        },
        // 기본값이 있는 오브젝트
        propE: {
          type: Object,
          // 오브젝트나 배열은 꼭 기본값을 반환하는
          // 팩토리 함수의 형태로 사용되어야 합니다. 
          default: function () {
            return { message: 'hello' }
          }
        },
        // [선택적 적용] 커스텀 유효성 검사 함수
        propF: {
          validator: function (value) {
            // 값이 항상 아래 세 개의 문자열 중 하나여야 합니다. 
            return ['success', 'warning', 'danger'].indexOf(value) !== -1
          }
        }
      }
    })
    ```

- v-for 내 key값 형식

  - data 변경 시 실시간 rerender를 위해 unique 한 key 값으로 설정

  - data 내 id 값이 없을 경우 index와 적절한 suffix를 조합하여 설정

    ```vue
    <div v-for="(item, index) in list" :key="index+item.id">
        {{item.data}}
    </div>
    
    또는
    
    <div id="A" v-for"(item, index) in list" :key"index+'A'">
        {{item.data}}
    </div>
    ```

- UI프레임 워크

  - vuetify 사용 [vuetify 공식 문서 웹페이지 바로가기](https://vuetifyjs.com/en/)
  - 고도화된 기능에만 적용
    - Calendar [바로가기](https://vuetifyjs.com/en/components/calendars/#usage)
    - File inputs [바로가기](https://vuetifyjs.com/en/components/file-inputs/)
    - transitions [바로가기](https://vuetifyjs.com/en/styles/transitions/)
    - ...(추가 활용시 아래 이하 업데이트)

## axios convention

- api 폴더 내에서 관리

- baseURL 및 interceptor를 위한 최상위 .js 파일 

  ```javascript
  //http.js 파일
  
  import axios from 'axios';
  import store from '../vuex/store';
  
  // axios 하위 .js에서 import 하여 활용
  const instance = axios.create({
    baseURL: 'http://localhost:8000/',
  });
  
  instance.interceptors.request.use(function(config) {
     //ex| header에 토큰 기술 
  });
  isntance.interceptors.response.use(function(response){
     //error처리, 토큰 갱신 처리 등 
  });
  
  export default instance;
  ```

- 기능 별 axios .js 파일

  - json 형태의 데이터의 경우 payload 형태로 미리 vuex 내 actions에서 전달

  ```javascript
  //ex| auth.js - 인증 관련 예시
  import http from './http';
  
  //get
  export function duplicate(email) {
    return http.get(`/account/confirm/emailCheck/${email}`);
  }
  
  //post 지양
  export function login(email, password) {
    return http.post('/account/confirm/login', {
      user_email: email,
      user_password: password,
    });
  }
  //post 추천
  export function login(payload) {
    return http.post('/account/confirm/login', payload);
  }
  
  //put
  export function putPassword(payloard) {
    return http.put('/account/user/password', payloard);
  }
  ```

## vuex convention

- store폴더 내에서 관리

- mutations의 경우 대문자로 작성

- 최상위 index.js 내에서는 모든 페이지에서 사용되는 컴포넌트 관리, modules로 분리할 수 있으면 최대한 분리하여 merge request에서 발생하는 충돌 방지

  ```javascript
  //기본 셋팅
  import Vue from 'vue';
  import Vuex from 'vuex';
  
  //module import 
  import main from './main';
  import recommendation from './recommendation';
  ...
  
  Vue.use(Vuex);
  
  const state = {
      spinnerActive: false,
  }
  const getters = {
      //state에서 추가적인 작업 후 return
  }
  const actions = {
      //async await 사용
  }
  const mudations = {
      SET_SPINNER_TOGGLE(state){
          state.spinnerActive = !state.spinnerActive;
      },
  }
  
  
  export default new Vuex.Store({
      state,
      getters,
      actions,
      mutations,
      modules: {
          main,
          recommendation,
      },
  });
  ```

  

- api와 1 대 1 대응으로 vuex module 분할하여 merge 충돌 방지

- store>modules> 내에 생성

  ```javascript
  import * as calendarApi from '@/api/calendar';
  const INIT_EVENT = () => {
    return {
      //state 초기화
    };
  };
  
  const state = {
      event: INIT_EVENT(),
  }
  const getters = {
      getEventById: (state) => (id) => {
        return state.events.find((event) => event.id === id);
      },
  }
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
  }
  const mutations = {
      SET_CALENDAR_LIST(state, payload) {
        state.events = [...payload];
      },
  }
  
  export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
  }
  
  //부가적인 기능 함수
  const getEventsTransfer = (arr) => {};
  ```

  

- state 초기화는 arrow 함수 활용

  ```javascript
  //initialized
  const INIT_USER = () => {
    return {
      token: localStorage.getItem('token'),
      userId: localStorage.getItem('userId'),
      nickname: localStorage.getItem('nickname'),
    };
  };
  
  export default {
      state:{
          user: INIT_USER(),
      },
  }
  ```

  

## router convention

- 예외 redirect 처리 

  ```javascript
  import Main from './views/main/Main.vue';
  import Login from './views/user/Login.vue';
  
  //로그인 중이면 로그인 페이지 방문시 이전 라우터로 이동
  const redirectBefore = () => (to, from, next) => {
    //   const beforeRoute = from.path;
    if (store.getters.getToken != null) {
      return next(from);
    } else {
      return next();
    }
  };
  
  export default[
    // 유저 관련 영역
    {
      path: '/login',
      name: 'Login',
      component: Login,
      beforeEnter: redirectBefore(),
    },
  ]
  ```

  

