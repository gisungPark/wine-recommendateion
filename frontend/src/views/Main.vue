<template>
  <div>
    <h1>{{ message }}</h1>
    <div id="wrap">
      <h1>hello</h1>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Main',
  data() {
    return {
      message: 'main',
    };
  },
};
</script>

<style scoped>
div {
  background-color: rgba(255, 0, 0, 0.295);
}
#wrap {
  width: 100%;
  height: 2000px;
  background-color: green;
}
</style>
