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
                "#FFC2E0",
                "#FFF5AF",
                "#F6DA25",
                "#D5AD5A",
                "#986D24",
                "#ECD6B9",
                "#FF6B6B",
                "#F8C294",
                "#E1FAD9",
                "#3C6704",
                "#FFFDC0",
                "#CAB9B9",
                "#FF3030",
                "#9D6E10",
                "#C6A25A",
                "#9D7373",
                "#774000",
                "#FFE6E6",
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

