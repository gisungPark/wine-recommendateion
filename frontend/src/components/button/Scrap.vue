<template>
  <div id="scrap" @click="clickedScrap">
    <svg :class="{ red: scraped }" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
      <path d="M12 4.248c-3.148-5.402-12-3.825-12 2.944 0 4.661 5.571 9.427 12 15.808 6.43-6.381 12-11.147 12-15.808 0-6.792-8.875-8.306-12-2.944z" />
    </svg>
    <span class="b-desc">Scrap</span>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
export default {
  name: 'Scrap',
  props: {
    scraped: {
      type: Boolean,
      default: function() {
        return false;
      },
    },
  },
  methods: {
    ...mapActions('scrap', ['actAddWineScrap', 'actRemoveWineScrap']),
    clickedScrap() {
      if (this.scraped) {
        this.actRemoveWineScrap().then((result) => {
          if (result) {
            this.scraped = false;
            this.$toast.open({
              message: '찜목록에서 삭제되었습니다.',
              type: 'warning',
            });
          } else {
            this.$toast.open({
              message: '문제가 발생했습니다.',
              type: 'error',
            });
            return;
          }
        });
      } else {
        this.actAddWineScrap().then((result) => {
          if (result) {
            this.scraped = true;
            this.$toast.open({
              message: '찜목록에 추가되었습니다.',
              type: 'success',
            });
          } else {
            this.$toast.open({
              message: '문제가 발생했습니다.',
              type: 'error',
            });
          }
        });
      }
    },
  },
};
</script>

<style>
#scrap {
  display: flex;
  justify-content: space-around;
  align-items: center;
  cursor: pointer;
  color: black;
  background-color: #fff;
  padding: 0.5rem;
  width: 6.2rem;
  height: 2.4rem;
  border-radius: 1.2rem;
  transition: background-color 0.3s ease;
}
#scrap:hover {
  background-color: var(--basic-color-key);
}
#scrap:hover span {
  color: #fff;
}
#scrap:hover svg {
  transform: scale(1.3);
}

#scrap svg {
  padding: 3px;
  fill: var(--basic-color-fill);
  transition: fill 0.3s, opacity 0.3s, transform 0.3s cubic-bezier(0.49, 0.15, 0.22, 1.36);
}
#scrap span {
  padding: 3px;
  margin-top: -0.1rem;
  font-size: 1.2rem;
  transition: color 0.3s ease;
}
.red {
  fill: rgb(205, 0, 0) !important;
}
</style>
