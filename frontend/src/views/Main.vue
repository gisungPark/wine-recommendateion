<template>
  <div>
    <h1>{{ message }}</h1>
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
  background-color: red;
}
</style>
