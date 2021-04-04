<template>
  <div ref="wineDetails" id="wine-details" :style="{ color: fontColor }">
    <img :src="`${this.s3url}${detail.wineDto.wineId}.png`" :alt="`${detail.wineDto.kname} 와인의 이미지`" id="detail-wine-img" />
    <section id="detail-section-0" :style="{ backgroundColor: backgroundColor }">
      <!-- 왼쪽 -->
      <div class="sec-0-part">
        <p class="ename">
          <span>{{ detail.wineDto.year }}</span>
          {{ detail.wineDto.ename }}
        </p>
        <div class="bar" :style="{ backgroundColor: fontColor }"></div>
        <p class="kname">{{ detail.wineDto.kname }}</p>
        <div class="sub-info">
          <svg :style="{ fill: fontColor }" class="icon" height="512" viewBox="0 0 505 505" width="512" xmlns="http://www.w3.org/2000/svg">
            <path
              d="M494 80l-6-9c-2-2-2-5-3-9-1-7-3-17-10-23-6-4-14-4-20-5l-13-1-7-8c-5-6-10-13-17-15-8-2-16 0-23 3-4 1-8 3-10 2-4 0-8-3-12-5-5-4-12-8-19-7-10 0-16 7-22 13l-7 6-10 3c-7 2-15 4-20 10h-14c-3-5-6-10-10-13-9-7-18-10-28-9-8 2-15 7-21 12l-1-18a7 7 0 10-15 2c0 1 6 51-16 85-13-2-27 1-39 8-8-11-19-19-32-22a56 56 0 00-69 46c-14 3-27 9-36 20a8 8 0 1011 10 44 44 0 0167 1 58 58 0 00-21 45c-14 2-27 9-36 18-14-8-22-24-20-41a7 7 0 10-15-2c-2 22 8 44 26 56a58 58 0 0010 68 58 58 0 00-6 54 52 52 0 00-13 73 51 51 0 0013 64 51 51 0 0038 12c21-2 39-17 45-38a52 52 0 0059-52 58 58 0 0039-57l2-1c28-2 49-27 48-55a52 52 0 0058-57c-1-13-7-25-17-34a58 58 0 008-65 8 8 0 00-13 7 43 43 0 01-38 63 58 58 0 00-43-46 44 44 0 0121-35 43 43 0 0142-1 8 8 0 006-13 59 59 0 00-54 0c-6-9-15-17-26-22 9-15 13-32 15-47 4-6 14-21 26-23 5-1 10 1 15 5l5 7c-15 7-26 21-29 33s3 22 15 26h6c8 0 16-5 21-15 5-9 7-22 6-34h8c2 6 6 11 10 15l7 8 2 9c1 8 3 17 10 23 6 5 14 5 21 5 5 0 9 0 12 2 3 1 5 5 8 8 4 6 9 12 17 15 8 2 15-1 22-3l11-3 11 6c5 3 11 7 18 7h2c9-1 16-8 21-13l7-6 10-4c9-2 19-5 22-15s-4-18-10-24zM73 128l-8-2 1-3a41 41 0 1178 23c-13-3-27-2-39 4-8-11-19-19-32-22zm14 64a43 43 0 0153-31c11 3 19 9 25 18a58 58 0 00-22 44h-6c-13 1-24 7-34 15a43 43 0 01-16-46zm59 133h-4a58 58 0 00-43-47 43 43 0 0186 0c2 23-15 44-39 47zm-58 53a43 43 0 01-47-40 43 43 0 0143-47 43 43 0 0143 40 43 43 0 01-39 47zM36 249c4-17 19-30 36-32 4 13 11 24 21 32-5 8-8 17-9 27h-5c-12 1-24 6-33 14a43 43 0 01-10-41zm1 119a59 59 0 0037 24l-4 9a52 52 0 00-43 14 37 37 0 0110-47zm29 121a37 37 0 01-40-33 37 37 0 0134-40h8v4a51 51 0 0031 42c-5 15-17 26-33 27zm82-50a37 37 0 01-52 4 37 37 0 01-5-51l12-2a59 59 0 0054 27c-1 8-4 16-9 22zm38-52c-7 9-18 14-29 15-16 2-32-6-40-19a59 59 0 0025-43 59 59 0 0038-15 44 44 0 016 62zm26-46l-3 1c-4-11-10-21-19-28 6-9 10-19 10-30h1c11 0 22-4 31-9a38 38 0 01-20 67zm58-55c-4 1-9 0-13-1-3-7-7-14-13-20a58 58 0 0015-35c12 0 23-4 32-10a38 38 0 01-21 66zm-31-83c6 10 7 22 4 33-5 20-24 34-44 33a58 58 0 00-41-44 44 44 0 0154-42c12 3 21 10 27 20zm-20-74a58 58 0 00-18 38c-8 0-16 2-23 5-5-8-12-15-20-20a57 57 0 000-37 43 43 0 0161 14zm47-52c-3 6-6 8-10 7s-6-3-4-8c1-7 9-16 18-21 1 7 0 15-4 22zm224 23c-1 2-7 4-12 5l-13 4c-4 2-8 6-11 10-4 4-8 8-12 8l-10-5c-5-3-11-7-17-8h-3c-5 0-10 2-15 4-5 1-10 3-13 2s-6-5-9-9c-4-5-7-10-13-13s-13-3-19-3l-11-1c-3-3-4-9-5-14l-4-14-9-11v-1l91 28a8 8 0 105-14l-92-28a1136 1136 0 0114-4l11-10c4-4 8-8 12-9l10 5c5 4 11 7 18 8 6 1 12-1 17-3l13-3c3 1 6 6 9 10 4 4 8 9 13 12 6 3 13 3 19 3l12 2c3 2 4 8 4 14 1 5 2 10 5 14l8 11c3 3 8 8 7 10z"
            />
          </svg>
          <div class="data">
            <p class="key">Grape</p>
            <p class="value">{{ detail.wineDto.grape.kname }}</p>
          </div>
          <!-- modal -->
          <div ref="modalInitPosition"></div>
          <div class="modal-background" :class="{ 'modal-background-active': grapeInfoState }" @click="clickedModalBG"></div>
          <div
            ref="modalWrap"
            class="grape-info-modal real-box-shadow"
            :class="{ 'grape-info-modal-active': grapeInfoState, 'transition-none': modalPosition.transitionState }"
          >
            <svg
              class="container-shadow"
              :class="{ 'svg-active': grapeInfoState }"
              :style="{ fill: fontColor }"
              xmlns="http://www.w3.org/2000/svg"
              width="24"
              height="24"
              viewBox="0 0 24 24"
              @click="clickedInfoIcon"
            >
              <path
                d="M12 0c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm-.001 5.75c.69 0 1.251.56 1.251 1.25s-.561 1.25-1.251 1.25-1.249-.56-1.249-1.25.559-1.25 1.249-1.25zm2.001 12.25h-4v-1c.484-.179 1-.201 1-.735v-4.467c0-.534-.516-.618-1-.797v-1h3v6.265c0 .535.517.558 1 .735v.999z"
              />
            </svg>
            <transition name="fade">
              <p v-if="grapeInfoTextState" class="grape-info-modal-text">
                {{ detail.wineDto.grape.detail }}
              </p>
            </transition>
          </div>
          <!-- modal end -->
        </div>
        <div class="sub-info">
          <svg :style="{ fill: fontColor }" class="icon" xmlns="http://www.w3.org/2000/svg" data-name="Layer 1" width="512" height="512" viewBox="0 0 512 512">
            <path
              d="M441 135a22 22 0 1022 22 22 22 0 00-22-22zm0 32a10 10 0 1110-10 10 10 0 01-10 10zM184 80a26 26 0 10-26-26 26 26 0 0026 26zm0-40a14 14 0 11-14 14 14 14 0 0114-14zM285 232a26 26 0 10-26 26 26 26 0 0026-26zm-26 14a14 14 0 1114-14 14 14 0 01-14 14zM87 162a26 26 0 10-26-26 26 26 0 0026 26zm0-40a14 14 0 11-14 14 14 14 0 0114-14z"
            />
            <path
              d="M479 155c-1-20-18-35-37-35a36 36 0 00-24 9 250 250 0 00-164-69 233 233 0 00-25 1 43 43 0 001-8c-2-24-21-43-45-43s-43 19-44 43a47 47 0 009 30l1 2a301 301 0 00-31 20 44 44 0 00-32-14c-23 0-43 19-44 43a47 47 0 009 30l7 10c-17 33-27 72-27 107a221 221 0 10442 0c0-25-7-62-15-86l11-15a38 38 0 008-25zM153 53c1-15 13-31 32-31s32 16 33 31a34 34 0 01-7 23l-26 34-25-34a35 35 0 01-7-23zm-97 82c1-16 13-32 32-32s31 16 32 32a34 34 0 01-7 22l-25 34-25-34a35 35 0 01-7-22zm12 50l15 20a6 6 0 0010 0l30-41a45 45 0 004-49 288 288 0 0132-21l21 29a6 6 0 0010 0l30-40a48 48 0 006-9l7-1 25 36a24 24 0 010 29l-8 9a16 16 0 01-15 6l-8-1a29 29 0 00-29 14 16 16 0 01-16 8l-18-2a30 30 0 00-29 13 27 27 0 00-1 30l12 19a25 25 0 0022 12 25 25 0 0011-3l13-7c6-3 16-3 24 0a48 48 0 009 24l30 41a6 6 0 0010 0l15-21 2 3 3 3 15 19a8 8 0 01-1 11l-23 18a38 38 0 00-14 19l-7 25a16 16 0 01-3 5l-8 9a44 44 0 00-10 24 12 12 0 01-7 10 6 6 0 01-8-4l-7-43a33 33 0 00-7-15l-26-34a30 30 0 01-5-10l-9-32a33 33 0 00-23-22l-1-1a24 24 0 01-11-6l-16-15a36 36 0 01-6-8 36 36 0 00-38-17 6 6 0 00-1 0l-16 5a235 235 0 0115-39zm160 46c1-16 13-31 32-31s31 15 32 31a34 34 0 01-7 22l-25 34-25-34a35 35 0 01-7-22zm235 50a209 209 0 01-418 0 207 207 0 015-43l23-7a24 24 0 0125 11 48 48 0 008 11l16 15a36 36 0 0015 9h2a21 21 0 0115 15l8 31a42 42 0 008 14l26 34a21 21 0 014 10l7 43a18 18 0 0025 13 24 24 0 0014-19 32 32 0 017-18l8-9a28 28 0 005-10l8-25a26 26 0 019-13l23-18a20 20 0 003-28l-15-18-2-3-4-6 7-10a46 46 0 009-30c-1-24-21-42-44-42-21 0-39 15-44 35-9-3-21-2-30 2l-12 7a13 13 0 01-17-4l-13-20a15 15 0 010-16 18 18 0 0118-8l19 2a28 28 0 0028-13 17 17 0 0117-9l8 1a28 28 0 0026-11l7-9a36 36 0 001-42l-21-31h7c28 0 58 7 88 20a251 251 0 0126 14l-16 28a22 22 0 003 26 29 29 0 0018 9l3 1a15 15 0 0112 10 14 14 0 01-4 15l-29 25a28 28 0 00-9 21v7a24 24 0 0011 20 13 13 0 016 10v7a22 22 0 0022 20h17a36 36 0 0033-22l1-2a13 13 0 0112-8h4l2-1h8l1 9zm-2-21h-8l-2 1h-3a25 25 0 00-23 15l-1 2a24 24 0 01-22 15h-17a10 10 0 01-10-9l-1-7a25 25 0 00-11-19 12 12 0 01-5-10v-7a16 16 0 015-12l29-25a26 26 0 007-27 27 27 0 00-22-19l-3-1a17 17 0 01-10-5 10 10 0 01-1-12l15-27a224 224 0 0132 26 38 38 0 00-5 16 39 39 0 008 25l24 33a6 6 0 0010 0l4-6a329 329 0 0110 53zm1-87l-20 26-19-26a27 27 0 01-6-17c1-12 11-24 25-24s24 12 25 24a26 26 0 01-5 17z"
            />
          </svg>
          <div class="data">
            <p class="key">From</p>
            <p class="value">{{ detail.wineDto.area }}</p>
          </div>
        </div>
        <div class="sub-info">
          <svg :style="{ fill: fontColor }" class="icon" height="512" viewBox="0 0 24 24" width="512" xmlns="http://www.w3.org/2000/svg">
            <path
              d="M10.5 24A5.5 5.5 0 017 14.3V3.5a3.5 3.5 0 017 0v10.8c1.3 1 2 2.6 2 4.2 0 3-2.5 5.5-5.5 5.5zm0-23A2.5 2.5 0 008 3.5v11c0 .2 0 .3-.2.4a4.5 4.5 0 105.4 0 .5.5 0 01-.2-.4v-11C13 2.1 11.9 1 10.5 1z"
            />
            <path d="M10.5 21a2.5 2.5 0 110-5 2.5 2.5 0 010 5zm0-4a1.5 1.5 0 100 3 1.5 1.5 0 000-3z" />
            <path
              d="M10.5 17a.5.5 0 01-.5-.5v-11a.5.5 0 011 0v11c0 .3-.2.5-.5.5zM18.5 4h-2a.5.5 0 010-1h2a.5.5 0 010 1zM18.5 8h-2a.5.5 0 010-1h2a.5.5 0 010 1zM18.5 12h-2a.5.5 0 010-1h2a.5.5 0 010 1z"
            />
          </svg>
          <div class="data">
            <p class="key">Temperature</p>
            <p class="value">{{ detail.wineDto.temper }}</p>
          </div>
        </div>
        <div class="sub-info">
          <svg :style="{ fill: fontColor }" class="icon" height="511pt" viewBox="-9 0 511 512" width="511pt" xmlns="http://www.w3.org/2000/svg">
            <path
              d="M491 464l-31-40c-29-37-65-67-107-89 22-22 37-51 43-83l25-151c1-4-1-8-5-11L262 1c-4-2-8-1-12 1l-118 98c-32 26-52 65-55 106v1c-4-11-7-21-9-32-4-16-17-27-34-27a34 34 0 00-33 38 407 407 0 00112 233l-20 33-53-31a10 10 0 10-10 18l124 72a10 10 0 0014-4c2-5 1-11-4-14l-54-31 18-30c21 18 45 32 70 43l86 36a10 10 0 0013-5c2-5 0-11-5-13l-86-37c-37-15-71-40-97-71-48-57-79-127-88-201-1-4 0-8 3-11s6-5 10-5c7 0 13 4 14 11a354 354 0 0096 174l38 38a32 32 0 0045 0c12-13 12-33 0-46l-64-64a19 19 0 010-27c7-7 19-7 27 0l14 15c32 31 69 56 111 72 51 19 96 52 130 95l30 40a10 10 0 1016-12zM145 115l113-93 25 15-54 44a10 10 0 0013 15l60-49 98 57-17 102c-7 5-58 37-140-7-52-28-93-27-118-21-10 2-18 6-25 9 7-28 22-53 45-72zm59 265c-3 0-6-1-8-3l-8-8 2 1c8 2 15 5 23 6v1l-9 3zm14-126l-14-14a39 39 0 00-55 55l59 59-10-3c-19-7-37-17-53-30a133 133 0 01-49-109c12-8 61-36 138 5 35 19 66 25 90 25a140 140 0 0055-11l-3 18c-5 30-20 57-43 77l-10-4c-40-15-75-38-105-68zm0 0"
            />
            <path d="M209 121c2 0 5-1 7-3s3-4 3-7a10 10 0 00-10-10c-3 0-5 1-7 3s-3 4-3 7 1 5 3 7 4 3 7 3zm0 0" />
          </svg>
          <div class="data">
            <p class="key">Flavor</p>
            <span class="value" v-for="(item, index) in this.detail.flavorList" :key="item.flavorId + index">{{ item.name }}, </span>
          </div>
        </div>
        <div class="bar" :style="{ backgroundColor: fontColor }"></div>
      </div>
      <!-- 가운데 -->
      <div class="wine-blank">
        <div class="info-thumb-type">
          <div class="circle"></div>
          <span>{{ type }}</span>
        </div>
      </div>
      <!-- 오른쪽 -->
      <div class="sec-0-part">
        <p class="price">₩ {{ detail.wineDto.price | currency }}</p>
        <div class="bar" :style="{ backgroundColor: fontColor }"></div>
        <div class="avg">
          <span>{{ detail.wineDto.avg }}</span>
          <div class="star">
            <div class="star-rate" :style="{ width: starRate + 'rem' }"></div>
            <div class="star-rate-bg"></div>
          </div>
        </div>
        <div class="bar" :style="{ backgroundColor: fontColor }"></div>
        <Scrap class="scrap-btn" :scraped="true" />
      </div>
    </section>
    <!--******************************************************************** -->
    <!--******************************************************************** -->
    <!--******************************************************************** -->
    <section id="detail-section-1" :style="{ backgroundColor: backgroundColor }">
      <div class="section-title">Taste</div>
      <div class="sec-1-part">
        <div class="line left-top" :style="{ backgroundColor: fontColor }">
          <div class="detail-torus"></div>
          <span>SWEET</span>
          <div class="frame">
            <div v-for="n in 4" :key="n" class="line" :style="{ backgroundColor: backgroundColor }"></div>
            <div class="gauge" ref="sweet"></div>
          </div>
        </div>
        <div class="line left-bottom" :style="{ backgroundColor: fontColor }">
          <div class="detail-torus"></div>
          <span>ACIDITY</span>
          <div class="frame">
            <div v-for="n in 4" :key="n" class="line" :style="{ backgroundColor: backgroundColor }"></div>
            <div class="gauge" ref="acidity"></div>
          </div>
        </div>
      </div>
      <div class="sec-1-part">
        <div class="line top-left" :style="{ backgroundColor: fontColor }"></div>
        <div class="line top-right" :style="{ backgroundColor: fontColor }"></div>
        <div class="line bottom-left" :style="{ backgroundColor: fontColor }"></div>
        <div class="line bottom-right" :style="{ backgroundColor: fontColor }"></div>
        <div class="circle-bg" :style="{ backgroundColor: fontColor }"></div>
      </div>
      <div class="sec-1-part">
        <div class="line right-top" :style="{ backgroundColor: fontColor }">
          <div class="detail-torus"></div>
          <span>TANNIN</span>
          <div class="frame">
            <div v-for="n in 4" :key="n" class="line" :style="{ backgroundColor: backgroundColor }"></div>
            <div class="gauge" ref="tannin"></div>
          </div>
        </div>
        <div class="line right-bottom" :style="{ backgroundColor: fontColor }">
          <div class="detail-torus"></div>
          <span>BODY</span>
          <div class="frame">
            <div v-for="n in 4" :key="n" class="line" :style="{ backgroundColor: backgroundColor }"></div>
            <div class="gauge" ref="body"></div>
          </div>
        </div>
      </div>
    </section>
    <!--******************************************************************** -->
    <!--******************************************************************** -->
    <!--******************************************************************** -->
    <section id="detail-section-2" :style="{ backgroundColor: backgroundColor }">
      <div class="section-title">Detail</div>
      <div class="sec-2-part-left">
        <div v-for="n in 2" :key="n" class="food-svg-frame">
          <FoodSvgs :foodId="n" />
          <p>이탈리아 음식</p>
        </div>
        <!-- <FoodSvgs v-for="(item, index) in detail.foodList" :key="item.foodId + index" :foodId="item.foodId" /> -->
      </div>
      <div class="sec-2-part-right">
        <p>
          <span>"</span>
          {{ detail.wineDto.detail }}
        </p>
      </div>
    </section>

    <!--******************************************************************** -->
    <!--******************************************************************** -->
    <!--******************************************************************** -->
    <section id="detail-section-3" :style="{ backgroundColor: backgroundColor }">
      <div class="section-title">Review</div>
      <div class="sec-3-part">
        <div class="avg">
          <span class="b-title">{{ detail.wineDto.avg.toFixed(1) }}</span>
          <div class="star">
            <div class="star-rate" :style="{ width: starRate + 'rem' }"></div>
            <div class="star-rate-bg"></div>
          </div>
        </div>
      </div>
      <div class="wine-blank"></div>
      <div class="sec-3-part">
        <div class="review-data-frame">
          <div class="review-item" v-for="(review, index) in detail.reviewList" :key="review.nickname + index">
            <div class="profile">
              <img v-if="review.userImg != null" class="profile-img" :src="review.userImg" alt="프로필 이미지" />
              <div v-else class="profile-img"></div>
              <p>{{ review.nickname }}</p>
            </div>
            <div class="review-data">
              <div class="star">
                <div class="star-rate" :style="{ width: starRateReview[index] + 'rem' }"></div>
                <div class="star-rate-bg"></div>
              </div>
              <div class="review-text-frame">
                <div class="balloon">
                  <p>{{ review.contents }}</p>
                </div>
              </div>
            </div>
          </div>
          <div class="review-show">
            <span class="b-title" @click="clickedShowReview">Show more review</span>
          </div>
        </div>
      </div>
    </section>
    <!--******************************************************************** -->
    <!--******************************************************************** -->
    <!--******************************************************************** -->
    <section id="detail-section-4" :style="{ backgroundColor: backgroundColor }">
      <div class="section-title">
        More
        <br />
        Wines?
      </div>
      <swiper class="swiper" :options="swiperOption2" ref="moreWineSwiper">
        <swiper-slide v-for="(item, index) in detail.moreWineList" :key="item.wineId + index" class="slide"><WineItem :wine="item"/></swiper-slide>
        <div class="swiper-button-prev swiper-button-white" slot="button-prev"></div>
        <div class="swiper-button-next swiper-button-white" slot="button-next"></div>
        <div class="swiper-pagination" slot="pagination"></div>
      </swiper>
    </section>
  </div>
