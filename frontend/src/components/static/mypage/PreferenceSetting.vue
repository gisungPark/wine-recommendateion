<template>
  <div class="preference-fram">
    <div class="preference-stages">
      <a href="#">좋아하는 향 설정</a>
      <a href="#">싫어하는 향 설정</a>
      <a href="#">와인 취향 설정</a>
    </div>
    <div class="preference-fillter">
      <div v-show="curStage == 1" class="preference-item1">
        <div class="list-1" style="background-color: white">
          <div class="list-item">
            <div
              class="list-item-cards"
              v-for="(item, idx) in list1"
              :key="item + idx"
              :style="{ backgroundImage: `url(${item})` }"
            ></div>
          </div>
        </div>
        <div class="list-2" style="background-color: pink">
          <div
            class="list-item-cards"
            v-for="(item, idx) in list2"
            :key="item + idx"
            :style="{ backgroundImage: `url(${item})` }"
          ></div>
        </div>
        <div class="list-3" style="background-color: green">
          <div
            class="list-item-cards"
            v-for="(item, idx) in list3"
            :key="item + idx"
            :style="{ backgroundImage: `url(${item})` }"
          ></div>
        </div>
        <div class="list-4" style="background-color: blue">
          <div
            class="list-item-cards"
            v-for="(item, idx) in list4"
            :key="item + idx"
            :style="{ backgroundImage: `url(${item})` }"
          ></div>
        </div>
        <div class="list-5" style="background-color: white">
          <div
            class="list-item-cards"
            v-for="(item, idx) in list5"
            :key="item + idx"
            :style="{ backgroundImage: `url(${item})` }"
          ></div>
        </div>
      </div>
      <div v-show="curStage == 2" class="preference-item2"></div>
      <div v-show="curStage == 3" class="preference-item3"></div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["preferenceList"],
  created() {
    console.log("asdfasdfasdfa" + this.preferenceList[0].img);
    for (var i = 0; i < this.preferenceList.length; i++) {
      if (i % 5 == 0) this.list1.push(this.preferenceList[i].img);
      else if (i % 5 == 1) this.list2.push(this.preferenceList[i].img);
      else if (i % 5 == 2) this.list3.push(this.preferenceList[i].img);
      else if (i % 5 == 3) this.list4.push(this.preferenceList[i].img);
      else this.list5.push(this.preferenceList[i].img);

      console.log(this.list1);
    }
  },
  mounted() {},
  data: () => ({
    curStage: 1,
    list1: [],
    list2: [],
    list3: [],
    list4: [],
    list5: [],
  }),
  computed: {},
};
</script>

<style>
.preference-fram {
  width: 100%;
  flex-direction: column;
  justify-content: center;
}
.preference-stages {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.preference-stages > a {
  color: white;
  border: none;
  padding: 15px 0;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 15px;
  margin: 4px;
  cursor: pointer;
  flex: 1 1 0;
  /* position: relative;
  left: -85px; */
}

.preference-fillter {
  height: 100%;
  width: 100%;
}
.preference-item1 {
  margin-top: 120px;
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.preference-item1 > div {
  height: auto;
  width: 200px;
  margin: 8px;
  display: flex;
  flex-direction: column;
}

.list-2,
.list-4 {
  margin-top: 60px !important;
}
.list-item-cards {
  width: 100%;
  height: 300px;
  margin-top: 10px;
  background-size: contain;
  background-position: center;
}
</style>