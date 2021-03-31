<template>
  <div id="topic">
    <transition name="slide">
      <div id="sidebar">
        <div class="sidebar-container">
          <span class="nav-title b-desc">Wines</span>
          <div class="nav-btn b-desc">
            <button @click="clickedBtn(1)">
              <span>price</span>
            </button>
            <button @click="clickedBtn(2)">
              <span>Point</span>
            </button>
            <button @click="clickedBtn(3)">
              <span>Wine Kind</span>
            </button>
            <button @click="clickedBtn(4)">
              <span>Grape</span>
            </button>
          </div>
        </div>
      </div>
    </transition>
    <div class="content">
      <div
        id="topic-wrap"
        v-for="(item, index) in topics"
        :key="item + index"
        @click="onClickTipic(item)"
      >
        <div id="topic-category">{{ item.category }}</div>
        <div id="topic-title">{{ item.title }}</div>
        <div v-show="item.flag === true" id="topic-content-wrap">
          {{ item.content }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import Winelist from "@/components/articles/Winelist.vue";

export default {
  name: "Topic",
  components: {
    Winelist,
  },

  data: () => ({
    contentState: -1,
    topics: [
      {
        category: "Tip",
        title: "와인을 즐기는 10가지 팁!!",
        content: "",
        flag: false,
      },
      {
        category: "Video",
        title: "와인 용어 총정리",
        content: `Senectus et netus et malesuada. Nunc pulvinar sapien et ligula ullamcorper malesuada proin. Neque convallis a cras semper auctor. Libero id faucibus nisl tincidunt eget. Leo a diam sollicitudin tempor id. A lacus vestibulum sed arcu non odio euismod lacinia. In tellus integer feugiat scelerisque. Feugiat in fermentum posuere urna nec tincidunt praesent. Porttitor rhoncus dolor purus non enim praesent elementum facilisis. Nisi scelerisque eu ultrices vitae auctor eu augue ut lectus. Ipsum faucibus vitae aliquet nec ullamcorper sit amet risus. Et malesuada fames ac turpis egestas sed. Sit amet nisl suscipit adipiscing bibendum est ultricies. Arcu ac tortor dignissim convallis aenean et tortor at. Pretium viverra suspendisse potenti nullam ac tortor vitae purus. Eros donec ac odio tempor orci dapibus ultrices. Elementum nibh tellus molestie nunc. Et magnis dis parturient montes nascetur. Est placerat in egestas erat imperdiet. Consequat interdum varius sit amet mattis vulputate enim.
Sit amet nulla facilisi morbi tempus. Nulla facilisi cras fermentum odio eu. Etiam erat velit scelerisque in dictum non consectetur a erat. Enim nulla aliquet porttitor lacus luctus accumsan tortor posuere. Ut sem nulla pharetra diam. Fames ac turpis egestas maecenas. Bibendum neque egestas congue quisque egestas diam. Laoreet id donec ultrices tincidunt arcu non sodales neque. Eget felis eget nunc lobortis mattis aliquam faucibus purus. Faucibus interdum posuere lorem ipsum dolor sit.`,
        flag: false,
      },
      {
        category: "Tip",
        title: "초보자들을 위한 마트에서 와인 고르는 팁!!!",
        content: "",
        flag: false,
      },
      {
        category: "Tip",
        title: "프로포즈에 어울리는 와인",
        content: `Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nisl tincidunt eget nullam non. Quis hendrerit dolor magna eget est lorem ipsum dolor sit. Volutpat odio facilisis mauris sit amet massa. Commodo odio aenean sed adipiscing diam donec adipiscing tristique. Mi eget mauris pharetra et. Non tellus orci ac auctor augue. Elit at imperdiet dui accumsan sit. Ornare arcu dui vivamus arcu felis. Egestas integer eget aliquet nibh praesent. In hac habitasse platea dictumst quisque sagittis purus. Pulvinar elementum integer enim neque volutpat ac.

Senectus et netus et malesuada. Nunc pulvinar sapien et ligula ullamcorper malesuada proin. Neque convallis a cras semper auctor. Libero id faucibus nisl tincidunt eget. Leo a diam sollicitudin tempor id. A lacus vestibulum sed arcu non odio euismod lacinia. In tellus integer feugiat scelerisque. Feugiat in fermentum posuere urna nec tincidunt praesent. Porttitor rhoncus dolor purus non enim praesent elementum facilisis. Nisi scelerisque eu ultrices vitae auctor eu augue ut lectus. Ipsum faucibus vitae aliquet nec ullamcorper sit amet risus. Et malesuada fames ac turpis egestas sed. Sit amet nisl suscipit adipiscing bibendum est ultricies. Arcu ac tortor dignissim convallis aenean et tortor at. Pretium viverra suspendisse potenti nullam ac tortor vitae purus. Eros donec ac odio tempor orci dapibus ultrices. Elementum nibh tellus molestie nunc. Et magnis dis parturient montes nascetur. Est placerat in egestas erat imperdiet. Consequat interdum varius sit amet mattis vulputate enim.`,
        flag: false,
      },
    ],
  }),
  computed: {
    ...mapState("userInfo", ["userInfo"]),
    ...mapState("nav", ["navActive"]),
  },
  methods: {
    clickedBtn(index) {
      if (this.contentState === index) this.contentState = -1;
      else this.contentState = index;
    },
    onClickTipic(item) {
      console.log("클릭이벤트!!!");
      item.flag = !item.flag;
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

/* end 사이드 바 */
/* #################################################### */

#topic {
  width: 100%;
  height: 100vh;
  display: flex;
  flex-direction: row;
}
.content {
  width: 100%;
  padding-left: 172px;
  padding-top: 100px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

#topic-wrap {
  height: 150px;
  width: 90%;
  margin-bottom: 10px;
  display: flex;
  flex-direction: column;
}
#topic-category {
  font-size: 30px;
  font-weight: 700;
  color: #e1a957;
}
#topic-title {
  font-size: 72px;
  font-weight: 900;
  display: flex;
  flex-wrap: nowrap;
  color: var(--basic-color-fill);
}
#topic-content-wrap {
  color: var(--basic-color-fill);
  display: flex;
}
</style>
