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
                class="blue"
                d="M24 20.188l-8.315-8.209 8.2-8.282-3.697-3.697-8.212
                  8.318-8.31-8.203-3.666 3.666 8.321 8.24-8.206 8.313 3.666 3.666 8.237-8.318 8.285 8.203z"
              />
            </svg>
          </div>
          <div style="height: 70px"></div>
          <v-card-text>
            <!-- #########################################################  -->
            <!-- 리뷰 item #################################################  -->
            <v-row v-for="(review, idx) in review" :key="review + idx">
              <v-col>
                <div class="review-item-frame">
                  <div class="review-content">
                    <span>
                      {{ review.contents }}
                    </span>
                  </div>
                  <div style="height: 30px"></div>
                  <div class="review-writer">
                    <div class="writer-info">
                      <div class="writer-img">
                        <img :src="review.profile" alt="" />
                      </div>
                      <span class="writer-nickname">
                        {{ review.nickname }}
                      </span>
                      <span class="writing-date">{{ review.date }}</span>
                    </div>
                    <!-- ######################################################## -->
                    <!-- 가성비 아이콘, 별점 아이콘  -->
                    <div class="review-rating-wrap">
                      <div class="star-rating">
                        <svg
                          class="star"
                          xmlns="http://www.w3.org/2000/svg"
                          width="24"
                          height="24"
                          viewBox="0 0 24 24"
                        >
                          <path
                            d="M12 .587l3.668 7.568 8.332 1.151-6.064 5.828 1.48 8.279-7.416-3.967-7.417 3.967 1.481-8.279-6.064-5.828 8.332-1.151z"
                          />
                        </svg>
                        <span style="font-weight: 600">4.5</span>
                      </div>
                      <div v-show="review.priceScore == 1" class="cost-rating">
                        <div class="cost-rating-icon blue">
                          <img src="@/assets/images/smiley-1.png" />
                        </div>
                        <span>가성비</span>
                      </div>
                      <div v-show="review.priceScore == 2" class="cost-rating">
                        <div class="cost-rating-icon orange">
                          <img src="@/assets/images/smiley-2.png" />
                        </div>
                        <span>가성비</span>
                      </div>
                      <div v-show="review.priceScore == 3" class="cost-rating">
                        <div class="cost-rating-icon red">
                          <img src="@/assets/images/smiley-3.png" />
                        </div>
                        <span>가성비</span>
                      </div>
                    </div>
                  </div>
                </div>
              </v-col>
            </v-row>

            <!-- 리뷰 item end #############################################  -->
            <!-- #########################################################  -->
          </v-card-text>

          <div class="writeBtn-wrap"></div>
          <v-card-actions>
            <v-btn
              text
              id="writeBtn"
              class="writePop"
              @click="clickWriteBtn"
            ></v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <ReviewWrite />
    </v-row>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import ReviewWrite from "@/components/static/reviews/ReviewWrite.vue";

export default {
  name: "Reviews",
  props: [],
  components: {
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
        priceScore: 1,
      },
      {
        contents: `Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
        sed do eiusmod tempor incididunt ut 
        labore et dolore magna aliqua. Nisl tincidunt eget nullam non. `,
        nickname: "와인최고!!!!",
        profile:
          "https://i.pinimg.com/474x/a3/62/e4/a362e4507fbfe50404cd374594e48825.jpg",
        date: "2021.11.01",
        priceScore: 3,
      },
      {
        contents: `Nunc pulvinar sapien et ligula ullamcorper malesuada proin. Neque 
        convallis a cras semper auctor. Libero id faucibus nisl tincidunt eget. Leo a 
        diam sollicitudin tempor id. A lacus vestibulum sed arcu non odio euismod lacinia.`,
        nickname: "와인최고!!!!",
        profile:
          "https://i.pinimg.com/474x/a3/62/e4/a362e4507fbfe50404cd374594e48825.jpg",
        date: "2021.11.01",
        priceScore: 1,
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
        priceScore: 1,
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
        priceScore: 2,
      },
      {
        contents: `Quis hendrerit dolor magna eget est lorem ipsum dolor sit. 
        Volutpat odio facilisis mauris sit amet massa.`,
        nickname: "와인최고!!!!",
        profile:
          "https://i.pinimg.com/474x/a3/62/e4/a362e4507fbfe50404cd374594e48825.jpg",
        date: "2021.11.01",
        priceScore: 2,
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
  justify-content: space-between;
  align-items: center;
}
.writer-info {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-left: 12px;
  margin-bottom: 5px;
}

.writer-img {
  width: 40px;
  height: 40px;
  border-radius: 70%;
  overflow: hidden;
}

.writer-img > img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.writer-nickname {
  font-size: 20px;
  font-weight: 550;
  margin-left: 3px;
  color: var(--basic-color-bg2);
}
.writing-date {
  margin-left: 3px;
  margin-top: 6px;
  font-size: 16px;
  color: #808080;
}

.review-rating-wrap {
  display: flex;
  position: relative;
  right: 30px;
  bottom: 3px;
}

/* ########################## */
/* 별점 아이콘!! */
.star-rating {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
  right: 5px;
}
.star {
  fill: #f9ba01;
  width: 38px;
  height: 38px;
}
/* ########################## */
/* 가성비!! */
.cost-rating {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.cost-rating > span {
  font-size: 13px;
  color: #626262;
  font-weight: 900;
  margin-top: 2px;
}

.cost-rating-icon {
  height: 38px;
  width: 38px;
  border-radius: 70%;
  overflow: hidden;
  /* background-color: #faad50; */
}

.blue {
  background-color: #4169e1;
}
.orange {
  background-color: #fda613;
}
.red {
  background-color: #eb3763;
}
.cost-rating-icon > img {
  height: 100%;
  width: 100%;
  object-fit: cover;
}

.writeBtn-wrap {
  height: 35px;
  position: relative;
}
#writeBtn {
  position: fixed;
  right: 20%;
  bottom: 8%;
  width: 64px;
  height: 64px;
  background-image: url(../../../assets/images/plus-icon.png);
  background-position: center;
  background-size: contain;
  opacity: 0;
}
#writeBtn:hover {
  transform: scale(1.11);
}
</style>