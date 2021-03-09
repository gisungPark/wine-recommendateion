import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';

//추가 라이브러리
import vuetify from './plugins/vuetify';
import infiniteScroll from 'vue-infinite-scroll';

Vue.config.productionTip = false;
Vue.use(infiniteScroll);
//https://www.npmjs.com/package/vue-moment
//[moment 사용법]: https://flamingotiger.github.io/javascript/momentjs/
Vue.use(require('vue-moment'));

new Vue({
  vuetify,
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
