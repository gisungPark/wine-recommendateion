<template>
  <div class="preference-fram">
    <div class="preference-stages">
      <div class="preference-stages-wrap">
        <div
          class="stage"
          :class="{ 'active-stage': isCurStage(1) }"
          @click="onClickStage(1)"
        ></div>
        <a
          class="stage-name"
          :class="{ 'active-stage-name': isCurStage(1) }"
          href="#"
          @click="onClickStage(1)"
          >좋아하는 향</a
        >
      </div>
      <div class="preference-stages-wrap">
        <div
          class="stage"
          :class="{ 'active-stage': isCurStage(2) }"
          @click="onClickStage(2)"
        ></div>
        <a
          class="stage-name"
          :class="{ 'active-stage-name': isCurStage(2) }"
          @click="onClickStage(2)"
          href="#"
          >싫어하는 향</a
        >
      </div>
      <div class="preference-stages-wrap">
        <div
          class="stage"
          :class="{ 'active-stage': isCurStage(3) }"
          @click="onClickStage(3)"
        ></div>
        <a
          class="stage-name"
          :class="{ 'active-stage-name': isCurStage(3) }"
          @click="onClickStage(3)"
          href="#"
          >와인 취향</a
        >
      </div>
    </div>
    <div class="preference-fillter">
      <div v-show="curStage != 3" class="preference-item1">
        <!-- ######################### CARD LIST ################################## -->
        <div class="lists list-1">
          <div
            class="list-item-cards"
            :class="{
              'list-item-cards-active': currentCardState(item),
              'list-item-cards-inactive': isInactive(item),
            }"
            v-for="(item, idx) in list1"
            :key="item + idx"
            @click="onCardActive(item)"
            :style="{ backgroundImage: `url(${item.img})` }"
          ></div>
        </div>
        <div class="lists list-2">
          <div
            class="list-item-cards"
            :class="{
              'list-item-cards-active': currentCardState(item),
              'list-item-cards-inactive': isInactive(item),
            }"
            v-for="(item, idx) in list2"
            :key="item + idx"
            @click="onCardActive(item)"
            :style="{ backgroundImage: `url(${item.img})` }"
          ></div>
        </div>
        <div class="lists list-3">
          <div
            class="list-item-cards"
            :class="{
              'list-item-cards-active': currentCardState(item),
              'list-item-cards-inactive': isInactive(item),
            }"
            v-for="(item, idx) in list3"
            :key="item + idx"
            @click="onCardActive(item)"
            :style="{ backgroundImage: `url(${item.img})` }"
          ></div>
        </div>
        <div class="lists list-4">
          <div
            class="list-item-cards"
            :class="{
              'list-item-cards-active': currentCardState(item),
              'list-item-cards-inactive': isInactive(item),
            }"
            v-for="(item, idx) in list4"
            :key="item + idx"
            @click="onCardActive(item)"
            :style="{ backgroundImage: `url(${item.img})` }"
          ></div>
        </div>
        <div class="lists list-5">
          <div
            class="list-item-cards"
            :class="{
              'list-item-cards-active': currentCardState(item),
              'list-item-cards-inactive': isInactive(item),
            }"
            v-for="(item, idx) in list5"
            :key="item + idx"
            @click="onCardActive(item)"
            :style="{ backgroundImage: `url(${item.img})` }"
          ></div>
        </div>

        <!-- ############################################ -->
        <!-- ########## start 우측 선택된 아이템 목록 ########### -->

        <div class="selected-list">
          <div class="selected-list-title">
            <span v-show="curStage == 1">Favorite list</span>
            <span v-show="curStage == 2">Dislike list</span>
          </div>
          <div v-show="curStage == 1" class="selected-list-like">
            <div
              class="selected-list-item"
              v-for="(item, idx) in likeList"
              :key="item + idx"
            >
              <h1>{{ item.name }}</h1>
            </div>
          </div>
          <div v-show="curStage == 2" class="selected-list-hate">
            <div
              class="selected-list-item"
              v-for="(item, idx) in hateList"
              :key="item + idx"
            >
              <h1>{{ item.name }}</h1>
            </div>
          </div>
        </div>

        <!-- ############################################ -->
        <!-- ########## end 우측 선택된 아이템 목록  ########### -->
      </div>
      <!-- <div v-show="curStage == 2" class="preference-item2"></div> -->
      <div v-show="curStage == 3" class="preference-item3">
        <div class="preference-item3-item">
          <span>낮다</span>
          <div id="slider-space">
            <v-slider
              v-model="slider1"
              :max="4"
              step="1"
              :thumb-size="24"
              ticks="always"
              tick-size="5"
              color="#e1a957"
              track-color="#821a33"
              track-fill-color="#821a33"
            ></v-slider>
          </div>
          <span>높다</span>
        </div>
        <div class="preference-item3-item">
          <span>낮다</span>
          <div id="slider-space">
            <v-slider
              v-model="slider2"
              :max="4"
              step="1"
              :thumb-size="24"
              ticks="always"
              tick-size="5"
              color="#e1a957"
              track-color="#821a33"
              track-fill-color="#821a33"
            ></v-slider>
          </div>
          <span>높다</span>
        </div>
        <div class="preference-item3-item">
          <span>낮다</span>
          <div id="slider-space">
            <v-slider
              v-model="slider3"
              :max="4"
              step="1"
              :thumb-size="24"
              ticks="always"
              tick-size="5"
              color="#e1a957"
              track-color="#821a33"
              track-fill-color="#821a33"
            ></v-slider>
          </div>
          <span>높다</span>
        </div>
        <div class="preference-item3-item">
          <span>가볍다</span>
          <div id="slider-space">
            <v-slider
              v-model="slider4"
              :max="4"
              step="1"
              :thumb-size="24"
              ticks="always"
              tick-size="5"
              color="#e1a957"
              track-color="#821a33"
              track-fill-color="#821a33"
            ></v-slider>
          </div>
          <span>무겁다</span>
        </div>
        <div class="preference-item3-item">
          <span>적다</span>
          <!-- <div id="slider-space">
            <input
              type="range"
              min="1"
              max="100"
              value="50"
              class="slider"
              id="myRange"
            /> -->
          <div id="slider-space">
            <v-slider
              v-model="slider5"
              :max="4"
              step="1"
              :thumb-size="24"
              ticks="always"
              tick-size="5"
              color="#e1a957"
              track-color="#821a33"
              track-fill-color="#821a33"
            ></v-slider>
          </div>
          <span>많다</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