</template>

<script>
import common from '@/assets/js/common.js';
import Scrap from '@/components/button/Scrap.vue';
import { Swiper, SwiperSlide } from 'vue-awesome-swiper';
import 'swiper/css/swiper.css';

import WineItem from '@/components/articles/WineItem.vue';
import FoodSvgs from '@/components/articles/FoodSvgs.vue';

import { mapState, mapActions } from 'vuex';

export default {
  name: 'WineDetails',
  filters: {
    currency(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
  },
  components: {
    Scrap,
    Swiper,
    SwiperSlide,
    WineItem,
    FoodSvgs,
  },
  data() {
    return {
      id: '',
      backgroundColor: '',
      fontColor: '',
      type: 'none',
      // section 0
      starRate: 3,
      starRateReview: [],
      scrap: true,
      // section 1
      modalPosition: {
        top: 0,
        left: 0,
        viewportTop: 0,
        transitionState: false,
      },
      grapeInfoState: false,
      grapeInfoTextState: false,
      swiperOption2: {
        slidesPerView: 'auto',
        spaceBetween: 30,
        grabCursor: true,
        freeMode: true,
        pagination: {
          el: '.swiper-pagination',
          clickable: true,
        },
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev',
        },
      },
      // 이미지 postion 조절
      targetImg: null,
      targetYScroll: null,
    };
  },
  created() {
    // url에서 와인 아이디 추출
    this.id = this.$route.params.id;
    //TODO: 와인 데이터 요청

    // 모달 위치 초기화
    window.addEventListener('resize', this.initModalPosition);
    window.addEventListener('scroll', this.imgPositionChanger);

    // vuex
    this.actGetWineDetail(this.id).then(() => {
      // 한글 와인 타입 영문화
      this.type = common.getEType(this.detail.wineDto.type);
      // 별점에 따라 별 개수 동적 표시를 위해 width값 계산
      this.starRate = this.starRate * this.detail.wineDto.avg;
      // 리뷰 3개 별점 계산
      this.detail.reviewList.forEach((element) => {
        this.starRateReview.push(1 * element.point);
      });

      // 배경색 동적 할당
      this.backgroundColor = common.getTypeColor(this.detail.wineDto.type);
      // 텍스트 색 설정
      switch (this.detail.wineDto.type) {
        case '레드':
          this.fontColor = '#EDDB4B';
          break;
        case '화이트':
        case '스파클링':
          this.fontColor = '#9A040C';
          break;
        default:
          console.log('wine type error!');
          this.fontColor = '#fff';
          break;
      }
      // 모달 버튼 위치 초기화
      this.initModalPosition();
      // 맛 게이지 초기화
      const sweetGauge = this.detail.wineDto.sweet * 2;
      const acidityGauge = this.detail.wineDto.acidity * 2;
      const tanninGauge = this.detail.wineDto.tannin * 2;
      const bodyGauge = this.detail.wineDto.body * 2;
      this.$refs.sweet.style.transform = `translateY(-${sweetGauge}rem)`;
      this.$refs.acidity.style.transform = `translateY(-${acidityGauge}rem)`;
      this.$refs.tannin.style.transform = `translateY(-${tanninGauge}rem)`;

      this.$refs.body.style.transform = `translateY(-${bodyGauge}rem)`;

      // wine.img position chanager
      this.targetImg = document.querySelector('detail-wine-img');
      this.targetYScroll = document.body.offsetHeight - window.innerHeight * 2;
    });
  },
  destroyed() {
    window.removeEventListener('resize', this.initModalPosition);
    window.removeEventListener('scroll', thiss.imgPositionChanger);
  },
  mounted() {
    //TODO: 마운트 되었을 때 스크롤 살짝 내려서 애니메이션 동작하도록 설정
    //TODO: 그냥 등장 시키자....
  },
  computed: {
    ...mapState(['s3url']),
    ...mapState('wineDetail', ['detail']),
  },
  methods: {
    // vuex!
    ...mapActions('wineDetail', ['actGetWineDetail']),

    // vue 페이지 내 활용
    imgPositionChanger() {
      // 와인 이미지가 detail-section-3 중앙까지 위치 후 고정 됨
      if (window.pageYOffset <= this.targetYScroll) {
        this.targetImg.position = 'fixed';
      } else {
        this.targetImg.position = 'absolute';
      }
    },

    // modal 위치 초기화
    initModalPosition() {
      this.modalPosition.left = this.$refs.modalInitPosition.offsetLeft;
      this.modalPosition.top = this.$refs.modalInitPosition.offsetTop;
      this.$refs.modalWrap.style.top = `${this.modalPosition.top}px`;
      this.$refs.modalWrap.style.left = `${this.modalPosition.left}px`;
      this.modalPosition.viewportTop = window.innerHeight / 4;
    },
    // clickedGrapeInfo() {
    openModal() {
      this.grapeInfoState = true;
      //active 상태의 top설정
      const targetTop = window.pageYOffset + this.modalPosition.viewportTop;
      this.$refs.modalWrap.style.top = `${targetTop}px`;
      setTimeout(() => {
        this.grapeInfoTextState = true;
      }, 400);
      // fixed로 고정하기
      setTimeout(() => {
        this.modalPosition.transitionState = true;
        this.$refs.modalWrap.style.top = `${this.modalPosition.viewportTop}px`;
        this.$refs.modalWrap.style.position = 'fixed';
      }, 500);
      setTimeout(() => {
        this.modalPosition.transitionState = false;
      }, 510);
    },
    closeModal() {
      // fixed에서 -> absolute로 전환
      this.modalPosition.transitionState = true;
      const targetTop = window.pageYOffset + this.modalPosition.viewportTop;
      this.$refs.modalWrap.style.top = `${targetTop}px`;
      this.$refs.modalWrap.style.position = 'absolute';
      this.grapeInfoTextState = false;
      setTimeout(() => {
        this.modalPosition.transitionState = false;
        this.grapeInfoState = false;
        this.$refs.modalWrap.style.top = `${this.modalPosition.top}px`;
      }, 100);
    },
    clickedInfoIcon() {
      if (this.grapeInfoState) {
        this.closeModal();
      } else {
        this.openModal();
      }
    },
    clickedModalBG() {
      this.closeModal();
    },
    clickedShowReview() {
      alert('리뷰 모달 뿅!');
    },
  },

  //
};
</script>

