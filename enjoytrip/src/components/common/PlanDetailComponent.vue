<template>
  <div class="card ms-4 mb-1" style="width: 100%">
    <div class="row g-0">
      <div class="col-md-4">
        <img
          :src="planDetail.imgSrc"
          class="img-fluid rounded-start"
          style="width: 80%; height: 150px; object-fit: cover"
          alt="..."
        />
      </div>
      <div class="col-md-8">
        <div class="card-body">
          <h5 class="card-title">{{ planDetail.title }}</h5>
          <p class="card-text">{{ planDetail.addr }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ListComponent",
  components: {},
  data() {
    return {};
  },
  props: {
    planDetail: [],
  },
  created() {},
};
</script>

<style scoped></style>
