<template>
  <div id="wrap" class="content-shadow">
    <div
      id="guide-msg-frame"
      class="real-shadow-box"
      :class="{ extend: guideBtnTip, clear: !guideBtnTip }"
    >
      <p class="guide-msg m-desc">{{ initMsg }}</p>
      <div class="marquee-container">
        <div class="marquee marquee-frame" @click="clickedGuideMsg">
          <span class="guide-msg m-desc">{{ initMsg }}</span>
          <span class="guide-msg m-desc">{{ initMsg }}</span>
          <span class="guide-msg m-desc">{{ initMsg }}</span>
          <span class="guide-msg m-desc">{{ initMsg }}</span>
        </div>
      </div>
    </div>
    <div id="guide-btn-frame">
      <button
        class="guide-btn container-shadow-bold"
        @click="clickedGuideBtn"
      ></button>
    </div>
    <div id="extension-frame" :class="{ height: !guideBtn }">
      <button
        class="extension-item"
        :class="{ shrink: !guideBtn }"
        @click="loginPop"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="24"
          height="24"
          viewBox="0 0 24 24"
        >
          <path
            d="M8 9v-4l8 7-8 7v-4h-8v-6h8zm6-7c-1.787 0-3.46.474-4.911 1.295l.228.2 1.395 1.221c1.004-.456 2.115-.716 3.288-.716 4.411 0 8 3.589 8 8s-3.589 8-8 8c-1.173 0-2.284-.26-3.288-.715l-1.395 1.221-.228.2c1.451.82 3.124 1.294 4.911 1.294 5.522 0 10-4.477 10-10s-4.478-10-10-10z"
          />
        </svg>
        <span class="extension-item-title">Login | Join</span>
      </button>
      <button class="extension-item" :class="{ shrink: !guideBtn }">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="24"
          height="24"
          viewBox="0 0 24 24"
        >
          <path
            d="M12 4.248c-3.148-5.402-12-3.825-12 2.944 0 4.661 5.571 9.427 12 15.808 6.43-6.381 12-11.147 12-15.808 0-6.792-8.875-8.306-12-2.944z"
          />
        </svg>
        <span class="extension-item-title">Scrap List</span>
      </button>
      <button class="extension-item" :class="{ shrink: !guideBtn }">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="24"
          height="24"
          viewBox="0 0 24 24"
        >
          <path
            d="M12 .587l3.668 7.568 8.332 1.151-6.064 5.828 1.48 8.279-7.416-3.967-7.417 3.967 1.481-8.279-6.064-5.828 8.332-1.151z"
          />
        </svg>
        <span class="extension-item-title">Review List</span>
      </button>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";

export default {
  name: "GuideBtn",
  data() {
    return {
      initMsg: "로그인하여 다양한 서비스를 이용하세요.",
    };
  },
  created() {},
  mounted() {},
  computed: {
    ...mapState("guideBtn", ["guideBtn", "guideBtnTip"]),
    ...mapState("loginDialog", ["loginDialog"]),
  },
  methods: {
    ...mapMutations("guideBtn", [
      "SET_GUIDEBTN_TOGGLE",
      "SET_GUIDEBTNTIP_TOGGLE",
    ]),
    ...mapMutations("loginDialog", ["SET_LOGIN_TOGGLE"]),
    clickedGuideBtn() {
      this.SET_GUIDEBTN_TOGGLE();
    },
    clickedGuideMsg() {
      alert(`가이드 메시지에 맞는 동작\n예: 로그인 dialog 활성화`);
    },
    loginPop() {
      this.SET_LOGIN_TOGGLE();
    },
  },
};
</script>

<style scoped>
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
  box-shadow: 0px 0px 1px rgba(0, 0, 0, 0.05), 0px 0px 2px rgba(0, 0, 0, 0.05),
    0px 0px 4px rgba(0, 0, 0, 0.04), 0px 0px 8px rgba(0, 0, 0, 0.1),
    0 16px 16px rgba(0, 0, 0, 0.1) !important;
  transition: background-color, transform 0.3s ease;
}
.extension-item:hover {
  background-color: #fff;
}
.extension-item:nth-child(1) {
  padding-top: 2px;
  transition-delay: 0s;
}
.extension-item:nth-child(2) {
  padding-top: 6px;
  fill: rgb(205, 0, 0);
  transition-delay: 0.1s;
}
.extension-item:nth-child(3) {
  transition-delay: 0.2s;
}

.extension-item-title {
  width: 200px;
  font-family: "Noto Sans KR", sans-serif;
  font-weight: 900;
  font-size: 1.8rem;
  color: var(--basic-color-fill);
  text-align: end;
  position: absolute;
  right: 84px;
}
</style>
