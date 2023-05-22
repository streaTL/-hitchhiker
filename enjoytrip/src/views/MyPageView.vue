<template>
  <div>
    <section
      class="masthead bg-primary text-white text-center"
      id="loginBackground"
    >
      <div
        class="container d-flex align-items-center flex-column card2 border-white"
      >
        <h1 class="masthead-heading text-uppercase mb-0">My Page</h1>

        <div class="divider-custom divider-light">
          <div class="divider-custom-line"></div>
          <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
          <div class="divider-custom-line"></div>
        </div>

        <form name="login-form" class="login-form">
          <div class="d-flex mb-4 justify-content-md-between">
            <h3 class="masthead-heading text-uppercase mb-0 fs-1 me-5">ID</h3>
            <input
              disabled
              type="text"
              class="form-control input-sm"
              id="userId"
              v-model="user.id"
              style="width: 200px"
            />
          </div>
          <div class="d-flex mb-4 justify-content-md-between">
            <h3 class="masthead-heading text-uppercase mb-0 fs-1 me-5">
              E-MAIL
            </h3>
            <input
              :disabled="able"
              type="text"
              class="form-control input-sm"
              id="userEmail"
              v-model="user.email"
              style="width: 200px"
            />
          </div>
          <div class="d-flex mb-4 justify-content-md-between">
            <h3 class="masthead-heading text-uppercase mb-0 fs-1 me-5">NAME</h3>
            <input
              :disabled="able"
              type="text"
              class="form-control input-sm"
              id="userName"
              style="width: 200px"
              v-model="user.name"
            />
          </div>
          <button
            type="button"
            @click="abled"
            class="btn btn-outline-light text-nowrap mt-3 input-sm me-3"
            id="editUser"
          >
            수정
          </button>
          <button
            class="btn btn-outline-light text-nowrap mt-3 input-sm me-3"
            id="leaveUser"
            type="button"
            @click="deleteU"
          >
            탈퇴
          </button>
        </form>
      </div>
    </section>
  </div>
</template>

<script>
import userHttp from "@/api/user";
import { mapActions, mapState } from "vuex";
export default {
  name: "MyPage",
  components: {},
  data() {
    return {
      user: {
        id: "",
        email: "",
        name: "",
      },
      able: true,
    };
  },
  computed: {
    ...mapState(["userInfo"]),
  },
  created() {
    let accessToken = sessionStorage.getItem("access-token");
    userHttp
      .get("/info/" + this.userInfo, {
        headers: {
          "access-token": accessToken,
        },
      })
      .then(({ data }) => {
        console.log(data);
        this.user = data.userInfo;
      });
  },
  methods: {
    ...mapActions(["deleteUser"]),
    abled() {
      let accessToken = sessionStorage.getItem("access-token");
      if (this.able === true) {
        this.able = !this.able;
      } else {
        userHttp
          .post("/editUser", this.user, {
            headers: {
              "auth-token": accessToken,
            },
          })
          .then(({ data }) => {
            console.log(data);
            this.user = data;
          });
        this.able = !this.able;
      }
    },

    async deleteU() {
      let isSuccess = await this.deleteUser(this.user.id);
      console.log(isSuccess);
      if (isSuccess) {
        this.$router.push({ name: "main" });
      }
    },
  },
};
</script>

<style scoped></style>