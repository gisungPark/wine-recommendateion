<script>
import { Pie } from "vue-chartjs";

export default {
  extends: Pie,
  data() {
    return {
      datacollection: {
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
            data: [300, 50, 100, 300, 50, 100, 300, 50, 100, 12],
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
