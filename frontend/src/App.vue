<template>
  <div id="app">
    <Nav id="navigation" />
    <GuideBtn id="guide-btn" />
    <LoginModal id="login-modal" />
    <transition name="opacity">
      <router-view id="router-view" ref="routerView" :key="$route.path" />
    </transition>
  </div>
</template>

<script>
import './assets/css/static/style.css';
import Nav from './components/static/Nav.vue';
import GuideBtn from './components/static/GuideBtn.vue';
import LoginModal from './components/static/user/Login.vue';

// import * as scroll from '@/assets/js/scroll.js';

export default {
  name: 'App',
  components: {
    Nav,
    GuideBtn,
    LoginModal,
  },
  date() {
    return {
      transitionName: 'slide',
    };
  },
  created() {},
  mounted() {
    // scroll.smoothScroll(document.querySelector('#router-view'));
    // scroll.smoothScroll(this.$refs.router);
    // scroll.semi(document);
  },
  computed: {},
  watch: {
    // $route(to, from) {
    //   const toDepth = to.path.split('/').length;
    //   const fromDepth = from.path.split('/').length;
    //   this.transitionName = toDepth < fromDepth ? 'slide-right' : 'slide-left';
    // },
  },
  methods: {},
};
</script>

<style>
body {
  background-color: var(--basic-color-bg);
  scroll-behavior: smooth;
}

.opacity-enter-active {
  transition: opacity 0.5s cubic-bezier(0, 1, 0.65, 1) 0.5s;
}
.opacity-leave-active {
  transition: opacity 0.5s cubic-bezier(0, 1, 0.65, 1);
}
.opacity-enter,
.opacity-leave-to {
  opacity: 0;
}
.opacity-enter-to,
.opacity-leave {
  opacity: 1;
}

.fade-enter-active,
.fade-leave-active {
  transition: all 0.3s ease;
}
.fade-enter,
.fade-leave-to {
  transform: scale(0.9);
  opacity: 0;
}
.fade-enter-to,
.fade-leave {
  transform: scale(1);
  opacity: 1;
}

.scale-enter-active {
  transition: all 0.3s cubic-bezier(0, 1, 0.65, 1) 0.3s;
}
.scale-leave-active {
  transition: all 0.3s cubic-bezier(0, 1, 0.65, 1);
}
.scale-enter,
.scale-leave-to {
  transform: scale(0.3);
  opacity: 0;
}
.scale-enter-to,
.scale-leave {
  transform: scale(1);
  opacity: 1;
}
</style>

<style scoped>
#navigation {
  position: fixed;
  width: 100%;
  z-index: 10;
  top: 0;
  left: 0;
}
#guide-btn {
  position: fixed;
  width: 100%;
  bottom: 0;
  pointer-events: none;
  z-index: 10;
}
#login-modal {
  position: absolute;
}
#router-view {
  position: relative;
  width: 100%;
  overflow-x: hidden;
  top: 0;
  left: 0;
  z-index: 0;
}
</style>