<style scoped>
section {
  display: flex;
  width: 100vw;
  height: 100vh;
  border-bottom: 5px solid #cdc169;
  background-color: rgba(255, 255, 255, 0.427);
  font-family: 'Noto Sans KR', sans-serif;
  font-size: 1rem;
}
#detail-wine-img {
  position: fixed;
  z-index: 2;
  filter: drop-shadow(15px 15px 30px rgb(0, 0, 0));
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: auto;
  height: 60%;
  min-height: 550px;
}
.wine-blank {
  width: 30vh;
  height: 60vh;
  min-width: 300px;
  min-height: 550px;
}
.bar {
  width: 100%;
  height: 1px;
  margin: 1rem 0;
}
/* *************************************************************************** */
/* TODO: section 0 영역 */
/* FIXME:  */
/* **************************************************************************** */
#detail-section-0 {
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.sec-0-part {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 30%;
  max-width: 960px;
}
.sec-0-part span {
  display: block;
  font-size: 2rem;
}
.sec-0-part .ename {
  font-size: 3rem;
  font-weight: 700;
  margin-right: auto;
}
.sec-0-part .kname {
  font-size: 1rem;
  font-weight: 400;
  margin-left: auto;
}
.sub-info {
  margin-right: auto;
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 0.5em 0;
}
.sub-info .icon {
  width: 4.2rem;
  height: 4.2rem;
}
.sub-info .data p {
  font-size: 1.8rem;
  margin-left: 1em;
  margin: 0.2em 0 0.2em 1em;
}
.sub-info .data span {
  display: inline-block;
  font-size: 1.8rem;
  margin-left: 1em;
  margin: 0.2em 0 0.2em 1em;
}
.sub-info .data .key {
  font-family: 'Montserrat', sans-serif;
  font-weight: 700;
}
.sub-info .data .value {
  font-weight: 400;
}

