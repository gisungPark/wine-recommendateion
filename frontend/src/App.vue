<template>
  <div id="app">
    <Nav id="navigation" />
    <GuideBtn id="guide-btn" />
    <LoginModal />
    <JoinModal />
    <router-view id="router-view" />
  </div>
</template>

<script>
import './assets/css/static/style.css';
import Nav from './components/static/Nav.vue';
import GuideBtn from './components/static/GuideBtn.vue';
import LoginModal from './components/static/user/Login.vue';
import JoinModal from './components/static/user/Join.vue';

export default {
  name: 'App',
  components: {
    Nav,
    GuideBtn,
    LoginModal,
    JoinModal,
  },
  date() {
    return {};
  },
  created() {},
  mounted() {},
  computed: {},
  watch: {},
  methods: {},
};
</script>

<style>
body {
  background-color: var(--basic-color-bg);
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
  width: 100%;
  position: fixed;
  bottom: 0px;
  z-index: 10;
}

#router-view {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 0;
}
</style>
