<template>
  <div>
    <div style="margin-top: 150px; display: flex">
      <div class="chart-container">
        <h3>지역별 읽은 횟수</h3>
        <Bar v-if="loaded" class="chart" :data="data" :options="options" />
      </div>
      <div class="pie-container">
        <h3>지역별 관광지 갯수</h3>
        <Pie v-if="loaded" class="chart" :data="data1" :options="options" />
      </div>
    </div>
    <div style="margin-top: 150px; display: flex">
      <div class="chart-container">
        <h3>지역별 음식점 갯수</h3>
        <Bar v-if="loaded" class="chart" :data="data2" :options="options" />
      </div>
      <div class="pie-container">
        <h3>지역별 모든 시설 갯수</h3>
        <Pie v-if="loaded" class="chart" :data="data3" :options="options" />
      </div>
    </div>
  </div>
</template>

<script>
import { Bar, Pie } from "vue-chartjs";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
  ArcElement,
} from "chart.js";
import http from "@/api/http";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
  ArcElement
);

export default {
  name: "ChartView",
  components: { Bar, Pie },
  data() {
    return {
      loaded: false,
      data: {
        labels: [],
        datasets: [
          {
            label: "수치",
            data: [],
            backgroundColor: [
              "rgba(255, 99, 132, 0.5)", // Set the background color for each pie slice
            ],
          },
        ],
      },
      data1: {
        labels: [],
        datasets: [
          {
            data: [],
            backgroundColor: [
              "rgba(255, 99, 132, 0.5)", // Set the background color for each pie slice
            ],
          },
        ],
      },
      data2: {
        labels: [],
        datasets: [
          {
            label: "수치",
            data: [],
            backgroundColor: [
              "rgba(255, 99, 132, 0.5)", // Set the background color for each pie slice
            ],
          },
        ],
      },
      data3: {
        labels: [],
        datasets: [
          {
            data: [],
            backgroundColor: [
              "rgba(255, 99, 132, 0.5)", // Set the background color for each pie slice
            ],
          },
        ],
      },
      options: {
        responsive: true,
      },
    };
  },
  async mounted() {
    const response = await http("/map/chart");
    const colorPalette = this.generateRandomColors(response.data.length); // Generate random colors
    response.data.forEach((element, index) => {
      this.data.labels.push(element.sido_name);
      this.data.datasets[0].data.push(element.avg);
      this.data.datasets[0].backgroundColor.push(colorPalette[index]);
    });

    const response1 = await http("/map/chart1");
    response1.data.forEach((element, index) => {
      this.data1.labels.push(element.sido_name);
      this.data1.datasets[0].data.push(element.avg);
      this.data1.datasets[0].backgroundColor.push(colorPalette[index]);
    });
    const response2 = await http("/map/chart2");
    response2.data.forEach((element, index) => {
      this.data2.labels.push(element.sido_name);
      this.data2.datasets[0].data.push(element.avg);
      this.data2.datasets[0].backgroundColor.push(colorPalette[index]);
    });
    const response3 = await http("/map/chart3");
    response3.data.forEach((element, index) => {
      this.data3.labels.push(element.sido_name);
      this.data3.datasets[0].data.push(element.avg);
      this.data3.datasets[0].backgroundColor.push(colorPalette[index]);
    });

    this.loaded = true;
  },
  methods: {
    generateRandomColors(count) {
      const colors = [];
      for (let i = 0; i < count; i++) {
        const randomColor = `rgba(${this.getRandomNumber(
          0,
          255
        )}, ${this.getRandomNumber(0, 255)}, ${this.getRandomNumber(
          0,
          255
        )}, 0.5)`;
        colors.push(randomColor);
      }
      return colors;
    },

    getRandomNumber(min, max) {
      return Math.floor(Math.random() * (max - min + 1)) + min;
    },
  },
};
</script>

<style scoped>
.chart-container {
  display: flex;
  width: 50%;
  flex-grow: 1;
  min-height: 0;
  flex-direction: column;
}
.pie-container {
  display: flex;
  width: 20%;
  flex-grow: 1;
  min-height: 0;
  flex-direction: column;
}
</style>