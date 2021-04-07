<template>
  <div>
    <div id="wrap" class="content-shadow">
      <div id="guide-msg-frame" class="real-shadow-box" :class="{ extend: guideBtnTip, clear: !guideBtnTip }">
        <p class="guide-msg m-desc">{{ guideMsg }}</p>
        <div class="marquee-container">
          <div class="marquee marquee-frame" @click="clickedGuideMsg">
            <span class="guide-msg m-desc">{{ guideMsg }}</span>
            <span class="guide-msg m-desc">{{ guideMsg }}</span>
            <span class="guide-msg m-desc">{{ guideMsg }}</span>
            <span class="guide-msg m-desc">{{ guideMsg }}</span>
          </div>
        </div>
      </div>
      <div id="guide-btn-frame">
        <button class="guide-btn container-shadow-bold" @click="clickedGuideBtn"></button>
      </div>
      <div id="extension-frame" :class="{ height: !guideBtn }">
        <button v-if="!loginState" class="extension-item" :class="{ shrink: !guideBtn }" @click="loginPop">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
            <path
              d="M8 9v-4l8 7-8 7v-4h-8v-6h8zm6-7c-1.787 0-3.46.474-4.911 1.295l.228.2 1.395 1.221c1.004-.456 2.115-.716 3.288-.716 4.411 0 8 3.589 8 8s-3.589 8-8 8c-1.173 0-2.284-.26-3.288-.715l-1.395 1.221-.228.2c1.451.82 3.124 1.294 4.911 1.294 5.522 0 10-4.477 10-10s-4.478-10-10-10z"
            />
          </svg>
          <span class="extension-item-title">Login | Join</span>
        </button>
        <button v-if="loginState" class="extension-item" :class="{ shrink: !guideBtn }" @click="clickedMyInfo">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
            <path
              d="M19 7.001c0 3.865-3.134 7-7 7s-7-3.135-7-7c0-3.867 3.134-7.001 7-7.001s7 3.134 7 7.001zm-1.598 7.18c-1.506 1.137-3.374 1.82-5.402 1.82-2.03 0-3.899-.685-5.407-1.822-4.072 1.793-6.593 7.376-6.593 9.821h24c0-2.423-2.6-8.006-6.598-9.819z"
            />
          </svg>
          <span class="extension-item-title">My Info</span>
        </button>
        <button class="extension-item" :class="{ shrink: !guideBtn }" @click="clickedScrapList">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
            <path d="M12 4.248c-3.148-5.402-12-3.825-12 2.944 0 4.661 5.571 9.427 12 15.808 6.43-6.381 12-11.147 12-15.808 0-6.792-8.875-8.306-12-2.944z" />
          </svg>
          <span class="extension-item-title">Scrap List</span>
        </button>
        <button class="extension-item" :class="{ shrink: !guideBtn }" @click="clickedReviewList">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
            <path d="M12 .587l3.668 7.568 8.332 1.151-6.064 5.828 1.48 8.279-7.416-3.967-7.417 3.967 1.481-8.279-6.064-5.828 8.332-1.151z" />
          </svg>
          <span class="extension-item-title">Review List</span>
        </button>
      </div>

      <transition name="scale">
        <div v-if="loginState && guideBtn" id="logout-btn-frame" class="real-shadow-box">
          <button class="logout-btn" @click="clickedLogoutBtn">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
              <path
                d="M16 9v-4l8 7-8 7v-4h-8v-6h8zm-2 10v-.083c-1.178.685-2.542 1.083-4 1.083-4.411 0-8-3.589-8-8s3.589-8 8-8c1.458 0 2.822.398 4 1.083v-2.245c-1.226-.536-2.577-.838-4-.838-5.522 0-10 4.477-10 10s4.478 10 10 10c1.423 0 2.774-.302 4-.838v-2.162z"
              />
            </svg>
            <span class="logout-btn-title t-desc-e">Logout</span>
          </button>
        </div>
      </transition>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from 'vuex';

export default {
  name: 'GuideBtn',
  data() {
    return {
      loginState: false,
    };
  },
  created() {},
  mounted() {
    this.$store.dispatch('guideBtn/setGuideMsg');
    this.$store.dispatch('guideBtn/initGuideMsg');
  },
  computed: {
    ...mapState('userInfo', ['userInfo']),
    ...mapState('guideBtn', ['guideBtn', 'guideBtnTip', 'guideMsg']),
    ...mapState('loginDialog', ['loginDialog']),
  },
  watch: {
    userInfo: {
      deep: true,
      immediate: true,
      handler(data) {
        if (data.token) {
          this.loginState = true;
        } else {
          this.loginState = false;
        }
      },
    },
  },
  methods: {
    ...mapMutations('guideBtn', ['SET_GUIDEBTN_TOGGLE', 'SET_GUIDEBTNTIP_TOGGLE']),
    ...mapMutations('loginDialog', ['SET_LOGIN_TOGGLE']),
    clickedGuideBtn() {
      this.SET_GUIDEBTN_TOGGLE();
    },
    clickedGuideMsg() {
      console.log('clicked guideMsg area');
    },
    loginPop() {
      this.SET_LOGIN_TOGGLE();
    },
    clickedLogoutBtn() {
      this.$store.commit('userInfo/SET_LOGOUT');
      this.SET_GUIDEBTN_TOGGLE();
      this.$router.push({ name: 'Main' });
    },
    clickedMyInfo() {
      this.$router.push({ name: 'Mypage' });
    },
    clickedScrapList() {
      this.$router.push({ name: 'Mypage' });
    },
    clickedReviewList() {
      this.$router.push({ name: 'Mypage' });
    },
  },
};
</script>

