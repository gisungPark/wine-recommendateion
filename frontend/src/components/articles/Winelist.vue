<template>
  <div id="winelist" ref="winelist">
    <!-- <div v-for="(wine, index) in wines" :key="index+wine.ename" :class="{'wineitem-even':wineListType, 'wineitem-odd':!wineListType}"></div> -->
    <div v-for="(wine, index) in wines" :key="index+wine.ename+wine.madrginTop" :style="{marginTop: wine.marginTop}"></div>
  </div>
</template>

<script>
export default {
  name: 'Winelist',
  data() {
    return {
      wineListType: true,
      wines: [{
        type: '레드',
        ename: 'wine name',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
      }, {
        type: '레드',
        ename: 'wine name',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
      }, {
        type: '레드',
        ename: 'wine name',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
      }, {
        type: '레드',
        ename: 'wine name',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
      }, {
        type: '레드',
        ename: 'wine name',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
      }, {
        type: '레드',
        ename: 'wine name',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
      }, {
        type: '레드',
        ename: 'wine name',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
      }, {
        type: '레드',
        ename: 'wine name',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
      }, {
        type: '레드',
        ename: 'wine name',
        kname: '와인 명',
        avg: '5.0',
        year: '2021',
        price: '100000',
      },]
    };
  },
  created() {
    
  },
  mounted() {
    const winelistWidth = this.$refs.winelist.clientWidth;
    const itemCountInLine = Math.floor(winelistWidth / 300);
    console.log(winelistWidth, itemCountInLine);
    for (let i = 0; i < this.wines.length; i++) {
      let nth = i % itemCountInLine + 1;
      if(nth % 2 === 0){
        this.wines[i].marginTop = '0';
      } else {
        this.wines[i].marginTop = '-250px';
      }
      console.log(this.wines[i]);
    }
  },
};
</script>


<style>
#winelist {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  width: 90%;
  margin-top: 250px;
  background-color: rgba(255, 255, 255, 0.502);
}

/* 와인 리스트가 홀수일 경우 적용 */
#winelist div{
  width: 300px;
  border-radius: 150px;
  height: 500px;
  background-color: #fff;
}
#winelist div:hover{
  background-color: red;
}

/* 와인 리스트가 짝수일 경우 적용 */
/* .wineitem-even {
  width: 300px;
  border-radius: 150px;
  height: 500px;
  background-color: #fff;
}
.wineitem-even:nth-child(even){
  margin-top: 250px;
  background-color: red !important; 
}*/
</style>
