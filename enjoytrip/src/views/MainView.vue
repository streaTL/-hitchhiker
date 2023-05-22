<template>
  <div>
    <header-component></header-component>
    <section class="page-section portfolio" id="portfolio">
      <div class="container">
        <!-- Portfolio Section Heading-->
        <h2
          class="page-section-heading text-center text-uppercase text-secondary mb-0"
        >
          관광지
        </h2>
        <!-- Icon Divider-->
        <div class="divider-custom">
          <div class="divider-custom-line"></div>
          <div class="divider-custom-icon">
            <i class="fas fa-star"></i>
          </div>
          <div class="divider-custom-line"></div>
        </div>

        <!-- Portfolio Grid Items-->
        <div class="row justify-content-center">
          <main-list-component
            v-for="(trip, index) in trips"
            :key="index"
            :trip="trip"
          ></main-list-component>
        </div>
        <!-- 여기까지 컴포넌트 -->
      </div>
    </section>
  </div>
</template>

<script>
import HeaderComponent from "@/components/common/HeaderComponent.vue";
import axios from "axios";
import MainListComponent from "@/components/common/MainListComponent.vue";

export default {
  name: "MainView",
  components: { HeaderComponent, MainListComponent },
  data() {
    return {
      trips: [],
    };
  },
  created() {
    axios.get("http://localhost/map/ramAttrInfo").then(({ data }) => {
      this.trips = data;
      console.log(this.trips);
    });
  },
  methods: {},
};
</script>

<style scoped></style>