.modal-background {
  visibility: hidden;
  position: fixed;
  z-index: -1;
  opacity: 0;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.3);
  backdrop-filter: blur(15px);
  transition: opacity 0.5s ease;
}
.modal-background-active {
  visibility: visible;
  opacity: 1;
  z-index: 4;
}
.prevent-scroll {
  position: fixed;
  height: 100vh;
}
.grape-info-modal {
  position: absolute;
  z-index: 5;
  width: 2rem;
  height: 2rem;
  margin-left: 0.5rem;
  border-radius: 1.1rem;
  background-color: rgba(255, 255, 255, 0.6);
  backdrop-filter: saturate(180%) blur(60px);
  transition: all 0.5s ease;
  overflow: hidden;
  cursor: pointer;
}
.grape-info-modal:hover {
  transform: scale(1.2);
}
.transition-none {
  transition: none !important;
}
.grape-info-modal-active {
  /* top: 25vh !important; */
  left: 25vw !important;
  width: 50vw;
  height: 50vh;
  padding: 1rem;
  cursor: all-scroll;
  overflow: auto !important;
}
.grape-info-modal svg {
  float: left;
  width: 2rem;
  height: 2rem;
  border-radius: 50%;
  cursor: pointer;
}
.svg-active {
  margin-right: 0.5rem;
  margin-bottom: 0.1rem;
}
.grape-info-modal-text {
  z-index: 3;
  color: black;
  font-size: 2rem;
  line-height: 2.2rem;
}

