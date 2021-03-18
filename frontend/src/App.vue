<template>
  <div id="app">
    <GuideBtn id="guide-btn" />
    <LoginDialog />
    <router-view />
  </div>
</template>

<script>
import './assets/css/static/style.css';
import GuideBtn from './components/static/GuideBtn.vue';
import LoginDialog from './components/static/LoginDialog.vue;';

export default {
  name: 'App',
  components: {
    GuideBtn,
    LoginDialog,
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
</style>