const FIRST_STAGE = 1,
  SECOND_STAGE = 2,
  THIRD_STAGE = 3;

export default {
  props: ["preferenceList"],
  created() {
    for (var i = 0; i < this.preferenceList.length; i++) {
      const item = {
        flavor_id: this.preferenceList[i].flavor_id,
        name: this.preferenceList[i].name,
        img: this.preferenceList[i].img,
        isLike: false,
        isHate: false,
      };

      if (i % 5 == 0) this.list1.push(item);
      else if (i % 5 == 1) this.list2.push(item);
      else if (i % 5 == 2) this.list3.push(item);
      else if (i % 5 == 3) this.list4.push(item);
      else this.list5.push(item);
    }
  },
  mounted() {},
  data: () => ({
    curStage: 1,
    lickCnt: 0,
    hateCnt: 0,
    list1: [],
    list2: [],
    list3: [],
    list4: [],
    list5: [],
    likeList: [],
    hateList: [],
    slider1: 1,
    slider2: 2,
    slider3: 2,
    slider4: 2,
    slider5: 2,
  }),
  methods: {
    onClickStage(stage) {
      this.curStage = stage;
    },
    // 상단 stage 클릭시 stage 변경 함수
    isCurStage(stage) {
      if (this.curStage == stage) return true;
      else return false;
    },
    /*
     * 현재 stage(좋아하는 또는 싫어하는) 여부에 따라 카드 활성화 여부 체크 함수!
     * 현재 like 단계시, like 선택된 카드는 크기 1.2배, 투명도 0 설정!!
     */
    currentCardState(item) {
      if (this.curStage == FIRST_STAGE && item.isLike) return true;
      else if (this.curStage == SECOND_STAGE && item.isHate) return true;
      return false;
    },
    /*
     * 현재 stage(좋아하는 또는 싫어하는) 여부에 따라 카드 비활성화 여부 체크 함수!
     * 현재 like 단계시, hate 선택된 카드는 크기 투명도 0.2 설정!!
     */
    isInactive(item) {
      if (this.curStage == FIRST_STAGE && item.isHate) return true;
      else if (this.curStage == SECOND_STAGE && item.isLike) return true;
      return false;
    },
    /*
     * 현재 stage에서 해당 카드가 선택가능한지 확인
     */
    isSelectPossible(stage) {
      if (stage == FIRST_STAGE) {
        if (this.lickCnt <= 2) return true;
        else return false;
      } else if (stage == SECOND_STAGE) {
        if (this.hateCnt <= 2) return true;
        else return false;
      }
    },
    /*
     * 배열에서 해당 객체 삭제 함수!!
     */
    removeItemFromList(stage, targe) {
      let itemToFine = "";
      let removeIdx = -1;
      if (stage == FIRST_STAGE) {
        itemToFine = this.likeList.find(function (item) {
          return item.flavor_id === targe.flavor_id;
        });
        removeIdx = this.likeList.indexOf(itemToFine);
        if (removeIdx > -1) {
          this.likeList.splice(removeIdx, 1);
          this.lickCnt--;
        }
      } else if (stage == SECOND_STAGE) {
        itemToFine = this.hateList.find(function (item) {
          return item.flavor_id === targe.flavor_id;
        });
        removeIdx = this.hateList.indexOf(itemToFine);
        if (removeIdx > -1) {
          this.hateCnt--;
          this.hateList.splice(removeIdx, 1);
        }
      }
    },
    /*
     * 카드 클릭 이벤트!!
     */
    onCardActive(item) {
      if (this.curStage == FIRST_STAGE) {
        if (item.isHate) return;
        // 1. 좋아한다고 선택하는 경우
        if (!item.isLike) {
          if (!this.isSelectPossible(FIRST_STAGE)) {
            alert("선택 가능한 갯수를 초과했습니다.");
            return;
          } else {
            this.likeList.push(item);
            this.lickCnt++;
            item.isLike = true;
          }
        } else {
          // 3. 좋아한다고 했다가 취소하는 경우
          item.isLike = false;
          this.removeItemFromList(FIRST_STAGE, item);
        }
      }
      // 2. 싫어한다고 선택하는 경우
      // 4. 싫어한다고 했다가 취소하는 경우
      else if (this.curStage == SECOND_STAGE) {
        if (item.isLike) return;
        if (!item.isHate) {
          if (!this.isSelectPossible(SECOND_STAGE)) {
            alert("선택 가능한 갯수를 초과했습니다." + this.hateCnt);
            return;
          } else {
            this.hateList.push(item);
            this.hateCnt++;
            item.isHate = true;
          }
        } else {
          // 3. 좋아한다고 했다가 취소하는 경우
          item.isHate = false;
          this.removeItemFromList(SECOND_STAGE, item);
        }
        console.log("싫어하는 리스트!!!   " + this.hateCnt);
        for (var i = 0; i < this.hateList.length; i++) {
          console.log(this.hateList[i]);
        }
        console.log();
      }
    },
  },
};
</script>

