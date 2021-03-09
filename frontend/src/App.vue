<template>
  <v-app id="app">
    <route-view />
  </v-app>
</template>

<script>
import './assets/css/static/style.css';

export default {
  name: 'app',
  components: {
    Drawer,
    Toolbar,
    RouteView,
    GoTop,
  },
  date() {
    return {};
  },
  created() {},
};
</script>
