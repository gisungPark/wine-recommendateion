<template>
  <div data-app>
    <v-row justify="center">
      <v-dialog v-model="reviewDialog" persistent max-width="1000px">
        <v-card>
          <div id="closeBtn" @click="close">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="24"
              height="24"
              viewBox="0 0 24 24"
            >
              <path
                d="M24 20.188l-8.315-8.209 8.2-8.282-3.697-3.697-8.212
                  8.318-8.31-8.203-3.666 3.666 8.321 8.24-8.206 8.313 3.666 3.666 8.237-8.318 8.285 8.203z"
              />
            </svg>
          </div>
          <div style="height: 50px"></div>
          <v-card-text>
            <v-row v-for="(review, idx) in review" :key="review + idx">
              <v-col>
                <ReviewItem :review="review" />
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import ReviewItem from "@/components/static/mypage/ReviewItem.vue";

export default {
  props: [],
  components: {
    ReviewItem,
  },
  computed: {
    ...mapState("reviewDialog", ["reviewDialog"]),
  },
  data: () => ({
    review: [
      {
        contents: "이거 너무 맛있네요1111111",
        nickname: "와인최고!!!!",
        profile:
          "https://i.pinimg.com/474x/a3/62/e4/a362e4507fbfe50404cd374594e48825.jpg",
        date: "2021.11.01",
      },
      {
        contents: "이거 너무 맛있네요2222222",
        nickname: "와인최고!!!!",
        profile:
          "https://i.pinimg.com/474x/a3/62/e4/a362e4507fbfe50404cd374594e48825.jpg",
        date: "2021.11.01",
      },
      {
        contents: "이거 너무 맛있네요33333",
        nickname: "와인최고!!!!",
        profile:
          "https://i.pinimg.com/474x/a3/62/e4/a362e4507fbfe50404cd374594e48825.jpg",
        date: "2021.11.01",
      },
      {
        contents:
          "ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",
        nickname: "와인최고!!!!",
        profile:
          "https://i.pinimg.com/474x/a3/62/e4/a362e4507fbfe50404cd374594e48825.jpg",
        date: "2021.11.01",
      },
      {
        contents: "이거 너무 맛있네요5555555555555",
        nickname: "와인최고!!!!",
        profile:
          "https://i.pinimg.com/474x/a3/62/e4/a362e4507fbfe50404cd374594e48825.jpg",
        date: "2021.11.01",
      },
      {
        contents: "이거 너무 맛있네요66666666666666666666",
        nickname: "와인최고!!!!",
        profile:
          "https://i.pinimg.com/474x/a3/62/e4/a362e4507fbfe50404cd374594e48825.jpg",
        date: "2021.11.01",
      },
    ],
  }),
  methods: {
    ...mapMutations("reviewDialog", ["SET_REVIEW_TOGGLE"]),
    close() {
      this.SET_REVIEW_TOGGLE();
    },
  },
};
</script>

<style scoped>
.frame {
  background-color: white;
  width: 100%;
  height: 600px;
}
#closeBtn {
  position: absolute;
  right: 15px;
  margin-top: 15px;
  cursor: pointer;
}
</style>