<template>
  <div id="coantainer">
    <section id="header">
      <div class="menu" @click="clickedMenu">
        <div class="menu-icon">
          <div class="line line-top" :class="{ cross1: navActive, pop2: navActive }"></div>
          <div class="line line-bottom" :class="{ cross2: navActive, pop2: navActive }"></div>
        </div>
        <p class="l-desc no-drag" :class="{ move: navActive, pop2: navActive }">MENU</p>
      </div>
      <div class="logo">
        <h1 id="title">Your Wine</h1>
        <img src="../../assets/images/logo_w.png" alt="logo image" @click="clickedLogo" />
      </div>
    </section>

    <section id="nav-frame" class="real-shadow-box" :class="{ pop: navActive }">
      <ul id="menu-list">
        <li class="menu-list-item b-desc-e no-drag" @click="clickedRcomm">Recommendation</li>
        <li class="menu-list-item b-desc-e no-drag" @click="clickedWines">Wines</li>
        <li class="menu-list-item b-desc-e no-drag" @click="clickedTopic">Topic</li>
        <li class="menu-list-item b-desc-e no-drag" @click="clickedChart">Chart</li>
      </ul>
    </section>
  </div>
</template>

<script>
import { mapMutations, mapState } from 'vuex';

export default {
  name: 'Nav',
  data() {
    return {};
  },
  computed: {
    ...mapState('nav', ['navActive']),
  },
  methods: {
    ...mapMutations('nav', ['SET_NAV_TOGGLE']),
    clickedMenu() {
      this.SET_NAV_TOGGLE();
    },
    clickedRcomm() {
      this.SET_NAV_TOGGLE();
      alert(`clicked!`);
    },
    clickedWines() {
      this.SET_NAV_TOGGLE();
      alert(`clicked!`);
    },
    clickedTopic() {
      this.SET_NAV_TOGGLE();
      alert(`clicked!`);
    },
    clickedChart() {
      this.SET_NAV_TOGGLE();
      alert(`clicked!`);
    },
    // 메인 페이지로 이동
    clickedLogo() {
      this.$router.push({ name: 'Main' });
    },
  },
};
</script>

<style scoped>
#coantainer {
  width: 100%;
}
/* TODO: 기본 프레임 */
/* FIXME:  */
#header {
  position: absolute;
  z-index: 12;
}
.menu {
  display: flex;
  margin: 2rem;
  height: 2rem;
  cursor: pointer;
  transition: border 0.3s ease;
}
.menu:hover .l-desc {
  color: var(--basic-color-fill);
}
.menu:hover .line {
  background-color: var(--basic-color-fill);
}

/* TODO: 네비바 메뉴 상태에 따라 변경되는 막대  */
/* FIXME:  */
.menu-icon {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.line {
  width: 1.7rem;
  height: 1px;
  margin: 6px;
  background-color: var(--basic-color-key);
  transition: transform 0.3s, background-color 0.3s ease;
  transform: rotate(0);
}
.line-top {
  transform-origin: left top;
}
.line-bottom {
  transform-origin: bottom left;
}
.cross1 {
  transform: rotate(45deg);
}
.cross2 {
  transform: rotate(-45deg);
}

/* TODO: 메뉴 텍스트 */
/* FIXME:  */
.menu p {
  margin: 0;
  padding: 0;
  height: 100%;
  vertical-align: middle;
  font-size: 1.4rem;
  font-weight: 300;
  line-height: 1.8rem;
  display: inline-block;
  text-align: center;
  color: var(--basic-color-key);
  background-color: transparent;
  transition: transform 0.3s, color 0.3s ease;
}
.move {
  transform: translate(-10px, 0);
}

/* TODO: 로고 영역 */
/* FIXME:  */
#title {
  height: 0;
  visibility: hidden;
  cursor: pointer;
}
.logo {
  position: absolute;
  top: 0%;
  margin-top: 2rem;
  left: 50vw;
  transform: translate(-50%, 0);
}
.logo img {
  cursor: pointer;
  width: auto;
  height: auto;
  max-height: 2rem;
  transition: transform 0.3s ease;
}
.logo img:hover {
  transform: scale(1.1);
}

/* TODO: 메뉴 리스트 영역 */
/* FIXME:  */
#nav-frame {
  position: absolute;
  z-index: 10;
  top: 0;
  left: 0;
  height: 100vh;
  width: auto;
  border-right: solid 1px var(--basic-color-key);
  background-color: rgba(255, 255, 255, 0.728);
  transform: translate(-100%, 0);
  transition: transform 0.3s cubic-bezier(0, 1, 0.65, 1);
}
.pop {
  transform: translate(0, 0) !important;
}
/* pop될 때 메뉴 x 버튼과 글자 색 변경 */
.pop2 {
  color: var(--basic-color-fill) !important;
  background-color: var(--basic-color-fill);
}
#menu-list {
  margin-top: 6rem;
  padding: 0 2rem;
}
li {
  color: var(--basic-color-fill);
  font-size: 10vw;
  cursor: pointer;
  transition: color 0.6s, transform 0.3s ease;
}
li:hover {
  color: var(--basic-color-key);
  transform: scale(1.01);
}
</style>