<style scoped>
#wrap {
  pointer-events: all;
}
/* TODO: 가이드 버튼 기본 프레임 */
/* FIXME:  */
#guide-msg-frame {
  float: right;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width: 80px;
  height: 80px;
  margin-right: 10px;
  margin-bottom: 12px;
  overflow: hidden;
  cursor: pointer;
  background-color: white;
  border-radius: 40px;
  transition: 0.3s all, 0.05s border-radius ease;
}
.extend {
  width: 230px !important;
}
#guide-msg-frame:hover {
  border-radius: 0;
  margin-right: 0px;
  margin-bottom: 0px;
  padding-right: 10px;
  width: 100% !important;
  height: 104px;
  background-color: rgba(255, 255, 255, 0.5);
}
#guide-msg-frame:hover p {
  display: none;
}
#guide-msg-frame:hover .marquee-container {
  display: flex;
}

/* TODO: 도움말 텍스트 */
/* FIXME:  */
.guide-msg {
  font-size: 0.8rem;
  line-height: 1rem;
  color: var(--basic-color-key);
  padding: 0 4px 0 20px;
  width: calc(100% - 80px);
}
.guide-msg-marquee {
  width: 100%;
}
.guide-mag-marquee {
  position: absolute;
}

/* TODO: 흐르는 텍스트 */
/* FIXME:  */
.marquee-container {
  display: none;
  align-items: center;
  position: absolute;
  margin-top: 16px;
  height: 100%;
  white-space: nowrap;
  overflow: hidden;
}
.marquee-frame {
  padding: 10px 0;
  white-space: nowrap;
}
.marquee-frame span {
  font-size: 4rem;
}

/* TODO: 젠킨스 버튼 */
/* FIXME:  */
#guide-btn-frame {
  position: absolute;
  z-index: 2;
  width: 80px;
  height: 80px;
  right: 10px;
  bottom: 12px;
  cursor: pointer;
  background-color: white;
  border-radius: 40px;
  transition: 0.3s all, 0.05s border-radius ease;
}
button {
  width: 72px;
  height: 72px;
  margin: 4px 4px 4px 4px;
  border-radius: 50%;
}
.guide-btn {
  background-color: #c9726d;
  background-image: url(../../assets/images/guide_btn_image.png);
  background-position: center;
  background-size: contain;
  transition: 0.5s transform, 0.5s background-color ease;
}
.guide-btn:hover {
  transform: scale(1.11);
  background-color: #c7433c;
}

/* TODO:확장 버튼 */
/* FIXME:  */
#extension-frame {
  position: absolute;
  right: 10px;
  bottom: 12px;
  width: 80px;
  height: 320px;
  overflow: visible;
  background-color: rgba(255, 255, 255, 0.5);
  border-radius: 80px;
  padding-bottom: 80px;
  transition: height 0.5s ease;
}
.height {
  height: 0px !important;
  overflow: hidden !important;
}
.extension-item {
  background-color: rgba(255, 255, 255, 0.7);
  box-shadow: 0px 0px 1px rgba(0, 0, 0, 0.05), 0px 0px 2px rgba(0, 0, 0, 0.05), 0px 0px 4px rgba(0, 0, 0, 0.04), 0px 0px 8px rgba(0, 0, 0, 0.1),
    0 16px 16px rgba(0, 0, 0, 0.1) !important;
  transition: background-color 0.3s, transform 0.3s ease;
}
.extension-item:hover {
  background-color: #fff;
}
.extension-item:nth-child(1) {
  padding-top: 2px;
  padding-right: 3px;
  transition-delay: 0s;
}
.extension-item:nth-child(2) {
  padding-top: 6px;
  transition-delay: 0.1s;
}
.extension-item:nth-child(3) {
  transition-delay: 0.2s;
}

.extension-item-title {
  width: 200px;
  font-family: 'Noto Sans KR', sans-serif;
  font-weight: 900;
  font-size: 1.8rem;
  color: var(--basic-color-fill);
  text-align: end;
  position: absolute;
  right: 84px;
}

/* 로그아웃 */
#logout-btn-frame {
  position: absolute;
  right: 10px;
  bottom: 340px;
  border-radius: 50%;
  background-color: rgba(255, 255, 255, 0.3);
}
.logout-btn {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: rgba(255, 162, 47, 0.5);
  transition: all 0.3s ease;
}
.logout-btn:hover {
  transform: scale(0.8);
}
.logout-btn svg {
  margin-left: 5px;
  fill: rgb(205, 0, 0);
  opacity: 0.7;
}
.logout-btn-title {
  font-size: 14px;
}
</style>
