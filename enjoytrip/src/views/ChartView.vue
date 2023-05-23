<template>
  <div style="margin-top: 130px">
    <Bar v-if="loaded" :data="data" :options="options" />
  </div>
</template>

<script>
import { Bar } from "vue-chartjs";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
} from "chart.js";
import http from "@/api/http";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

export default {
  name: "ChartView",
  components: { Bar },
  data() {
    return {
      loaded: false,
      data: {
        labels: [],
        datasets: [{ data: [] }],
      },
      options: {
        responsive: true,
      },
    };
  },
  async mounted() {
    const response = await http("/map/chart");
    response.data.forEach((element) => {
      this.data.labels.push(element.sido_name);
      this.data.datasets[0].data.push(element.avg);
    });
    this.loaded = true;
  },
  methods: {},
};
</script>

<style scoped></style>