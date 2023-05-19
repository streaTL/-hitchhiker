<template>
  <div>
    <section
      class="masthead bg-primary text-white text-center"
      id="loginBackground"
    >
      <div
        class="container d-flex align-items-center flex-column card2 border-white"
      >
        <h1 class="masthead-heading text-uppercase mb-0">find pw</h1>

        <div class="divider-custom divider-light">
          <div class="divider-custom-line"></div>
          <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
          <div class="divider-custom-line"></div>
        </div>

        <form name="login-form" class="login-form">
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
          <button
            class="btn btn-outline-light text-nowrap mt-3 input-sm"
            v-on:click="findPw"
            type="button"
          >
            확인
          </button>
        </form>
      </div>
    </section>
  </div>
</template>

<script>
import userHttp from "@/api/user";
export default {
  name: "findPw",
  components: {},
  data() {
    return {
      user: {
        id: "",
        name: "",
        email: "",
      },
    };
  },
  created() {},
  methods: {
    findPw() {
      userHttp.post("/findPw", this.user).then(({ data }) => {
        if (data.message === "success") {
          alert("비밀번호는 " + data.password + "입니다!");
          this.$router.push({ name: "userLogin" });
        } else {
          alert("값을 확인해주세요!");
        }
      });
    },
  },
};
</script>

<style scoped></style>