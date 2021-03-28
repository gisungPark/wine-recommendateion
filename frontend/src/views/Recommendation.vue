<template>
  <div id="recommendation">
    <Sidebar class="sidebar" />
    <section class="content">
      <div class="info">
        임시 영역 || 추천 기반 정보 제공 || 통계자료 확인>
      </div>
      <Winelist class="winelist" />
    </section>
  </div>
</template>

<script>
import { mapState, mapMutations } from 'vuex';
import Sidebar from '@/components/articles/Sidebar.vue';
import Winelist from '@/components/articles/Winelist.vue';

export default {
  name: 'Recommendation',
  components: {
    Sidebar,
    Winelist,
  },

  data: () => ({}),
  computed: {
    ...mapState('userInfo', ['userInfo']),
    ...mapState('nav', ['navActive']),
  },
  methods: {},
};
</script>

<style scoped>
#recommendation {
  display: flex;
  flex-direction: row;
  background-color: blue !important;
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
  height: 2000px;
  z-index: 0;
  padding-left: 172px;
  padding-top: 100px;
  background-color: green;
}
.info {
  width: 90%;
  height: 5rem;
  text-align: center;
  color: aliceblue;
  font-size: 2rem;
  line-height: 5rem;
  background-color: rgba(0, 0, 0, 0.488);
}
.winelist {
}
</style>