/* 중간 영역 */
.info-thumb-type {
  display: flex;
  justify-content: flex-end;
  position: relative;
  margin-top: 124px;
  margin-left: 100px;
  z-index: 3;
  width: 110px;
  font-size: 1rem;
  transform: rotate(-90deg);
  transform-origin: top left;
}
.info-thumb-type span {
  position: relative;
  top: -1px;
  font-weight: 400;
}
.circle {
  display: inline-block;
  width: 1rem;
  height: 1rem;
  margin-right: 5px;
  border-radius: 0.5rem;
  backdrop-filter: saturate(180%);
  border: 1px solid var(--basic-color-key);
}

.sec-0-part .price,
.sec-0-part .avg {
  display: flex;
  font-size: 3rem;
  font-weight: 700;
  margin-right: auto;
}
.sec-0-part .avg span {
  font-size: 3rem;
  font-weight: 700;
  margin-right: auto;
  margin-right: 1rem;
}
.star {
  position: relative;
}
.star-rate {
  position: absolute;
  top: 0;
  z-index: 1;
  width: 3rem;
  height: 3rem;
  background-image: url(../assets/images/star_yellow.png);
  background-size: contain;
  background-repeat: repeat-x;
}
.star-rate-bg {
  position: relative;
  z-index: 0;
  width: 15rem;
  height: 3rem;
  background-image: url(../assets/images/star_white.png);
  background-size: contain;
  background-repeat: repeat-x;
}
.scrap-btn {
  margin: 30px auto 0 20px;
  transform: scale(1.4);
  margin-bottom: 14rem;
}

