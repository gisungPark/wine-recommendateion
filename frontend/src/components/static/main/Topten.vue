<template>
  <div class="topten-frame">
    <!-- <div class="topten-img" :style="{ backgroundImage: `url(${wine.img})` }"></div> -->
    <div class="topten-img"></div>
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
export default {
  name: 'Topten',
  props: {
    wine: {
      type: Object,
      default: () => {
        return {
          type: '레드',
          img: ``,
          ename: 'Hardys, Nottage Hill Cabernet Shiraz',
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
};
</script>

<style>
.topten-frame {
  position: absolute;
  top: 30%;
  width: 100%;
  height: 100%;
  transition: top 0.5s, transform 0.5s ease;
}
.topten-frame:hover {
  top: 0;
  transform: scale(1.2);
}
.topten-frame:hover .type {
  left: 13%;
}
.topten-frame:hover .name {
  bottom: 40%;
}
.topten-img {
  width: 110%;
  height: 100%;
  background-image: url('../../../assets/images/wine.png');
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
  background: linear-gradient(90deg, rgba(0, 0, 0, 1) 0%, rgba(0, 0, 0, 1) 25%, rgba(0, 0, 0, 0) 50%, rgba(0, 0, 0, 1) 75%, rgba(0, 0, 0, 1) 100%);
  opacity: 1;
  transition: opacity 0.5s ease;
}
.topten-img-fade:hover {
  opacity: 0.5;
}

.type {
  position: absolute;
  bottom: 70%;
  left: 0;
  z-index: -1;
  transform: rotate(270deg);
  display: flex;
  transition: left 0.5s ease;
}
.indicator {
  width: 2rem;
  height: 2rem;
  border-radius: 1rem;
}
.type span {
  color: white;
  font-size: 2rem;
  margin-top: 0.3rem;
  margin-left: 1rem;
}
.name {
  position: absolute;
  bottom: 70%;
  left: -5%;
  width: 70vh;
  text-align: end;
  z-index: 1;
  font-size: 2rem;
  color: var(--basic-color-fill);
  transform: rotate(270deg);
  transition: bottom 0.5s ease;
}
</style>
