<template>
  <div>
    <header class="masthead bg-primary text-white text-center" id="header-logo">
      <div class="container d-flex align-items-center flex-column">
        <!-- Masthead Avatar Image-->
        <img class="masthead-avatar mb-5" :src="logoImgSrc" alt="..." />
        <!-- Masthead Heading-->
        <h1 class="masthead-heading text-uppercase mb-0">Go! Trip!</h1>
        <!-- Icon Divider-->
        <div class="divider-custom divider-light">
          <div class="divider-custom-line"></div>
          <div class="divider-custom-icon">
            <i class="fas fa-star"></i>
          </div>
          <div class="divider-custom-line"></div>
        </div>
        <!-- Masthead Subheading-->
        <p class="masthead-subheading font-weight-light mb-0">
          전국에 있는 모든 명소에!
        </p>
      </div>
    </header>
  </div>
</template>

<script>
export default {
  name: "HeaderComponent",
  components: {},
  data() {
    return {
      logoImgSrc: require("@/assets/img/logo.png"),
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>