/* *************************************************************************** */
/* TODO: section 1 영역 */
/* FIXME:  */
/* **************************************************************************** */
#detail-section-1 {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  position: relative;
}
.section-title {
  position: absolute;
  top: 8vh;
  left: 8vw;
  font-size: 3rem;
  font-family: 'Montserrat', sans-serif;
  font-weight: 700;
}
.sec-1-part {
  position: relative;
  width: 30vw;
  max-width: 600px;
  height: 30vw;
  max-height: 600px;
}
.sec-1-part .line {
  position: absolute;
  margin: 0;
  height: 1px;
  z-index: 0;
}

.sec-1-part .top-left {
  top: 0;
  left: 0;
  transform: rotate(45deg);
  transform-origin: left;
}
.sec-1-part .top-right {
  top: 0;
  right: 0;
  transform: rotate(-45deg);
  transform-origin: right;
}
.sec-1-part .bottom-left {
  bottom: 0;
  left: 0;
  transform: rotate(-45deg);
  transform-origin: left;
}
.sec-1-part .bottom-right {
  bottom: 0;
  right: 0;
  transform: rotate(45deg);
  transform-origin: right;
}
.circle-bg {
  position: relative;
  z-index: 1;
  margin: 10% 10%;
  width: 80%;
  height: 80%;
  border-radius: 50%;
}

