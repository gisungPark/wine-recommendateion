<template>
  <div id="app">
    <Nav id="navigation" />
    <GuideBtn id="guide-btn" />
    <LoginModal />
    <router-view id="router-view" />
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
