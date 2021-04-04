<script>
import { Pie } from "vue-chartjs";

export default {
  extends: Pie,
  data() {
    return {
      datacollection: {
        labels: ["RedWine", "WhiteWine", "Sparkling"],
        datasets: [
          {
            label: "My First Dataset",
            data: [300, 50, 100],
            backgroundColor: ["#821a33", "#ded483", "#89b22b"],
            hoverOffset: 4,
          },
        ],
      },
      options: {
        legend: {
          display: true,
          position: "right",
        },
        maintainAspectRatio: false,
      },
    };
  },
  mounted() {
    this.renderChart(this.datacollection, this.options);
  },
};
</script>
