<template>
  <div id="coantainer">
    <section id="header">
      <div class="menu" @click="clickedMenu">
        <div class="menu-icon">
          <div class="line line-top" :class="{ cross1: navActive, pop2: navActive }"></div>
          <div class="line line-bottom" :class="{ cross2: navActive, pop2: navActive }"></div>
        </div>
        <p class="l-desc no-drag" :class="{ move: navActive, pop2: navActive }">
          MENU
        </p>
      </div>
      <div class="logo" :class="{ left: logoState }">
        <h1 id="title">Your Wine</h1>
        <img src="../../assets/images/logo_w.png" alt="logo image" @click="clickedLogo" />
      </div>
    </section>

    <section id="nav-frame" class="real-shadow-box" :class="{ pop: navActive, change: changeState, hidden: hiddenState }" @click="clickedMenuFrame">
      <ul id="menu-list">
        <li class="menu-list-item b-desc-e no-drag" @click.stop="clickedRcomm">
          Recommendation
        </li>
        <li class="menu-list-item b-desc-e no-drag" @click.stop="clickedWines">
          Wines
        </li>
        <li class="menu-list-item b-desc-e no-drag" @click.stop="clickedTopic">
          Topic
        </li>
        <li class="menu-list-item b-desc-e no-drag" @click.stop="clickedChart">
          Chart
        </li>
      </ul>
      <div class="source">
        wine21.com의 데이터를 활용하여 개발하였습니다.
      </div>
    </section>
    <div class="bg-close" :class="{ block: navActive }" @click="clickedBackground"></div>
  </div>
</template>

<script>
import { mapMutations, mapState } from 'vuex';

export default {
  name: 'Nav',
  data() {
    return {
      routeWithoutSidebar: ['Main'],
      routeWithSidebar: ['Mypage', 'Recommendation'],
      logoState: false, //false => center, true => 사이드바 영역만큼 오른쪽으로 치우침
      // router 전환 상태 관리
      changeState: false,
      hiddenState: false,
    };
  },
  computed: {
    ...mapState('nav', ['navActive']),
  },

  watch: {
    $route: {
      // 초기화가 되는 첫 순간에서도 아래 handler한 번 실행
      immediate: true,
      handler(to, from) {
        let currentRouteName = to.name;
        this.logoState = false;
        this.routeWithSidebar.forEach((element) => {
          if (element === currentRouteName) {
            this.logoState = true;
            return;
          }
        });
      },
    },
  },
  methods: {
    ...mapMutations('nav', ['SET_NAV_TOGGLE', 'SET_NAV_CLOSE']),
    menuTransition() {
      this.changeState = true;
      setTimeout(() => {
        this.hiddenState = true;
      }, 400);
      setTimeout(() => {
        this.changeState = false;
        this.SET_NAV_CLOSE();
      }, 500);
    },
    clickedMenu() {
      this.hiddenState = false;
      this.SET_NAV_TOGGLE();
    },
    clickedRcomm() {
      this.menuTransition();
      this.$router.push({ name: 'Recommendation' });
    },
    clickedWines() {
      this.menuTransition();
      this.$router.push({ name: 'Wines' });
    },
    clickedTopic() {
      this.menuTransition();
      this.$router.push({ name: 'Topic' });
    },
    clickedChart() {
      this.menuTransition();
      this.$router.push({ name: 'Recommendation' });
    },
    // 메인 페이지로 이동
    clickedLogo() {
      this.$router.push({ name: 'Main' });
    },
    // 배경 클릭시 메뉴 닫기
    clickedBackground() {
      this.SET_NAV_CLOSE();
    },
    clickedMenuFrame() {
      this.SET_NAV_CLOSE();
    },
  },
};
</script>

<style scoped>
.source {
  margin: 0 auto;
  position: absolute;
  font-weight: 700;
  left: 50%;
  bottom: 3rem;
  color: #fff;
  transform: translateX(-50%);
}

.change {
  padding-left: 100%;
  background-color: var(--basic-color-bg) !important;
  backdrop-filter: saturate(0%) blur(0px);
}

#coantainer {
  width: 100%;
}
.bg-close {
  display: none;
  position: absolute;
  width: 100vw;
  height: 100vh;
  z-index: -1;
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
  background: grays;
  padding: 1rem;
  position: absolute;
  top: 0%;
  margin-top: 1rem;
  left: 50vw;
  transform: translate(-50%, 0);
  transition: left 0.3s ease;
}
.logo img {
  cursor: pointer;
  width: auto;
  height: auto;
  max-height: 2rem;
  transition: transform 0.3s;
  backdrop-filter: blur(5px);
  border-radius: 1rem;
}
.logo img:hover {
  transform: scale(1.1);
}
.left {
  left: calc(50vw + 86px);
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
  box-sizing: border-box;
  background-color: rgba(100, 100, 100, 0.5);
  transform: translate(-100%, 0);
  transition: padding-left 0.5s, background-color 0.5s, backdrop-filter 0.5s, transform 0.5s cubic-bezier(0, 1, 0.65, 1);

  -webkit-backdrop-filter: saturate(180%) blur(100px);
  -moz-backdrop-filter: saturate(180%) blur(100px);
  -o-backdrop-filter: saturate(180%) blur(100px);
  backdrop-filter: saturate(180%) blur(100px);
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
  font-size: 9vw;
  cursor: pointer;
  transition: color 0.5s, transform 0.3s ease;
}
li:hover {
  color: var(--basic-color-key);
  transform: scale(1.01);
}
</style>
