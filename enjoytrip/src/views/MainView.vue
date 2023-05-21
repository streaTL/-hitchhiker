<template>
  <div>
    <header-component></header-component>
    <div>기능 추가 작업중</div>
    <div style="display: flex; justify-content: center">
      <div
        class="mb-auto ms-3 me-3 row"
        style="flex-wrap: wrap; width: 80%; position: relative"
      >
        <list-component
          v-for="(trip, index) in trips"
          :key="index"
          :trip="trip"
        ></list-component>
      </div>
    </div>
  </div>
</template>

<script>
import HeaderComponent from "@/components/common/HeaderComponent.vue";
import axios from 'axios';


export default {
  name: "MainView",
  components: { HeaderComponent },
  data() {
    return {
      trips : []
    };
  },
  created() {
    axios.get("http://localhost/map/ramAttrInfo").then(({data}) => trip = data)
  },
  methods: {},
};
</script>

<style scoped></style>