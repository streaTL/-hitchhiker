<template>
  <div>
    <nav
      class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top"
      id="mainNav"
    >
      <div class="container">
        <router-link class="navbar-brand" to="/">Go! Trip!</router-link>
        <!-- <a class="navbar-brand" href="${root}/">Go! Trip!</a> -->
        <button
          class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarResponsive"
          aria-controls="navbarResponsive"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          Menu <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item mx-0 mx-lg-1">
              <router-link
                class="btn-secondary nav-link py-3 px-0 px-lg-3 rounded"
                to="/map"
                >Search</router-link
              >
              <!-- <a
                class="nav-link py-3 px-0 px-lg-3 rounded"
                href="${root}/board/map"
                >Search</a
              > -->
            </li>
            <li class="nav-ityem mx-0 mx-lg-1">
              <div class="dropdown">
                <button
                  class="btn btn-secondary border-0 dropdown-toggle py-3 px-0 px-lg-3 rounded"
                  type="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  Board
                </button>
                <ul class="dropdown-menu">
                  <li>
                    <router-link
                      class="dropdown-item"
                      :to="{
                        name: `board1`,
                        params: {
                          msg: '공지',
                          type: '1',
                        },
                      }"
                      >anounce</router-link
                    >
                    <router-link
                      class="dropdown-item"
                      :to="{
                        name: `board2`,
                        params: {
                          msg: '자유',
                          type: '2',
                        },
                      }"
                      >Free Board</router-link
                    >
                    <!-- <a class="dropdown-item" href="${root}/board/freeList"
                      >Free Board</a
                    > -->
                  </li>
                </ul>
              </div>
            </li>
            <li v-if="userInfo == ``" class="nav-item mx-0 mx-lg-1">
              <router-link
                class="nav-link btn-secondary py-3 px-0 px-lg-3 rounded"
                to="/login"
                style="display: block"
                id="loginNavBtn"
                >Login</router-link
              >
            </li>
            <li
              v-if="userInfo != ``"
              class="nav-item mx-0 mx-lg-1"
              id="myPageBtn"
            >
              <router-link
                class="nav-link btn-secondary py-3 px-0 px-lg-3 rounded"
                to="/myPage"
                >My Page</router-link
              >
            </li>
            <li v-if="userInfo != ``" class="nav-item mx-0 mx-lg-1">
              <a
                class="nav-link btn-secondary border-0 py-3 px-0 px-lg-3 rounded"
                type="button"
                v-on:click="doLogout"
                value="Logout"
              >
                Logout
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  name: "NavComponentt",
  components: {},
  data() {
    return {};
  },
  computed: {
    ...mapState(["isLogin", "userInfo"]),
  },
  created() {},
  methods: {
    ...mapActions(["logout"]),
    async doLogout() {
      await this.logout();

      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
  },
};
</script>

<style scoped></style>