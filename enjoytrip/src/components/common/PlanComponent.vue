<template>
  <div class="card mb-3" style="width: 540px">
    <div class="row g-0">
      <div class="col-md-4">
        <img src="" class="img-fluid rounded-start" alt="..." />
      </div>
      <div class="col-md-8">
        <div class="card-body">
          <h5 class="card-title">관광지명</h5>
          <p class="card-text">관광지 주소</p>
          <p class="card-text">
            <button type="button" class="btn btn-primary">여행지 삭제</button>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PlanComponent",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  props: {
    trip: [],
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>
