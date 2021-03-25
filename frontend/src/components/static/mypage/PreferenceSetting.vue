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
        <div class="list-1">
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
        <div class="list-2">
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
        <div class="list-3">
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
        <div class="list-4">
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
        <div class="list-5">
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
      </div>
      <!-- <div v-show="curStage == 2" class="preference-item2"></div> -->
      <div v-show="curStage == 3" class="preference-item3"></div>
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
  }),
  methods: {
    onClickStage(n) {
      this.curStage = n;
    },
    // 상단 stage 클릭시 stage 변경 함수
    isCurStage(n) {
      if (this.curStage == n) return true;
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
    isSelectPossible(n) {
      if (n == FIRST_STAGE) {
        if (this.lickCnt <= 2) return true;
        else return false;
      } else {
        if (this.hateCnt <= 2) return true;
        else return false;
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
            this.lickCnt++;
            this.likeList.push(item);
            item.isLike = true;
          }
        } else {
          // 3. 좋아한다고 했다가 취소하는 경우
          item.isLike = false;
          this.lickCnt--;
        }
      }
      // 2. 싫어한다고 선택하는 경우
      // 4. 싫어한다고 했다가 취소하는 경우
      else if (this.curStage == SECOND_STAGE) {
        if (item.isLike) return;
        if (!item.isHate) {
          if (!this.isSelectPossible(SECOND_STAGE)) {
            alert("선택 가능한 갯수를 초과했습니다.");
            return;
          } else {
            item.isHate = true;
            this.hateCnt++;
          }
        } else {
          // 3. 좋아한다고 했다가 취소하는 경우
          item.isHate = false;
          this.hateCnt--;
        }
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
}
.preference-stages-wrap {
  display: flex;
  flex-direction: row;
  justify-content: center;
  flex: 1 1 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  /* 상단 스테이지값 고정시 삭제 요망!!! */
  /* z-index: 10 !important; */
}

.stage {
  width: 65px;
  height: 65px;
  border-radius: 70%;
  background-color: white;
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
  left: -10px;
  margin-top: 160px;
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.preference-item1 > div {
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
</style>