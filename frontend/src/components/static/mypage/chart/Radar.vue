<script>
import { Radar } from "vue-chartjs";

export default {
  extends: Radar,
  data() {
    return {
      datacollection: {
        labels: ["바디", "당도", "산도", "타닌"],
        datasets: [
          {
            data: [86, 35, 75, 95],
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
            fill: true,
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
    };
  },
  mounted() {
    this.renderChart(this.datacollection, this.options);
  },
};
</script>
