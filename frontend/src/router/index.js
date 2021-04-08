import Vue from 'vue';
import Router from 'vue-router';
import store from '../store';

// Routes
import paths from './paths';

function route(path, view, name) {
  return {
    name: name || view,
    path,
    component: (resolve) => import(`@/views/${view}.vue`).then(resolve),
  };
}

Vue.use(Router);

// Create a new router
const router = new Router({
  mode: 'history',
  routes: paths.map((path) => route(path.path, path.view, path.name)).concat([{ path: '*', redirect: '/' }]),
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    }
    if (to.hash) {
      return { selector: to.hash };
    }
    return { x: 0, y: 0 };
  },
});

router.beforeEach((to, from, next) => {
  console.log(to);
  if (to.name === 'Mypage') {
    if (store.state.userInfo.userInfo.token != null) {
      return next();
    } else {
      alert('로그인 후 이용하실 수 있습니다.');
      return next('/');
    }
  } else {
    return next();
  }
});

export default router;
