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
      :src="planDetail.imgSrc"
      class="card-img-top mt-2"
      style="height: 10rem"
    />
    <div class="card-body">
      <h5 class="card-title">{{ planDetail.title }}</h5>
      <p class="card-text">{{ planDetail.addr }}</p>
      <!-- <a href="" class="btn btn-primary">Go somewhere</a> -->
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
