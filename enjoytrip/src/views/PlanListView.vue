<template>
  <div>
    <section class="page-section masthead" id="contact">
      <div class="container">
        <!-- Contact Section Heading-->
        <h2
          class="page-section-heading text-center text-uppercase text-secondary mb-0"
        >
          계획 리스트
        </h2>
        <!-- Icon Divider-->
        <div class="divider-custom">
          <div class="divider-custom-line"></div>
          <div class="divider-custom-icon">
            <i class="fas fa-star"></i>
          </div>
          <div class="divider-custom-line"></div>
        </div>
        <!-- Contact Section Form-->
        <div
          id="my-table"
          class="mb-auto row"
          style="
            flex-wrap: wrap;
            display: flex;
            justify-content: center;
            width: 100%;
            position: relative;
          "
        >
          <!-- 계획 리스트 카드로 표시하기 -->
          <plan-list-component
            v-for="(plan, index) in paginatedPlans"
            :key="index"
            :plan="plan"
          ></plan-list-component>
        </div>
        <div style="justify-content: center; display: flex">
          <b-pagination
            v-model="currentPage"
            :total-rows="rows"
            :per-page="perPage"
            aria-controls="my-table"
          ></b-pagination>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import http from "@/api/http";
import PlanListComponent from "@/components/plan/PlanListComponent.vue";
import { BPagination } from "bootstrap-vue";

export default {
  name: "PlanListView",
  components: { PlanListComponent, BPagination },
  data() {
    return {
      plans: [],

      perPage: 10,
      currentPage: 1,
    };
  },
  computed: {
    rows() {
      return this.plans.length;
    },
    paginatedPlans() {
      const startIndex = (this.currentPage - 1) * this.perPage;
      const endIndex = startIndex + this.perPage;
      return this.plans.slice(startIndex, endIndex);
    },
  },
  created() {
    http.get("plan/list").then(({ data }) => {
      this.plans = data;
      console.log(this.plans);
    });
  },
  methods: {},
};
</script>

<style scoped></style>