<template>
  <div
    class="card ms-2 me-2 mb-2"
    style="
      width: 18rem;
      height: 22rem;
      object-fit: cover;
      display: inline-block;
    "
  >
    <img
      :src="plan.planDetail[0].imgSrc"
      class="card-img-top mt-2"
      style="height: 10rem"
    />
    <div class="card-body">
      <h5 class="card-title">이름: {{ plan.planName }}</h5>
      <p class="card-text">작성자: {{ plan.userId }}</p>
      <!-- <a href="" class="btn btn-primary">Go somewhere</a> -->
      <button
        class="btn btn-primary"
        type="button"
        data-bs-toggle="modal"
        :data-bs-target="modalId"
        style="position: absolute; bottom: 10px; right: 15px"
      >
        detail
      </button>
      <div
        class="modal fade"
        :id="id"
        tabindex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog modal-lg">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title fs-5" id="exampleModalLabel">
                <i class="fa-solid fa-hashtag ms-4 me-2"> </i>
                {{ plan.planName }}
              </h1>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div
              class="mb-auto row"
              style="
                flex-wrap: wrap;
                display: flex;
                justify-content: center;
                width: 100%;
                position: relative;
              "
            >
              <plan-detail-component
                v-for="(planDetail, index) in plan.planDetail"
                :key="index"
                :planDetail="planDetail"
              ></plan-detail-component>
            </div>
            <div class="ms-5 me-5 card-text">
              <p>
                <span> <i class="fa-regular fa-pen-to-square me-2"></i></span>
                {{ plan.planDesc }}
              </p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-primary">계획 삭제</button>
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Close
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PlanDetailComponent from "../common/PlanDetailComponent.vue";
export default {
  name: "PlanListComponent",
  components: { PlanDetailComponent },
  data() {
    return {
      modalId: "",
      Id: "",
    };
  },
  props: {
    plan: [],
  },
  created() {
    this.id = "id" + this.plan.planId;
    this.modalId = "#" + this.id;
  },
  methods: {
    deletePlan() {},
  },
};
</script>

<style scoped></style>