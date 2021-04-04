<template>
  <div id="recommendation">
    <button class="temp" @click="temp">add test</button>
    <Sidebar class="sidebar" :sidebar="sidebar" @clickedBtn="changeContent" />
    <section class="content">
      <div class="info">
        임시 영역 || 추천 기반 정보 제공 || 통계자료 확인>
      </div>

      <transition-group name="slideup" mode="out-in">
        <Winelist class="winelist" key="1" :wines="wines1" v-if="contentState === 1" />
        <Winelist class="winelist" key="0" :wines="wines0" v-if="contentState === 0" />
        <Winelist class="winelist" key="2" :wines="wines1" v-if="contentState === 2" />
        <Winelist class="winelist" key="3" :wines="wines2" v-if="contentState === 3" />
      </transition-group>
    </section>
  </div>
</template>

<script>
import { mapState, mapMutations } from 'vuex';
import Sidebar from '@/components/articles/Sidebar.vue';
import Winelist from '@/components/articles/Winelist.vue';

// import * as scroll from '@/assets/js/scroll.js';

export default {
  name: 'Recommendation',
  components: {
    Sidebar,
    Winelist,
  },
  data: () => ({
    contentState: 0,
    sidebar: {
      title: 'Recommendation',
      names: ['Preferences', 'Ratings', 'Pairings', 'Gifts'],
      descBtn: [
        '향과 맛 기반의 선호도 추천입니다.',
        '리뷰 데이터 기반의 평점 추천입니다.',
        '와인의 종류와 맛에 따른 음식 페어링 입니다',
        '대부분의 사람이 만족했던 와인을 찾아드립니다.',
      ],
    },
    wines0: [
      {
        id: 1,
        type: '레드',
        ename: 'wine name wine wine wine winewine name wine wine wine wine',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
        img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
      },
      {
        id: 1,
        type: '화이트',
        ename: 'wine name, wine wine wine wine',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
        img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
      },
      {
        id: 1,
        type: '스파클링',
        ename: 'wine name, wine wine wine wine',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
        img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
      },
      {
        id: 1,
        type: '레드',
        ename: 'wine name, wine wine wine wine',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
        img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
      },
      {
        id: 1,
        type: '레드',
        ename: 'wine name, wine wine wine wine',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
        img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
      },
      {
        id: 1,
        type: '레드',
        ename: 'wine name, wine wine wine wine',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
        img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
      },
      {
        id: 1,
        type: '레드',
        ename: 'wine name',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
        img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
      },
      {
        id: 1,
        type: '레드',
        ename: 'wine name',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
        img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
      },
      {
        id: 1,
        type: '레드',
        ename: '`Morande, Pionero Rose`',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
        img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
      },
    ],
    wines1: [
      {
        id: 1,
        type: '화이트',
        ename: 'wine name',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
        img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
      },
      {
        id: 1,
        type: '로제',
        ename: 'wine name',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
        img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
      },
      {
        id: 1,
        type: '레드',
        ename: 'wine name',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
        img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
      },
      {
        id: 1,
        type: '레드',
        ename: 'wine name',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
        img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
      },
      {
        id: 1,
        type: '레드',
        ename: 'wine name',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
        img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
      },
      {
        id: 1,
        type: '레드',
        ename: 'wine name',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
        img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
      },
      {
        id: 1,
        type: '레드',
        ename: 'wine name',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
        img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
      },
      {
        id: 1,
        type: '레드',
        ename: '`Morande, Pionero Rose`',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
        img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
      },
    ],
  }),
  mounted() {
    // scroll.linear();
  },
  computed: {
    ...mapState('userInfo', ['userInfo']),
    ...mapState('nav', ['navActive']),
  },
  methods: {
    changeContent(index) {
      this.contentState = index;
      window.scrollTo({ top: 0, behavior: 'smooth' });
    },
    temp() {
      this.wines0.push({
        type: '레드',
        ename: '`Morande, Pionero Rose`',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
        img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
      });
    },
  },
};
</script>

<style scoped>
/* 트랜지션 */
.slideup-leave-active {
  transition: transform 0.5s, opacity 0.5s cubic-bezier(0, 1, 0.65, 1);
}
.slideup-enter-active {
  transition: transform 0.5s cubic-bezier(0, 1, 0.65, 1) 0.5s, opacity 0.5s cubic-bezier(0, 1, 0.65, 1) 0.5s;
}
.slideup-enter,
.slideup-leave-to {
  transform: translate(0, 100%);
  opacity: 0;
}
.slideup-enter-to,
.slideup-leave {
  transform: translate(0, 0);
  opacity: 1;
}

.temp {
  position: fixed;
  left: 200px;
  top: 200px;
  width: 100px;
  height: 100px;
  background-color: #fff;
  z-index: 100;
}

#recommendation {
  display: flex;
  flex-direction: row;
}
.sidebar {
  z-index: 5;
  position: fixed;
}
.content {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  z-index: 0;
  padding-left: 172px;
  padding-top: 100px;
}
.info {
  width: 90%;
  height: 5rem;
  text-align: center;
  color: #fff;
  font-size: 2rem;
  line-height: 5rem;
  background-color: rgba(0, 0, 0, 0.488);
}
.winelist {
}
</style>
