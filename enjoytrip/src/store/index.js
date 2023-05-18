import Vue from "vue";
import Vuex from "vuex";
import user from "@/api/user";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    userInfo: "",
    isLogin: false,
  },
  getters: {},
  mutations: {
    SET_USERINFO(state, user) {
      this.userInfo = user;
    },
    SET_IS_LOGIN(state, check) {
      this.isLogin = check;
    },
  },
  actions: {
    async login({ commit }, user2) {
      user
        .get("/login", {
          userId: user2.userId,
          userPw: user2.userPw,
        })
        .then(({ data }) => {
          if (data.message == "success") {
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_USERINFO", user2.userId);
            sessionStorage.setItem("access-token", accessToken);
            sessionStorage.setItem("refresh-token", refreshToken);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_USERINFO", null);
          }
        });
    },
  },
  modules: {},
});
