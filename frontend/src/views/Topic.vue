<template>
  <div id="topic">
    <transition name="slide">
      <div id="sidebar">
        <div class="sidebar-container">
          <span class="nav-title b-desc">Topic</span>
          <div class="nav-btn b-desc">
            <button
              :class="{ 'nav-btn-active': isBtnClick(1) }"
              @click="clickedBtn(1)"
            >
              <span>All</span>
            </button>
            <button
              :class="{ 'nav-btn-active': isBtnClick(2) }"
              @click="clickedBtn(2)"
            >
              <span>News</span>
            </button>
            <button
              :class="{ 'nav-btn-active': isBtnClick(3) }"
              @click="clickedBtn(3)"
            >
              <span>Tip</span>
            </button>
            <button
              :class="{ 'nav-btn-active': isBtnClick(4) }"
              @click="clickedBtn(4)"
            >
              <span>Grape</span>
            </button>
          </div>
        </div>
      </div>
    </transition>
    <div class="content">
      <div
        class="content-wrap"
        v-show="this.contentState == 1 || this.contentState == 2"
      >
        <!-- ########################################### -->
        <div class="topic-wrap" @click="onClickTipic(news[1])">
          <div class="topic-category">{{ news[1].category }}</div>
          <div class="topic-title">
            {{ news[1].title }}
          </div>
          <div
            class="topic-content-wrap"
            :class="{ 'display-none': !news[1].flag }"
          >
            <News1 />
          </div>
          <hr />
        </div>
        <!-- ########################################### -->
        <div class="topic-wrap" @click="onClickTipic(news[2])">
          <div class="topic-category">{{ news[2].category }}</div>
          <div class="topic-title">
            {{ news[2].title }}
          </div>
          <div
            class="topic-content-wrap"
            :class="{ 'display-none': !news[2].flag }"
          >
            <News2 />
          </div>
          <hr />
        </div>
        <!-- ########################################### -->
        <div class="topic-wrap" @click="onClickTipic(news[3])">
          <div class="topic-category">{{ news[3].category }}</div>
          <div class="topic-title">
            {{ news[3].title }}
          </div>
          <div
            class="topic-content-wrap"
            :class="{ 'display-none': !news[3].flag }"
          >
            <News3 />
          </div>
          <hr />
        </div>
        <!-- ########################################### -->
        <div class="topic-wrap" @click="onClickTipic(news[4])">
          <div class="topic-category">{{ news[4].category }}</div>
          <div class="topic-title">
            {{ news[4].title }}
          </div>
          <div
            class="topic-content-wrap"
            :class="{ 'display-none': !news[4].flag }"
          >
            <News4 />
          </div>
          <hr />
        </div>
        <!-- ########################################### -->
        <div class="topic-wrap" @click="onClickTipic(news[5])">
          <div class="topic-category">{{ news[5].category }}</div>
          <div class="topic-title">
            {{ news[5].title }}
          </div>
          <div
            class="topic-content-wrap"
            :class="{ 'display-none': !news[5].flag }"
          >
            <News5 />
          </div>
          <hr />
        </div>
        <!-- ########################################### -->
        <div class="topic-wrap" @click="onClickTipic(news[6])">
          <div class="topic-category">{{ news[6].category }}</div>
          <div class="topic-title">
            {{ news[6].title }}
          </div>
          <div
            class="topic-content-wrap"
            :class="{ 'display-none': !news[6].flag }"
          >
            <News6 />
          </div>
          <hr />
        </div>
        <!-- ########################################### -->
        <div class="topic-wrap" @click="onClickTipic(news[7])">
          <div class="topic-category">{{ news[7].category }}</div>
          <div class="topic-title">
            {{ news[7].title }}
          </div>
          <div
            class="topic-content-wrap"
            :class="{ 'display-none': !news[7].flag }"
          >
            <News7 />
          </div>
          <hr />
        </div>
        <!-- ########################################### -->
        <div class="topic-wrap" @click="onClickTipic(news[8])">
          <div class="topic-category">{{ news[8].category }}</div>
          <div class="topic-title">
            {{ news[8].title }}
          </div>
          <div
            class="topic-content-wrap"
            :class="{ 'display-none': !news[8].flag }"
          >
            <News8 />
          </div>
          <hr />
        </div>
      </div>
      <!-- ########################################### -->
      <!-- start topic!!!   #################################### -->
      <div
        class="content-wrap"
        v-show="this.contentState == 1 || this.contentState == 3"
      >
        <!-- ########################################### -->
        <div class="topic-wrap" @click="onClickTipic(topics[1])">
          <div class="topic-category">{{ topics[1].category }}</div>
          <div class="topic-title">
            {{ topics[1].title }}
          </div>
          <div
            class="topic-content-wrap"
            :class="{ 'display-none': !topics[1].flag }"
          >
            <Topic1 />
          </div>
          <hr />
        </div>
        <!-- ########################################### -->
        <div class="topic-wrap" @click="onClickTipic(topics[2])">
          <div class="topic-category">{{ topics[2].category }}</div>
          <div class="topic-title">
            {{ topics[2].title }}
          </div>
          <div
            class="topic-content-wrap"
            :class="{ 'display-none': !topics[2].flag }"
          >
            <Topic2 />
          </div>
          <hr />
        </div>
        <!-- ########################################### -->
        <div class="topic-wrap" @click="onClickTipic(topics[3])">
          <div class="topic-category">{{ topics[3].category }}</div>
          <div class="topic-title">
            {{ topics[3].title }}
          </div>
          <div
            class="topic-content-wrap"
            :class="{ 'display-none': !topics[3].flag }"
          >
            <Topic3 />
          </div>
          <hr />
        </div>
      </div>
      <div
        class="content-wrap"
        v-show="this.contentState == 1 || this.contentState == 4"
      >
        <div
          class="topic-wrap"
          v-for="(item, index) in grape"
          :key="item + index"
          @click="onClickTipic(item)"
        >
          <div class="topic-category">grape</div>
          <div class="topic-title">{{ item.ename }}({{ item.kname }})</div>
          <div
            class="topic-content-wrap detail-wrap"
            :class="{ 'display-none': !item.flag }"
          >
            <p>{{ item.detail }}</p>
          </div>
          <hr />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import * as topicApi from "@/api/topic";
