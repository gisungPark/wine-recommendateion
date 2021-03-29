<template>
  <div>
    <div class="card">
      <div class="img" :style="{ 'background-image': `url(${item.img})` }"></div>
      <div class="name-container">
        <p class="m-desc">{{ item.name }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Card',
  props: {
    item: {
      type: Object,
      default: function() {
        return {
          flavor_id: -1,
          name: 'empty',
          img: 'empty',
          isLike: false,
          isHate: false,
        };
      },
    },
  },
  data() {
    return {};
  },
  mounted() {},
};
</script>

<style scoped>
.card {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  width: 180px;
  height: 280px;
  padding: 10px;
  margin: 0 auto;
  padding: 8px;
  border-radius: 12px;
  background-color: #ebe3d7;
  cursor: pointer;
}
.img {
  width: 100%;
  height: 200px;
  border-radius: 10px;
  background-color: #dcd0c2;
  background-position: center;
  background-size: contain;
}
.name-container {
  width: 100%;
  height: 60px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.name-container p {
  font-size: 2rem;
}
</style>
