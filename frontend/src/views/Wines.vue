<template>
  <div id="wines">
    <transition name="slide">
      <div id="sidebar">
        <div class="sidebar-container">
          <span class="nav-title b-desc">Wines</span>
          <div class="nav-btn b-desc">
            <button
              :class="{ active: contentState === 1 }"
              @click="clickedBtn(1)"
            >
              <span>price</span>
            </button>
            <button
              :class="{ active: contentState === 2 }"
              @click="clickedBtn(2)"
            >
              <span>Point</span>
            </button>
            <button
              :class="{ active: contentState === 3 }"
              @click="clickedBtn(3)"
            >
              <span>Red Wine</span>
            </button>
            <button
              :class="{ active: contentState === 4 }"
              @click="clickedBtn(4)"
            >
              <span>White Wine</span>
            </button>
          </div>
        </div>
      </div>
    </transition>
    <section class="content">
      <div class="search">
        <input
          id="searchInput"
          type="text"
          placeholder="search your wine"
          v-model="keyword"
          @keyup.enter="onSearch"
        />
        <div class="search-icon-wrap" @click="onSearch">
          <svg
            id="search-icon"
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
          >
            <path
              d="M23.832 19.641l-6.821-6.821c2.834-5.878-1.45-12.82-8.065-12.82-4.932 0-8.946 4.014-8.946 8.947 0 6.508 6.739 10.798 12.601 8.166l6.879 6.879c1.957.164 4.52-2.326 4.352-4.351zm-14.886-4.721c-3.293 0-5.973-2.68-5.973-5.973s2.68-5.973 5.973-5.973c3.294 0 5.974 2.68 5.974 5.973s-2.68 5.973-5.974 5.973z"
            />
          </svg>
        </div>
      </div>

      <transition-group name="slideup" mode="out-in">
        <!-- <Winelist class="winelist" key="0" :wines="wines0" v-if="contentState === 0" />
        <Winelist class="winelist" key="1" :wines="wines1" v-if="contentState === 1" />
        <Winelist class="winelist" key="2" :wines="wines1" v-if="contentState === 2" />
        <Winelist class="winelist" key="3" :wines="wines2" v-if="contentState === 3" /> -->
      </transition-group>
    </section>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import Winelist from "@/components/articles/Winelist.vue";

export default {
  name: "Wines",
  components: {
    Winelist,
  },

  data: () => ({
    contentState: 1,
    keyword: "",
  }),
  computed: {
    ...mapState("userInfo", ["userInfo"]),
    ...mapState("nav", ["navActive"]),
  },
  methods: {
    clickedBtn(index) {
      this.contentState = index;
    },
    onSearch() {
      alert(this.keyword + " 를 검색하셨습니다.");
    },
  },
};
</script>

<style scoped>
/* 트랜지션 */
.slideup-leave-active {
  transition: transform 0.5s cubic-bezier(0, 1, 0.65, 1);
}
.slideup-enter-active {
  transition: transform 0.5s cubic-bezier(0, 1, 0.65, 1) 0.5s;
}
.slideup-enter,
.slideup-leave-to {
  transform: translate(0, 100%);
}
.slideup-enter-to,
.slideup-leave {
  transform: translate(0, 0);
}

#wines {
  width: 100vw;
  display: flex;
  flex-direction: row;
}
.content {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  height: 100%;
  padding-left: 172px;
  padding-top: 100px;
}
.search {
  width: 75%;
  height: 5rem;
  display: flex;
  justify-content: center;
  background-color: rgba(0, 0, 0, 0.488);
}
#searchInput {
  width: 85%;
  font-size: 22px;
  color: white;
  border: 1px solid white;
  border-top-width: 0px;
  border-left-width: 0px;
  border-right-width: 0px;
  border-bottom-width: 1px;
}
.search-icon-wrap {
  display: flex;
  align-items: flex-end;
}

#search-icon {
  fill: white;
  width: 50px;
  height: 50px;
  margin-bottom: 5px;
}
/* #################################################### */
/* 사이드 바 */
#sidebar {
  width: 172px;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  border-right: 1px solid var(--basic-color-key);
  background-color: var(--basic-color-fill2);
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

#btnLogo {
  width: 12px;
  height: 12px;
  background-color: var(--basic-color-bg2);
  border-radius: 70%;
  position: relative;
  top: 10px;
  left: 1px;
}

/* #################################################### */
</style>
