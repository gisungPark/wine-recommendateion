<template>
  <div>
    <h1>기본 구조</h1>
  </div>
</template>

<script>
export default {
  name: 'WineDetails',
  props: {
    wineId: {
      typs: String,
      default: function() {
        return 0;
      },
    },
  },
  data() {
    return {
      type: '레드',
      ename: 'wine name wine wine wine winewine name wine wine wine wine',
      kname: '와인 명',
      avg: '5.0',
      year: '2021',
      price: '100000',
      img: 'https://images.vivino.com/thumbs/1mRIannXRIqzf7FwQCmaJg_pb_x600.png',
    };
  },
  created() {},
};
</script>

<style scoped></style>
