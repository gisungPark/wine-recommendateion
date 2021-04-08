<template>
  <div class="preference-fram">
    <div id="stage-line">
      <div
        id="preference-stages-line1"
        :class="{ 'active-stage': isCurStage(2) }"
      ></div>
    </div>
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
    </div>
    <div class="preference-fillter">
      <div v-show="curStage != 3" class="preference-item1">
        <!-- ######################### CARD LIST ################################## -->
        <div class="lists list-1">
          <Card
            class="list-item-cards"
            :class="{
              'list-item-cards-active': currentCardState(item),
              'list-item-cards-inactive': isInactive(item),
            }"
            v-for="(item, index) in this.list1"
            :key="item.name + index"
            @onClickCart="onCardActive(item)"
            :item="item"
          />
        </div>
        <div class="lists list-2">
          <Card
            class="list-item-cards"
            :class="{
              'list-item-cards-active': currentCardState(item),
              'list-item-cards-inactive': isInactive(item),
            }"
            v-for="(item, idx) in this.list2"
            :key="item + idx"
            @onClickCart="onCardActive(item)"
            :item="item"
          />
        </div>
        <div class="lists list-3">
          <Card
            class="list-item-cards"
            :class="{
              'list-item-cards-active': currentCardState(item),
              'list-item-cards-inactive': isInactive(item),
            }"
            v-for="(item, idx) in this.list3"
            :key="item + idx"
            @onClickCart="onCardActive(item)"
            :item="item"
          />
        </div>
        <div class="lists list-4">
          <Card
            class="list-item-cards"
            :class="{
              'list-item-cards-active': currentCardState(item),
              'list-item-cards-inactive': isInactive(item),
            }"
            v-for="(item, idx) in this.list4"
            :key="item + idx"
            @onClickCart="onCardActive(item)"
            :item="item"
          />
        </div>
        <div class="lists list-5">
          <Card
            class="list-item-cards"
            :class="{
              'list-item-cards-active': currentCardState(item),
              'list-item-cards-inactive': isInactive(item),
            }"
            v-for="(item, idx) in this.list5"
            :key="item + idx"
            @onClickCart="onCardActive(item)"
            :item="item"
          />
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
              <div id="cancleBtn" @click="removeItemFromList(1, item)"></div>
            </div>
          </div>
          <span v-show="curStage == 1" class="count-display"
            >{{ this.lickCnt }} / 3</span
          >
          <div v-show="curStage == 2" class="selected-list-hate">
            <div
              class="selected-list-item"
              v-for="(item, idx) in hateList"
              :key="item + idx"
            >
              <h1>{{ item.name }}</h1>
              <div id="cancleBtn" @click="removeItemFromList(2, item)"></div>
            </div>
          </div>
          <span v-show="curStage == 2" class="count-display"
            >{{ this.hateCnt }} / 3</span
          >
        </div>

        <!-- ############################################ -->
        <!-- ########## end 우측 선택된 아이템 목록  ########### -->

        <!-- ############################################ -->
        <!-- ########## start 와인 취향 ########### -->
        <button id="okBtn" @click="submit()">선택완료</button>
      </div>
      <!-- <div v-show="curStage == 2" class="preference-item2"></div> -->
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from "vuex";
import Card from "@/components/item/Card.vue";
import * as mypageApi from "@/api/mypageApi";

const FIRST_STAGE = 1,
  SECOND_STAGE = 2,
  THIRD_STAGE = 3;

