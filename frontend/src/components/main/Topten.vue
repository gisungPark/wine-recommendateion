<template>
  <div class="topten-frame" @click="clickedItem">
    <div class="topten-img" :style="{ 'background-image': `url(${this.s3url}${wine.wineId}.png)` }"></div>
    <div class="topten-img-fade"></div>
    <p>{{ wine.img }}</p>
    <div class="type">
      <div class="indicator" :style="{ 'background-color': `${color}` }"></div>
      <span>{{ type }}</span>
    </div>
    <p class="name">{{ wine.ename }}</p>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'Topten',
  props: {
    wine: {
      type: Object,
      default: () => {
        return {
          type: '',
          img: ``,
          wineId: '',
          ename: '',
        };
      },
    },
  },
  data() {
    return {
      color: '',
      type: '',
    };
  },
  created() {
    switch (this.wine.type) {
      case '로제':
        this.color = '#89b22b';
        this.type = 'ROSE WINE';
        break;
      case '화이트':
        this.color = '#ded483';
        this.type = 'WHITE WINE';
        break;
      default:
        this.color = '#821a33';
        this.type = 'RED WINE';
        break;
    }
  },
  computed: {
    ...mapState(['s3url']),
  },
  methods: {
    clickedItem() {
      this.$router.push(`/detail/${this.wine.wineId}`);
    },
  },
};
</script>

<style>
.topten-frame {
  position: absolute;
  top: 30%;
  width: 100%;
  cursor: pointer;
  height: 100%;

  transition: top 0.5s, transform 0.5s ease;
}
.topten-frame:hover {
  top: 0;
  transform: scale(1.2);
}

.topten-img {
  margin-top: 20px;
  width: 110%;
  height: 100%;
  background-size: contain;
  background-position: center;
  filter: drop-shadow(15px 15px 30px rgb(0, 0, 0));
}
.topten-img-fade {
  position: absolute;
  top: 0;
  width: 100%;
  height: 100%;
  background-size: contain;
  background-position: center;
  /* background: transparent; */
  z-index: 0;
  background: linear-gradient(90deg, rgba(0, 0, 0, 1) 0%, rgba(0, 0, 0, 1) 25%, rgba(0, 0, 0, 0) 50%, rgba(0, 0, 0, 1) 75%, rgba(0, 0, 0, 1) 100%);
  opacity: 1;
  transition: opacity 0.5s ease;
}
.topten-img-fade:hover {
  opacity: 0.5;
}

.type {
  position: relative;
  top: -50vh;
  left: -130px;
  display: flex;
  flex-direction: row;
  z-index: -1;
  transform: rotate(270deg);
  justify-content: flex-end;
  transition: all 0.5s ease;
  pointer-events: none;
}
.topten-frame:hover .type {
  left: -70px;
}
.indicator {
  width: 2rem;
  height: 2rem;
  border-radius: 1rem;
}
.type span {
  color: white;
  white-space: nowrap;
  font-size: 2rem;
  margin-top: 0.3rem;
  margin-left: 1rem;
}
.name {
  position: absolute;
  top: 350px;
  right: -430px;
  white-space: nowrap;
  text-align: end;
  width: 1000px;
  z-index: 1;
  font-size: 2rem;
  transform: rotate(270deg);
  color: var(--basic-color-fill);
  transition: all 0.5s ease;
  pointer-events: none;
}
.topten-frame:hover .name {
  top: calc(380px + 30%);
}
</style>
