<template>
  <div>
    <canvas id="myChart-2" width="100%" height="100%"></canvas>
  </div>
</template>

<script>
import Chart from "chart.js";

export default {
  props: ["grapeList"],
  mounted() {
    this.createChart();
  },
  watch: {
    grapeList: function () {
      this.createChart();
    },
  },

  methods: {
    createChart() {
      const ctx = document.getElementById("myChart-2");
      new Chart(ctx, {
        type: "pie",
        data: {
          labels: [
            "피노누아",
            "쉬라즈",
            "소비뇽블랑",
            "카베르네 소비뇽",
            "샤르도네",
            "메를로",
            "모스카토",
            "말벡",
            "카르메네르",
          ],
          datasets: [
            {
              data: this.grapeList,
              backgroundColor: [
                "#F6DFDF",
                "#E9B5B5",
                "#D98383",
                "#CB6666",
                "#931616 ",
                "#EAF6DF",
                "#CFF7C2",
                "#B5EEA4",
                "#9AD6A4",
                "#62C653",
              ],
              hoverOffset: 10,
            },
          ],
        },
        options: {
          legend: {
            position: "right",
            align: "middle",
          },
        },
      });
      // ###########################################
    },
  },
};
</script>