export default {
  props: ["preferenceList", "isUpdate"],
  components: {
    Card,
  },
  watch: {
    isUpdate: function () {
      this.onInit();
    },
  },
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
  created() {
    this.onInit();
  },
  watch: {
    preferenceList: function () {
      this.onInit();
    },
  },
  mounted() {},
  computed: {},
  methods: {
    onInit() {
      for (var i = 0; i < this.preferenceList.length; i++) {
        if (this.preferenceList[i].isLike) {
          this.lickCnt++;
          this.likeList.push(this.preferenceList[i]);
        } else if (this.preferenceList[i].isHate) {
          this.hateCnt++;
          this.hateList.push(this.preferenceList[i]);
        }

        if (i % 5 == 0) this.list1.push(this.preferenceList[i]);
        else if (i % 5 == 1) this.list2.push(this.preferenceList[i]);
        else if (i % 5 == 2) this.list3.push(this.preferenceList[i]);
        else if (i % 5 == 3) this.list4.push(this.preferenceList[i]);
        else this.list5.push(this.preferenceList[i]);
      }
    },
    onClickStage(stage) {
      this.curStage = stage;
    },
    // 상단 stage 클릭시 stage 변경 함수
    isCurStage(stage) {
      if (this.curStage >= stage) return true;
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
          return item.flavorId === targe.flavorId;
        });
        removeIdx = this.likeList.indexOf(itemToFine);
        if (removeIdx > -1) {
          this.likeList.splice(removeIdx, 1);
          targe.isLike = false;
          this.lickCnt--;
        }
      } else if (stage == SECOND_STAGE) {
        itemToFine = this.hateList.find(function (item) {
          return item.flavorId === targe.flavorId;
        });
        removeIdx = this.hateList.indexOf(itemToFine);
        if (removeIdx > -1) {
          this.hateCnt--;
          targe.isHate = false;
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
          this.removeItemFromList(SECOND_STAGE, item);
        }
      }
    },
    async submit() {
      if (this.lickCnt < 1) {
        alert("좋아하는 향을 한가지 이상 선택해주세요!");
        return;
      }
      const preferenceDTO = {
        dislikeList: this.hateList,
        likeList: this.likeList,
      };
      try {
        const response = await mypageApi.updatePreference(preferenceDTO);
        if (response.status === 200) {
          if (
            window.confirm(
              "선호하는 향 선택을 완료했습니다. 추천페이지로 이동하시겠습니까?"
            )
          ) {
            this.$router.push({ name: "Recommendation" });
          }
        }
      } catch (error) {
        alert("취향 설정에 실패했습니다.");
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

#stage-line {
  width: 100%;
  position: fixed;
  top: 133px;
  left: calc(50% + 86px);
  transform: translateX(-50%);
  display: flex;
  flex-direction: row;
  justify-content: center;
}
#preference-stages-line1 {
  width: 32%;
  height: 1px;
  background-color: #4e4c4c;
}
/* #preference-stages-line2 {
  width: 18%;
  height: 1px;
  background-color: #4e4c4c;
} */
.preference-stages {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  position: fixed;
  top: 100px;
  left: calc(50% + 86px);
  transform: translateX(-50%);
  z-index: 10;
  pointer-events: none;
}
.preference-stages-wrap {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  /* 상단 스테이지값 고정시 삭제 요망!!! */
  /* position: relative;
  z-index: 10 !important; */
}

.stage {
  width: 65px;
  height: 65px;
  border-radius: 70%;
  background-color: #4e4c4c;
  z-index: 3;
  pointer-events: all;
}

.active-stage {
  background-color: white !important;
}
.active-stage-name {
  color: white !important;
}

.stage-name {
  color: #4e4c4c;
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
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.preference-item1 {
  margin-top: 130px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  position: relative;
}

.preference-item3 {
  width: 100%;
  height: 100%;
  margin-top: 130px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.preference-item3-item {
  width: 100%;
  height: 80px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-bottom: 50px;
}
.preference-item3-item > span {
  position: relative;
  top: -10px;
  margin-bottom: 2px;
  color: var(--basic-color-fill);
  font-size: 32px;
  font-weight: bold;
}
#item3-item-slider {
  width: 900px;
  display: flex;
  flex-direction: column;
}
#item3-item-slider > div {
  position: relative;
  display: flex;
  flex-direction: row;
  color: white;
}

#slider-left {
  font-size: 20px;
  position: absolute;
  right: 905px;
  top: 5px;
}

#slider-right {
  font-size: 20px;
  position: absolute;
  left: 905px;
  top: 5px;
}
.slider-value {
  position: absolute;
  top: -20px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.slider-value > span {
  color: white;
  font-size: 18px;
}
.lists {
  height: auto;
  width: 240px;
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
  height: 280px;
  margin-top: 20px;
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
  right: 48px;
  top: 250px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center;
}
.selected-list-title {
  width: 160px;
  display: flex;
  justify-content: center;
}

.selected-list-title > span {
  color: white;
  font-size: 28px;
  font-weight: bold;
  margin-bottom: 10px;
  text-align: center;
}
.selected-list-like,
.selected-list-hate {
  width: 100%;
  min-height: 130px;
  border: 2px solid var(--basic-color-key);
  border-top: 0px;
  border-radius: 1em;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.selected-list-item {
  width: 140px;
  height: 30px;
  margin: 3px;
  background-color: var(--basic-color-key);
  text-align: center;
  border-radius: 1em;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}
.selected-list-item > h1 {
  margin-left: 12px;
  color: #e4e4e4;
  font-size: 18px;
  font-weight: 600;
}

.count-display {
  position: relative;
  left: 45px;
  font-size: 22px;
  font-weight: 900;
  color: white;
}

#cancleBtn {
  width: 16px;
  height: 16px;
  margin-right: 5px;
  background-image: url(../../../assets/images/cross-icon.png);
}

#okBtn {
  width: 150px;
  height: 45px;
  border-radius: 1em;
  font-size: 20px;
  font-weight: bold;
  color: white;
  background-color: var(--basic-color-bg2);
  position: fixed;
  right: 48px;
  top: 450px;
}
</style>
