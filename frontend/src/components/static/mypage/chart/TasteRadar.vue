<template>
  <div>
    <canvas id="myChart-4" width="100%" height="100%"></canvas>
  </div>
</template>

<script>
import Chart from "chart.js";

export default {
  props: ["tasteList"],
  mounted() {
    this.createChart();
  },
  watch: {
    tasteList: function () {
      this.createChart();
    },
  },

  methods: {
    createChart() {
      const ctx = document.getElementById("myChart-4");
      new Chart(ctx, {
        type: "radar",
        data: {
          labels: ["산도", "바디", "당도", "타닌"],
          datasets: [
            {
              data: this.tasteList,
              fill: true,
              backgroundColor: "rgba(255, 99, 132, 0.2)",
              borderColor: "rgb(255, 99, 132)",
              pointBackgroundColor: "rgb(255, 99, 132)",
              pointBorderColor: "#fff",
              pointHoverBackgroundColor: "#fff",
              pointHoverBorderColor: "rgb(255, 99, 132)",
            },
            {
              data: [0, 0, 0, 0],
              fill: false,
              backgroundColor: "rgba(0, 0, 0, 0.2)",
              borderColor: "rgb(0, 0, 0)",
              pointBackgroundColor: "rgb(0, 0, 0)",
              pointBorderColor: "#fff",
              pointHoverBackgroundColor: "#fff",
              pointHoverBorderColor: "rgb(0, 0, 0)",
            },
          ],
        },
        options: {
          legend: {
            display: false,
          },
          elements: {
            line: {
              borderWidth: 3,
            },
          },
          scales: {
            fontSize: 14,
          },
        },
      });
      // ###########################################
    },
  },
};
</script>

