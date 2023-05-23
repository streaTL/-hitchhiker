<template>
  <div class="card mb-3" style="width: 450px">
    <div class="row g-0">
      <div class="col">
        <img
          :src="plan.firstImage"
          class="img-fluid rounded-start"
          style="width: 100%; height: 100%; object-fit: cover"
          alt="..."
        />
      </div>
      <div class="col-md-8">
        <div class="card-body">
          <h5 class="card-title">{{ plan.title }}</h5>
          <p class="card-text">{{ plan.addr1 }}</p>
          <p class="card-text">
            <button type="button" class="btn btn-primary" @click="deleteplan">여행지 삭제</button>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
export default {
  name: "PlanComponent",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  props: {
    plan: [],
    index: Number,
  },
  created() {},
  methods: {
    ...mapMutations(["DELETE_PLAN"]),
    deleteplan() {
      this.DELETE_PLAN(this.index);
      alert("삭제되었습니다!");
    },
  },
};
</script>

<style scoped></style>
