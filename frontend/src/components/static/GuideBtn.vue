<template>
  <div id="wrap" class="content-shadow">
    <div id="guide-btn-frame" class="real-shadow-box" :class="{ extend: guideBtnTip }">
      <p class="guide-msg m-desc">{{ initMsg }}</p>
      <div class="marquee-container">
        <div class="marquee marquee-frame">
          <span class="guide-msg m-desc">{{ initMsg }}</span>
          <span class="guide-msg m-desc">{{ initMsg }}</span>
        </div>
      </div>
      <button class="guide-btn container-shadow-bold"></button>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  name: 'GuideBtn',
  data() {
    return {
      initMsg: '로그인하여 다양한 서비스를 이용하세요.',
    };
  },
  created() {},
  mounted() {},
  computed: {
    ...mapState('guideBtn', ['guideBtn', 'guideBtnTip']),
  },
};
</script>

<style scoped>
#guide-btn-frame {
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
#guide-btn-frame:hover {
  border-radius: 0;
  margin-right: 0px;
  margin-bottom: 0px;
  padding-right: 10px;
  width: 100% !important;
  height: 104px;
  background-color: rgba(255, 255, 255, 0.596);
}
#guide-btn-frame:hover p {
  display: none;
}
#guide-btn-frame:hover .marquee-container {
  display: flex;
}

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

.guide-btn {
  width: 72px;
  height: 72px;
  margin: 4px 4px 4px auto;
  background-color: #c9726d;
  background-image: url(../../assets/images/guide_btn_image.png);
  background-position: center;
  background-size: contain;
  border-radius: 50%;
  z-index: 2;
  transition: 0.5s transform, 0.5s background-color ease;
}
.guide-btn:hover {
  transform: scale(1.11);
  background-color: #c7433c;
}
</style>
