<template>
  <div id="app">
    <GuideBtn id="guide-btn" />
    <LoginModal />
    <JoinModal />
    <router-view id="router-view" />
  </div>
</template>

<script>
import "./assets/css/static/style.css";
import GuideBtn from "./components/static/GuideBtn.vue";
import LoginModal from "./components/static/Login.vue";
import JoinModal from "./components/static/Join.vue";

export default {
  name: "App",
  components: {
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
#guide-btn {
  width: 100%;
  position: fixed;
  bottom: 0px;
}

#router-view {
  position: absolute;
  top: 0;
  left: 0;
}
</style>