.sec-1-part .left-top,
.sec-1-part .left-bottom {
  width: 50%;
  margin-left: 50%;
}
.sec-1-part .left-bottom {
  bottom: 0;
}

.sec-1-part .right-top,
.sec-1-part .right-bottom {
  width: 50%;
  margin-right: 50%;
}
.sec-1-part .right-bottom {
  bottom: 0;
}

.detail-torus {
  width: 1rem;
  height: 1rem;
  border-radius: 50%;
  border: 1px solid;
}
.left-top .detail-torus,
.left-bottom .detail-torus {
  transform: translate(-100%, -50%);
}
.right-top .detail-torus,
.right-bottom .detail-torus {
  position: absolute;
  right: 0;
  transform: translate(100%, -50%);
}

.line span {
  position: absolute;
  top: -2rem;
  margin: 0 1rem;
  font-style: italic;
}
.frame {
  position: absolute;
  width: 3rem;
  height: 10rem;
  overflow: hidden;
  background-color: rgba(255, 255, 255, 0.5);
  border-radius: 1.5rem;
}
.frame .line {
  position: relative;
  margin-top: 2rem;
  z-index: 3;
}
.frame .gauge {
  position: absolute;
  z-index: 0;
  top: 10rem;
  width: 100%;
  height: 100%;
  background-color: #f86161;
}
.left-top .frame,
.left-bottom .frame {
  left: 0;
  top: 0;
  transform: translate(-180%, -50%);
}
.left-top .gauge {
  margin-top: 3px;
  background-color: #f86161;
}
.left-bottom .gauge {
  margin-top: 3px;
  background-color: #fc2121;
}
.right-top span,
.right-bottom span {
  right: 0;
}
.right-top .frame,
.right-bottom .frame {
  right: 0;
  top: 0;
  transform: translate(180%, -50%);
}
.right-top .gauge {
  margin-top: 3px;
  background-color: #fe9a43;
}
.right-bottom .gauge {
  margin-top: 3px;
  background-color: #5a0707;
}

/* *************************************************************************** */
/* TODO: section 2 영역 */
/* FIXME:  */
/* **************************************************************************** */
#detail-section-2 {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  position: relative;
}
.sec-2-part-left {
  display: flex;
  flex-direction: row;
  justify-content: center;
  position: absolute;
  width: 50%;
  height: auto;
  left: 0;
  top: 50%;
  transform: translateY(-50%);
  background-color: #ffffff55;
}
.food-svg-frame {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 0 2rem;
}
.food-svg-frame p {
  margin-top: 0.5rem;
  font-size: 1.5rem;
}
.sec-2-part-right {
  margin-left: 50%;
  width: 30%;
}
.sec-2-part p span {
  display: block;
  font-size: 8rem;
  margin-left: -2rem;
}
.sec-2-part p {
  font-size: 2rem;
  line-height: 2.5rem;
}

