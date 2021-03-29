<template>
  <div id="app">
    <Nav id="navigation" />
    <GuideBtn id="guide-btn" />
    <LoginModal />
    <JoinModal />
    <transition name="opacity">
      <router-view id="router-view" />
    </transition>
  </div>
</template>

<script>
import "./assets/css/static/style.css";
import Nav from "./components/static/Nav.vue";
import GuideBtn from "./components/static/GuideBtn.vue";
import LoginModal from "./components/static/user/Login.vue";

export default {
  name: "App",
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
  mounted() {},
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

#router-view {
  position: absolute;
  width: 100%;
  overflow-x: hidden;
  top: 0;
  left: 0;
  z-index: 0;
}
</style>
