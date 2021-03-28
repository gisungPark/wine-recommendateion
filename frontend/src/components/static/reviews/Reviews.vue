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
          <div style="height: 70px"></div>
          <v-card-text>
            <v-row v-for="(review, idx) in review" :key="review + idx">
              <v-col>
                <ReviewItem :review="review" />
              </v-col>
            </v-row>
          </v-card-text>

          <div class="writeBtn-wrap"></div>
          <v-card-actions>
            <v-btn text id="writeBtn" @click="clickWriteBtn"></v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <ReviewWrite />
    </v-row>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import ReviewItem from "@/components/static/reviews/ReviewItem.vue";
import ReviewWrite from "@/components/static/reviews/ReviewWrite.vue";

export default {
  name: "Reviews",
  props: [],
  components: {
    ReviewItem,
    ReviewWrite,
  },
  computed: {
    ...mapState("reviewDialog", ["reviewDialog"]),
  },
  data: () => ({
    review: [
      {
        contents: `Elementum eu facilisis sed odio morbi quis commodo odio. 
        Mauris rhoncus aenean vel elit scelerisque mauris pellentesque. 
        Accumsan sit amet nulla facilisi morbi tempus. Suscipit tellus mauris
         a diam maecenas sed enim ut sem. Turpis egestas maecenas pharetra convallis
          posuere. Nibh venenatis cras sed felis eget velit aliquet. Elementum nisi 
          quis eleifend quam adipiscing. Amet cursus sit amet dictum sit amet justo 
          donec enim. Odio pellentesque diam volutpat commodo sed egestas egestas fringilla.
           Habitant morbi tristique senectus et netus et malesuada. Imperdiet dui accumsan 
           sit amet nulla facilisi morbi tempus iaculis. 
        c turpis egestas maecenas pharetra.`,
        nickname: "와인최고!!!!",
        profile:
          "https://i.pinimg.com/474x/a3/62/e4/a362e4507fbfe50404cd374594e48825.jpg",
        date: "2021.11.01",
      },
      {
        contents: `Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
        sed do eiusmod tempor incididunt ut 
        labore et dolore magna aliqua. Nisl tincidunt eget nullam non. `,
        nickname: "와인최고!!!!",
        profile:
          "https://i.pinimg.com/474x/a3/62/e4/a362e4507fbfe50404cd374594e48825.jpg",
        date: "2021.11.01",
      },
      {
        contents: `Nunc pulvinar sapien et ligula ullamcorper malesuada proin. Neque 
        convallis a cras semper auctor. Libero id faucibus nisl tincidunt eget. Leo a 
        diam sollicitudin tempor id. A lacus vestibulum sed arcu non odio euismod lacinia.`,
        nickname: "와인최고!!!!",
        profile:
          "https://i.pinimg.com/474x/a3/62/e4/a362e4507fbfe50404cd374594e48825.jpg",
        date: "2021.11.01",
      },
      {
        contents: `Senectus et netus et malesuada. Nunc pulvinar 
          sapien et ligula ullamcorper malesuada proin. Neque 
          convallis a cras semper auctor. Libero id faucibus nisl tincidunt eget. 
          Leo a diam sollicitudin tempor id. A lacus vestibulum sed arcu non odio 
          euismod lacinia. In tellus integer feugiat scelerisque. Feugiat in fermentum 
          posuere urna nec tincidunt praesent. Porttitor rhoncus dolor purus non enim praesent 
          elementum facilisis. Nisi scelerisque eu ultrices vitae auctor eu augue ut lectus. 
          Ipsum faucibus vitae aliquet nec ullamcorper sit amet risus. Et malesuada fames ac 
          turpis egestas sed. Sit amet nisl suscipit adipiscing bibendum est ultricies. Arcu 
          ac tortor dignissim convallis aenean et tortor at. Pretium viverra suspendisse potenti 
          nullam ac tortor vitae purus. Eros donec ac odio tempor orci dapibus ultrices. Elementum 
          nibh tellus molestie nunc. Et magnis dis parturient montes nascetur. Est placerat in 
          egestas erat imperdiet. Consequat interdum varius sit amet mattis vulputate enim.`,

        nickname: "와인최고!!!!",
        profile:
          "https://i.pinimg.com/474x/a3/62/e4/a362e4507fbfe50404cd374594e48825.jpg",
        date: "2021.11.01",
      },
      {
        contents: `Senectus et netus et malesuada. Nunc pulvinar sapien et ligula ullamcorper
        malesuada proin. Neque convallis a cras semper auctor. Libero id faucibus nisl tincidunt eget. 
        Leo a diam sollicitudin tempor id. A lacus vestibulum sed arcu non odio euismod 
        lacinia. In tellus integer feugiat scelerisque. Feugiat in fermentum posuere urna 
        nec tincidunt praesent. Porttitor rhoncus dolor purus non enim praesent elementum 
        facilisis. Nisi scelerisque eu ultrices vitae auctor eu augue ut lectus. Ipsum 
        faucibus vitae aliquet nec ullamcorper sit amet risus. Et malesuada fames ac 
        turpis egestas sed. Sit amet nisl suscipit adipiscing bibendum est ultricies. 
        Arcu ac tortor dignissim convallis aenean et tortor at. Pretium viverra suspendisse 
        potenti nullam ac tortor vitae purus. Eros donec ac odio tempor orci dapibus ultrices.
         Elementum nibh tellus molestie nunc. Et magnis dis parturient montes nascetur. 
         Est placerat in egestas erat imperdiet. Consequat interdum varius sit amet mattis 
         vulputate enim.`,

        nickname: "와인최고!!!!",
        profile:
          "https://i.pinimg.com/474x/a3/62/e4/a362e4507fbfe50404cd374594e48825.jpg",
        date: "2021.11.01",
      },
      {
        contents: `Quis hendrerit dolor magna eget est lorem ipsum dolor sit. 
        Volutpat odio facilisis mauris sit amet massa.`,
        nickname: "와인최고!!!!",
        profile:
          "https://i.pinimg.com/474x/a3/62/e4/a362e4507fbfe50404cd374594e48825.jpg",
        date: "2021.11.01",
      },
    ],
  }),
  methods: {
    ...mapMutations("reviewDialog", [
      "SET_REVIEW_TOGGLE",
      "SET_REVIEW_WRITE_TOGGLE",
    ]),
    clickWriteBtn() {
      this.SET_REVIEW_WRITE_TOGGLE();
    },
    close() {
      this.SET_REVIEW_TOGGLE();
    },
  },
};
</script>

<style scoped>
#closeBtn {
  position: absolute;
  right: 15px;
  margin-top: 15px;
  cursor: pointer;
}
.writeBtn-wrap {
  height: 35px;
  position: relative;
}
#writeBtn {
  position: fixed;
  right: 300px;
  bottom: 55px;
  width: 64px;
  height: 64px;
  background-image: url(../../../assets/images/plus-icon.png);
  background-position: center;
  background-size: contain;
}
#writeBtn:hover {
  transform: scale(1.11);
}
</style>