/* *************************************************************************** */
/* TODO: section 3 영역 */
/* FIXME:  */
/* **************************************************************************** */
#detail-section-3 {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  position: relative;
}
.sec-3-part {
  position: relative;
  width: 40vw;
}
.sec-3-part .avg {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.sec-3-part .avg span {
  display: block;
  font-size: 18rem;
}
.sec-3-part .avg .star {
  width: 20rem;
  margin-top: 2rem;
  transform: scale(2);
  width: auto;
}

.sec-3-part .review-data-frame {
  display: flex;
  flex-direction: column;
  margin-top: 2rem;
}
.review-item {
  display: flex;
  flex-direction: row;
}
.sec-3-part .profile {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}
.sec-3-part .profile-img {
  width: 4rem;
  height: 4rem;
  margin-bottom: 0.5rem;
  border-radius: 50%;
  object-fit: cover;
  background: url(../assets/images/default_profile.jpg);
  background-position: center;
  background-size: cover;
}
.sec-3-part .profile p {
  width: 5rem;
  height: 1rem;
  text-align: right;
  font-size: 1rem;
  line-height: 1rem;
  overflow: hidden;
  display: -webkit-box;
  word-break: break-all;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
}
.sec-3-part .review-data {
  margin-left: 1rem;
  margin-bottom: 1rem;
}
.sec-3-part .review-data {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.sec-3-part .review-data .star-rate,
.sec-3-part .review-data .star-rate-bg {
  width: 1rem;
  height: 1rem;
}
.sec-3-part .review-data .star-rate-bg {
  width: 5rem;
}
.review-text-frame {
  position: relative;
  width: 18vw;
  height: 100px;
  margin-top: 1rem;
}
.review-text-frame .balloon {
  width: calc(18vw - 0.5rem);
  color: black;
  margin-left: auto;
  padding: 1rem;
  border-top-right-radius: 0.5rem;
  border-bottom-left-radius: 0.5rem;
  border-bottom-right-radius: 0.5rem;
  background-color: rgba(255, 255, 255, 0.7);
}
.review-text-frame .balloon::before {
  content: '';
  position: absolute;
}
.review-item:nth-child(2n-1) .review-text-frame .balloon::before {
  border-top: 0.5rem solid rgba(255, 255, 255, 0.7);
  border-left: 0.5rem solid transparent;
  border-right: 0px solid transparent;
  border-bottom: 0px solid transparent;
  top: 0;
  left: 0.5px;
}

.review-text-frame .balloon p {
  height: 3rem;
  font-size: 1.2rem;
  line-height: 1.5rem;
  overflow: hidden;
  display: -webkit-box;
  word-break: break-all;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
/* 리뷰 말풍선 엇갈려 쌓기 위해 */
.review-item:nth-child(2) {
  margin-left: 200px;
}
.review-item:nth-child(2) .star {
  margin-left: calc(18vw - 5rem);
}
.review-item:nth-child(2) .review-text-frame .balloon {
  border-top-left-radius: 0.5rem;
  border-top-right-radius: 0;
}
.review-item:nth-child(2) .review-text-frame .balloon::before {
  border-top: 0.5rem solid rgba(255, 255, 255, 0.7);
  border-left: 0px solid transparent;
  border-right: 0.5rem solid transparent;
  border-bottom: 0px solid transparent;
  content: '';
  position: absolute;
  top: 0;
  right: -0.5rem;
}
.review-item:nth-child(2) .profile {
  position: absolute;
  align-items: flex-start;
  margin-left: calc(18vw + 2.5rem);
}
.review-item:nth-child(2) .profile {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.review-show {
  display: flex;
  flex-direction: row;
}
.review-show span {
  padding: 1rem;
  border: 1px solid var(--basic-color-key);
  border-radius: 3rem;
  transition: all 0.5s ease;
}
.review-show span:hover {
  background-color: rgba(255, 255, 255, 0.5);
}

/* *************************************************************************** */
/* TODO: section 4 영역 */
/* FIXME:  */
/* **************************************************************************** */
#detail-section-4 {
  display: block;
  position: relative;
}
.swiper {
  width: 100vw;
  pointer-events: none;
  overflow: visible;
}
.slide {
  width: 300px;
  height: 500px;
  pointer-events: auto;
  top: 50vh;
  margin: 0 3rem;
  transform: translateY(-50%);
}
.slide:nth-child(2n) {
  padding-top: 250px;
}
.swiper-pagination {
  display: flex;
  width: auto;
  height: 2rem;
  position: absolute;
  z-index: 10;
  pointer-events: auto;
  top: calc(8vh + 58px);
  left: calc(8vw + 200px);
  background-color: transparent;
  pointer-events: none;
}
.swiper-button-prev,
.swiper-button-next {
  pointer-events: auto;
  position: absolute;
  top: 54vh;
}
</style>

<style>
.swiper-pagination-bullet {
  border-radius: 1rem;
  width: 2rem;
  height: 2rem;
  margin: 0 10rem;
  display: inline-block;
  background-color: #e4c18c;
  opacity: 0.5;
  transition: all 0.3s ease;
}
.swiper-pagination-bullet-active {
  background-color: var(--basic-color-key) !important;
  /* width: 2rem; */
  opacity: 1;
}
.swiper-pagination-bullet {
  margin: 0 1rem;
}
</style>
