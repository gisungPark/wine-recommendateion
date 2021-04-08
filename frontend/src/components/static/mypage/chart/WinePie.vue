<template>
  <div>
    <canvas id="myChart-1" width="100%" height="100%"></canvas>
  </div>
</template>
<script>
import Chart from "chart.js";

export default {
  props: ["basicList"],
  mounted() {
    this.createChart();
  },
  watch: {
    basicList: function () {
      this.createChart();
    },
  },
  methods: {
    createChart() {
      const ctx = document.getElementById("myChart-1");
      new Chart(ctx, {
        type: "pie",
        data: {
          labels: ["RED", "WHITE", "SPARKLING"],
          datasets: [
            {
              label: "# of Votes",
              data: this.basicList,
              backgroundColor: ["#821a33", "#ded483", "#89b22b"],
              hoverOffset: 4,
              borderWidth: 1,
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