import News1 from "@/components/topics/news1.vue";
import News2 from "@/components/topics/news2.vue";
import News3 from "@/components/topics/news3.vue";
import News4 from "@/components/topics/news4.vue";
import News5 from "@/components/topics/news5.vue";
import News6 from "@/components/topics/news6.vue";
import News7 from "@/components/topics/news7.vue";
import News8 from "@/components/topics/news8.vue";
import Topic1 from "@/components/topics/topic1.vue";
import Topic2 from "@/components/topics/topic2.vue";
import Topic3 from "@/components/topics/topic3.vue";

export default {
  name: "Topic",
  components: {
    News1,
    News2,
    News3,
    News4,
    News5,
    News6,
    News7,
    News8,
    Topic1,
    Topic2,
    Topic3,
  },

  created() {
    this.getGrape();
  },
  data: () => ({
    contentState: 1,
    grape: [],
    news: [
      {
        category: "",
        title: "",
        flag: false,
      },
      {
        category: "News",
        title: "와인21닷컴이 선정한 4월 이달의 와인",
        flag: false,
      },
      {
        category: "News",
        title:
          "TV조선 화제의 드라마 '결혼작사 이혼작곡' 속 와인! 라피우마 몬테풀치아노 다부르쪼",
        flag: false,
      },
      {
        category: "News",
        title: "벚꽃 필 무렵엔 이 와인을!",
        flag: false,
      },
      {
        category: "News",
        title: "하이트진로, 여왕의 샴페인 ‘마리 스튜아트’ 3종 출시",
        flag: false,
      },
      {
        category: "News",
        title: "와인의 관상학",
        flag: false,
      },
      {
        category: "News",
        title: "코로나 팬데믹 1년, 그리고 와인",
        flag: false,
      },
      {
        category: "News",
        title: "보틀샤크, 식탁 위에 봄 기운 소환하는 화이트 와인 추천",
        flag: false,
      },
      {
        category: "News",
        title: "봄날, 지금 썸타는 중이라면?",
        flag: false,
      },
    ],
    topics: [
      {
        category: "",
        title: "",
        flag: false,
      },
      {
        category: "Tip",
        title: "와인이란 무엇일까?",
        flag: false,
      },
      {
        category: "Tip",
        title: "와인이 만들어지는 법: 포도밭에서 와인잔까지의 6가지 단계",
        flag: false,
      },
      {
        category: "Tip",
        title: "와인 라벨 읽는 방법 : 초보자 가이드",
        flag: false,
      },
      {
        category: "News",
        title: "하이트진로, 여왕의 샴페인 ‘마리 스튜아트’ 3종 출시",
        flag: false,
      },
      {
        category: "Topic",
        title: "와인의 관상학",
        flag: false,
      },
      {
        category: "Topic",
        title: "코로나 팬데믹 1년, 그리고 와인",
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
      console.log(index + " 클릭 이벤트!!!");
      this.contentState = index;
    },
    onClickTipic(item) {
      console.log("클릭이벤트!!!");
      item.flag = !item.flag;
    },
    isBtnClick(index) {
      if (this.contentState === index) return true;
      else return false;
    },
    getGrapeApi() {
      return topicApi.getGrapeKind();
    },

    async getGrape() {
      try {
        const response = await this.getGrapeApi();
        console.log(response.data);
        for (var i = 0; i < response.data.length; i++) {
          const item = {
            grapeId: response.data[i].grapeId,
            kname: response.data[i].kname,
            ename: response.data[i].ename,
            detail: response.data[i].detail,
            flag: false,
          };
          this.grape.push(item);
        }
      } catch (error) {
        console.log(error);
      }
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
.nav-btn-active {
  border: 1px solid var(--basic-color-key) !important;
}

/* end 사이드 바 */
/* #################################################### */

#topic {
  width: 100%;
  display: flex;
  flex-direction: row;
}
.content {
  width: 100%;
  height: 100%;
  padding-left: 172px;
  padding-top: 100px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.content-wrap {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.topic-wrap {
  width: 90%;
  height: auto;
  margin-bottom: 10px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  overflow-y: hidden; /* 스크롤 중복 문제 해결!! */
  transition: all 0.5s ease;
}

.topic-category {
  font-size: 28px;
  font-weight: 700;
  color: #e1a957;
  position: relative;
  top: 7px;
}
.topic-title {
  font-size: 72px;
  font-weight: 900;
  margin-left: 5px;
  color: var(--basic-color-fill);
}
.topic-content-wrap {
  display: block;
  height: auto;
  color: var(--basic-color-fill);
  transition: all 0.5s ease;
}
.detail-wrap {
  width: 100%;
  height: 100%;
  margin-top: 20px;
  margin-bottom: 15px;
  padding-top: 45px;
  padding-bottom: 50px;
  padding-left: 15px;
  padding-right: 15px;
  background-color: #f1f0f0;
  opacity: 0.8;
}

.detail-wrap > p {
  font-size: 20px;
  line-height: 150%;
  color: #3d3939;
}

.display-none {
  height: 0px;
  display: none !important;
}

hr {
  width: 100%;
  height: 1px;
  color: var(--basic-color-fill); /* IE */
  border-color: var(--basic-color-fill); /* 사파리 */
  background-color: var(
    --basic-color-fill
  ); /* 크롬, 모질라 등, 기타 브라우저 */
  margin-top: 15px;
  margin-left: -70px;
  margin-bottom: 15px;
}
</style>
