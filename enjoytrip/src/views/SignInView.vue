<template>
  <div>
    <section
      class="masthead bg-primary text-white text-center"
      id="loginBackground"
    >
      <div
        class="container d-flex align-items-center flex-column card2 border-white"
      >
        <h1 class="masthead-heading text-uppercase mb-0">Register</h1>
        <!-- Icon Divider-->
        <div class="divider-custom divider-light">
          <div class="divider-custom-line"></div>
          <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
          <div class="divider-custom-line"></div>
        </div>

        <form
          action="${root}/user/regist"
          method="post"
          name="register-form"
          class="register-form"
        >
          <div class="d-flex mb-4 justify-content-md-between">
            <h3 class="masthead-heading text-uppercase mb-0 fs-1 me-5">ID</h3>
            <input
              type="text"
              class="form-control input-sm"
              id="userId"
              v-model="user.id"
              style="width: 200px"
            />
          </div>
          <div class="d-flex mb-4 justify-content-md-between">
            <h3
              class="masthead-heading text-uppercase mb-0 fs-1"
              style="margin-right: 20px"
            >
              PW
            </h3>
            <input
              type="password"
              class="form-control input-sm"
              id="userPw"
              v-model="user.password"
              style="width: 200px"
            />
          </div>
          <div class="d-flex mb-4 justify-content-md-between">
            <h3
              class="masthead-heading text-uppercase mb-0 fs-1"
              style="margin-right: 20px"
            >
              E-MAIL
            </h3>
            <input
              type="text"
              class="form-control input-sm"
              id="userEmail"
              v-model="user.email"
              style="width: 200px"
            />
          </div>
          <div class="d-flex mb-4 justify-content-md-between">
            <h3
              class="masthead-heading text-uppercase mb-0 fs-1"
              style="margin-right: 20px"
            >
              NAME
            </h3>
            <input
              type="text"
              class="form-control input-sm"
              id="userName"
              v-model="user.name"
              style="width: 200px"
            />
          </div>
          <input
            class="btn btn-outline-light text-nowrap mt-3 input-sm"
            type="button"
            @click="regist"
            value="확인"
          />
        </form>
      </div>
    </section>
  </div>
</template>

<script>
import httpUser from "@/api/user";
export default {
  name: "SignIn",
  components: {},
  data() {
    return {
      user: {
        id: "",
        password: "",
        email: "",
        name: "",
      },
    };
  },
  created() {},
  methods: {
    regist() {
      httpUser.post("/regist", this.user);
      this.$router.push({ name: "userLogin" });
    },
  },
};
</script>

<style scoped></style>