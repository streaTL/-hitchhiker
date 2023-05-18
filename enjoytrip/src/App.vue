<template>
  <div id="app">
    <nav-component></nav-component>
    <router-view />
    <footer-component></footer-component>
  </div>
</template>

<script>
import NavComponent from "@/components/common/NavComponent.vue";
import FooterComponent from "./components/common/FooterComponent.vue";

// import { js } from "../src/assets/js/scripts.js";

export default {
  components: { NavComponent, FooterComponent },
};
</script>

<style>
@import "../src/assets/css/styles.css";
/* #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
} */
</style>
