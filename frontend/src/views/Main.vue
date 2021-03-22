<template>
  <div id="wrap">
    <img src="../assets/images/wine.png" alt="오늘의 와인 이미지" id="main-wine-img" />
    <section id="main-section1">
      <div class="marquee-container tilt">
        <div class="marquee">
          <span class="title wine-title b-desc-e">
            {{ title }}
          </span>
          <span class="title wine-title b-desc-e">
            {{ title }}
          </span>
        </div>
      </div>
      <span class="title wine-title-sub b-desc-e tilt">RODA</span>
    </section>
    <section id="main-section2"></section>
    <section id="main-section3"></section>
    <section id="main-section4"></section>
  </div>
</template>

<script>
export default {
  name: 'Main',
  data() {
    return {
      title: `TODAY'S WINE`,
    };
  },
};
</script>

<style scoped>
#main-section1 {
  background-color: var(--basic-color-bg);
}
#main-section2 {
  background-color: var(--basic-color-bg2);
}
#main-section3 {
  background-color: var(--basic-color-bg3);
}
#main-section4 {
  background-color: var(--basic-color-bg4);
}

section {
  width: 100vw;
  height: 100vh;
}

/* TODO: section 1 영역 */
/* FIXME:  */
#main-wine-img {
  position: fixed;
  z-index: 5;
  filter: drop-shadow(5px 5px 12px rgb(0, 0, 0));
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: auto;
  height: auto;
  max-height: 80vh;
}
.stop {
}

/* TODO: section 1 영역 */
/* FIXME:  */
.marquee-container {
  position: absolute;
  top: 38vh;
  opacity: 0.8;
  white-space: nowrap;
}
.title {
  display: inline-block;
  color: var(--basic-color-key);
  white-space: nowrap;
}
.wine-title {
  /* transform: translate(0, -23vh); */
  font-size: 30vh;
  line-height: 30vh;
  padding-left: 200px;
}
.wine-title-sub {
  position: absolute;
  top: 55%;
  left: 50%;
  margin-left: 30px;
  font-size: 10vh;
  text-shadow: 0 0 1px rgba(225, 169, 87, 0.644), 0 0 2px rgba(225, 169, 87, 0.747), 0 0 4px rgba(225, 169, 87, 0.548), 0 0 12px rgba(225, 169, 87, 0.541) !important;
}
.tilt {
  transform: rotate(3deg);
}
</style>
