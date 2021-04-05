<template>
  <div>
    <canvas id="myChart-3" width="100%" height="100%"></canvas>
  </div>
</template>

<script>
import Chart from "chart.js";

export default {
  props: ["flavorList"],
  mounted() {
    this.createChart();
  },
  watch: {
    flavorList: function () {
      this.createChart();
    },
  },

  methods: {
    createChart() {
      const ctx = document.getElementById("myChart-3");
      new Chart(ctx, {
        type: "doughnut",
        data: {
          labels: [
            "복숭아 향",
            "꿀 향",
            "시트러스 향",
            "토스트 향",
            "오크",
            "아몬드 향",
            "베리 향",
            "배 향",
            "허브 향",
            "숲 향",
            "바닐라 향",
            "향신료 향",
            "자두 향",
            "헤이즐넛 향",
            "트러플 향",
            "가죽 향",
            "코코아 향",
            "흰꽃 향",
          ],
          datasets: [
            {
              data: this.flavorList,
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

