<template>
  <div
    class="card ms-2 me-2"
    style="width: 18rem; height: 22rem; object-fit: cover; display: inline-block"
  >
    <img :src="trip.firstimage" class="card-img-top" style="height: 10rem" alt="" />
    <div class="card-body">
      <h5 class="card-title">{{ trip.title }}</h5>
      <p class="card-text">{{ trip.addr1 }}</p>
      <!-- <a href="" class="btn btn-primary">Go somewhere</a> -->
      <button
        class="btn btn-primary"
        type="button"
        data-bs-toggle="modal"
        :data-bs-target="modalId"
      >
        dtail
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
              <h1 class="modal-title fs-5" id="exampleModalLabel">{{ trip.title }}</h1>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body" style="display: flex; justify-content: center">
              <img :src="trip.firstimage" />
            </div>
            <div class="ms-5">상세설명</div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">Save changes</button>
            </div>
          </div>
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
    return {
      id: "",
      modalId: "",
    };
  },
  props: {
    trip: [],
  },
  created() {
    this.id = "id" + this.trip.contentid;
    this.modalId = "#" + this.id;
  },
  methods: {},
};
</script>

<style scoped></style>
