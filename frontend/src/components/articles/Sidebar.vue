<template>
  <transition name="slide">
    <div id="sidebar" v-if="show">
      <div class="sidebar-container">
        <span class="nav-title b-desc">{{ sidebar.title }}</span>
        <div class="nav-btn b-desc">
          <button
            v-for="(name, index) in sidebar.names"
            :key="index + name"
            :class="{ active: activeState === index }"
            @click="clickedBtn(index)"
          >
            <span>{{ name }}</span>
          </button>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import common from "@/assets/js/common.js";
export default {
  name: "Sidebar",
  props: {
    sidebar: {
      type: Object,
      default: function () {
        return {
          title: "Error",
          names: ["Error"],
        };
      },
    },
  },
  data() {
    return {
      show: false,
      activeState: 0,
    };
  },

  mounted() {
    this.show = true;
  },
  beforeDestroy() {
    this.show = false;
  },
  methods: {
    clickedBtn(index) {
      this.activeState = index;
      this.emitEvent();
    },
    emitEvent() {
      this.$emit("clickedBtn", this.activeState);
    },
  },
};
</script>

<style scoped>
/* 사이드 바 트랜지션 */
.slide-enter-active {
  transition: transform 0.5s cubic-bezier(0, 1, 0.65, 1) 0.5s;
}
.slide-leave-active {
  transition: transform 0.5s cubic-bezier(0, 1, 0.65, 1);
}
.slide-enter,
.slide-leave-to {
  transform: translate(-100%, 0);
}
.slide-enter-to,
.slide-leave {
  transform: translate(0, 0);
}
.sidebar-container {
  position: relative;
  width: 95vh;
  height: 58px;
  transform: rotate(-90deg) translate(-102%, 100%);
  transform-origin: top left;
}
.nav-title {
  color: var(--basic-color-fill);
  font-size: 3rem;
}
.nav-btn {
  padding-top: 1rem;
}
.nav-btn button {
  width: 120px;
  height: 2rem;
  line-height: 2rem;
  font-size: 1rem;
  margin-right: 1rem;
  color: #a3a1a1;
  border-radius: 1rem;
  border: 1px solid var(--basic-color-bg);
  background: var(--basic-color-bg);
  transition: all 0.3s ease;
}
.nav-btn button:hover,
.nav-btn button:active {
  background-color: #e1a95744;
  border: 1px solid var(--basic-color-key);
}
.nav-btn button > span {
  position: relative;
  top: -0.15em;
}
.active {
  border: 1px solid var(--basic-color-key) !important;
}
/* 사이드 바 */
#sidebar {
  width: 172px;
  height: 100vh;
  border-right: 1px solid var(--basic-color-key);
  background-color: var(--basic-color-fill2);
}
</style>
