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
      :src="trip.firstImage"
      class="card-img-top mt-2"
      style="height: 10rem"
    />
    <div class="card-body">
      <h5 class="card-title">{{ trip.title }}</h5>
      <p class="card-text">{{ trip.addr1 }}</p>
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
                <i class="fa-solid fa-hashtag ms-4 me-2"> </i> {{ trip.title }}
              </h1>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div
              class="modal-body"
              style="display: flex; justify-content: center"
            >
              <img class="img-fluid" :src="trip.firstImage" />
            </div>

            <div class="ms-5 me-5 card-text">
              <p>
                <span> <i class="fa-regular fa-map me-2"></i></span>
                {{ trip.addr1 }} {{ trip.addr2 }}
              </p>
            </div>
            <div class="ms-5 me-5 card-text">
              <p>
                <span> <i class="fa-regular fa-pen-to-square me-2"></i></span>
                {{ trip.overview }}
              </p>
            </div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Close
              </button>
              <button type="button" class="btn btn-primary" @click="attrAdd">
                여행지 추가
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
export default {
  name: "ListComponent",
  components: {},
  data() {
    return {
      id: "",
      modalId: "",
    };
  },
  props: {
    trip: [],
  },
  created() {
    this.id = "id" + this.trip.contentId;
    this.modalId = "#" + this.id;
  },
  methods: {
    ...mapMutations(["SET_PLAN"]),
    attrAdd() {
      this.SET_PLAN(this.trip);
    },
  },
};
</script>

<style scoped></style>
