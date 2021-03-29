<template>
  <div class="review-item-frame">
    <div class="review-content">
      <span
        >{{ review.contents
        }}<img src="@/assets/images/star-rating.png" alt="" />
        <img src="@/assets/images/money-icons.png" alt=""
      /></span>
    </div>
    <div style="height: 50px"></div>
    <div class="review-writer">
      <div class="writer-img">
        <img :src="review.profile" alt="" />
      </div>
      <div class="addition-info">
        <span class="addition-info-usernickname">{{ review.nickname }}</span>
        <span class="addition-info-date">{{ review.date }}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["review"],
  computed: {},
  data: () => ({}),
  methods: {},
};
</script>

<style scoped>
.review-item-frame {
  width: 100%;
  min-height: 100px;
  display: flex;
  flex-direction: column;
  background-color: #f4f4f4;
  border-radius: 2em;
  overflow: hidden;
  position: relative;
}
.review-content {
  margin-top: 10px;
  margin-left: 15px;
  margin-right: 15px;
  display: flex;
  flex-direction: row;
}
.review-content > img {
  width: 30px;
  height: 30px;
}
.review-writer {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  position: absolute;
  bottom: 3px;
  right: 15px;
}
.writer-img {
  width: 40px;
  height: 40px;
  border-radius: 70%;
  overflow: hidden;
  margin-right: 10px;
}

.writer-img > img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.addition-info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
}
.addition-info-usernickname {
  font-size: 20px;
  font-weight: 550;
  color: var(--basic-color-bg2);
}
.addition-info-date {
  color: #808080;
}
</style>