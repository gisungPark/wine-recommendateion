<script>
import { Pie } from "vue-chartjs";
import * as chartApi from "@/api/chart.js";

export default {
  extends: Pie,
  props: ["basicList"],
  mounted() {
    this.getBasic();
  },
  methods: {
    async getBasic() {
      const response = await chartApi.getBasicStatistic();
      const array = new Array(
        response.data.redCnt,
        response.data.spaclingCnt,
        response.data.whiteCnt
      );
      this.renderChart(
        {
          datacollection: {
            labels: ["RedWine", "WhiteWine", "Sparkling"],
            datasets: [
              {
                label: "My First Dataset",
                data: [100, 100, 100],
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
        },
        this.options
      );

      console.log(array);
    },
  },
};
</script>
