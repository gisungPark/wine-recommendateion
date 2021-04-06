import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';

//추가 라이브러리
import vuetify from './plugins/vuetify'; //vuetify
// import infiniteScroll from 'vue-infinite-scroll'; //infinity Scroll
// Vue.use(infiniteScroll);
Vue.config.productionTip = false;
//https://www.npmjs.com/package/vue-moment
//[moment 사용법]: https://flamingotiger.github.io/javascript/momentjs/
Vue.use(require('vue-moment')); //moment

import VueToast from 'vue-toast-notification';
// Import one of the available themes
//import 'vue-toast-notification/dist/theme-default.css';
import 'vue-toast-notification/dist/theme-sugar.css';

Vue.use(VueToast, {
  position: 'top-right',
  duration: 2000,
  //message: msg,
  //typs: 'default'
  // duration: 1000,
  // position: 'bottom-right',
  // dismissible: true,
  // onClick: function() {
  //   alert('alert');
  // },
  // onDismiss: function() {
  //   alert('dismissed!');
  // },
  // queue: false,
  // pauseOnHover: true,
});

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount('#app');
