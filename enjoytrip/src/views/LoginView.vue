<template>
  <div>
    <section
      class="masthead bg-primary text-white text-center"
      id="loginBackground"
    >
      <div
        class="container d-flex align-items-center flex-column card2 border-white"
      >
        <h1 class="masthead-heading text-uppercase mb-0">Login</h1>

        <div class="divider-custom divider-light">
          <div class="divider-custom-line"></div>
          <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
          <div class="divider-custom-line"></div>
        </div>

        <form action="" name="login-form" class="login-form" method="post">
          <div class="d-flex mb-4 justify-content-md-between">
            <h3 class="masthead-heading text-uppercase mb-0 fs-1 me-5">ID</h3>
            <input
              type="text"
              class="form-control input-sm"
              id="userId"
              v-model="user.userId"
              style="width: 200px"
              @keyup.enter="doLogin"
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
              autocomplete="off"
              v-model="user.userPw"
              style="width: 200px"
              @keyup.enter="doLogin"
            />
          </div>
          <input
            class="btn btn-outline-light text-nowrap mt-3 input-sm me-3"
            type="button"
            id="login"
            @click="doLogin"
            value="로그인"
          />
          <router-link
            class="btn btn-outline-light text-nowrap mt-3 input-sm me-3"
            id="register"
            to="/signin"
          >
            회원가입
          </router-link>
          <router-link
            class="btn btn-outline-light text-nowrap mt-3 input-sm"
            id="findPW"
            to="/findpw"
          >
            PW 찾기
          </router-link>
        </form>
      </div>
    </section>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  name: "LoginView",
  components: {},
  data() {
    return {
      user: {
        userId: "",
        userPw: "",
      },
    };
  },
  created() {},
  computed: {
    ...mapState(["isLogin", "userInfo"]),
  },
  methods: {
    ...mapActions(["login"]),
    async doLogin() {
      await this.login(this.user);
      if (this.isLogin) {
        this.$router.push({ name: "main" });
      } else {
        alert("아이디와 비밀번호를 확인해주세요");
      }
    },
  },
};
</script>

<style scoped></style>