<style>
.preference-fram {
  width: 100%;
  flex-direction: column;
  justify-content: center;
}
.preference-stages {
  width: 80%;
  position: fixed;
  top: 100px;
  left: 230px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  z-index: 10;
  pointer-events: none;
}
.preference-stages-wrap {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  flex: 1 1 0;
  /* 상단 스테이지값 고정시 삭제 요망!!! */
  /* position: relative;
  z-index: 10 !important; */
}

.stage {
  width: 65px;
  height: 65px;
  border-radius: 70%;
  background-color: white;
  pointer-events: all;
}

.active-stage {
  background-color: var(--basic-color-key);
}
.active-stage-name {
  color: var(--basic-color-key) !important;
}

.stage-name {
  color: white;
  border: none;
  padding: 15px 0;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 0.8rem;
  margin-top: -2px;
  cursor: pointer;
  /* position: relative;
  left: -85px; */
}

.preference-fillter {
  height: 100%;
  width: 100%;
}
.preference-item1 {
  position: relative;
  left: -60px;
  margin-top: 160px;
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.preference-item3 {
  position: relative;
  left: -15px;
  margin-top: 130px;
  height: 90%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.preference-item3-item {
  width: 100%;
  height: 80px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.preference-item3-item > span {
  color: white;
  font-size: 32px;
  font-weight: bold;
}
#slider-space {
  width: 500px;
  margin-left: 45px;
  margin-right: 40px;
}

.slidecontainer {
  width: 100%;
}

.slider {
  -webkit-appearance: none;
  width: 100%;
  height: 25px;
  background: #d3d3d3;
  outline: none;
  opacity: 0.7;
  -webkit-transition: 0.2s;
  transition: opacity 0.2s;
}

.slider:hover {
  opacity: 1;
}

.slider::-webkit-slider-thumb {
  -webkit-appearance: none;
  appearance: none;
  width: 25px;
  height: 25px;
  background: #4caf50;
  cursor: pointer;
}

.slider::-moz-range-thumb {
  width: 25px;
  height: 25px;
  background: #4caf50;
  cursor: pointer;
}

.lists {
  height: auto;
  width: 200px;
  margin: 4px;
  display: flex;
  flex-direction: column;
  transform: scale(1.1);
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
  opacity: 0.7;
}
.list-item-cards-active {
  opacity: 1 !important;
  z-index: 5;
  transform: scale(1.2) !important;
}

.list-item-cards-inactive {
  opacity: 0.1 !important;
}

.selected-list {
  position: fixed;
  right: 38px;
  top: 250px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center;
}
.selected-list-title {
  width: 100%;
  display: flex;
  justify-content: center;
}
.selected-list-title > span {
  color: white;
  font-size: 28px;
  font-weight: bold;
  margin-bottom: 2px;
  text-align: center;
}
.selected-list-item {
  background-color: white;
  width: 150px;
  height: 30px;
  margin: 3px;
  text-align: center;
}
</style>