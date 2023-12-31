import Vue from "vue";
import Vuex from "vuex";
import user from "@/api/user";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

// const store = createStore({
//   // ...
//   plugins: [createPersistedState()],
// });

export default new Vuex.Store({
  state: {
    userInfo: "",
    isLogin: false,
    plans: [],
  },
  getters: {},
  mutations: {
    SET_USERINFO(state, user) {
      state.userInfo = user;
    },
    SET_IS_LOGIN(state, check) {
      console.log(state);
      state.isLogin = check;
    },
    SET_PLAN(state, attr) {
      state.plans.push(attr);
    },
    DELETE_PLAN(state, index) {
      state.plans.splice(index, 1);
    },
    DELETE_PLANS(state) {
      state.plans.splice(0);
    },
  },
  actions: {
    async login({ commit }, user2) {
      try {
        const response = await user.post("/login", {
          id: user2.userId,
          password: user2.userPw,
        });
        console.log("asdasd", response);
        if (response.data.message === "success") {
          console.log("로그인 성공");
          let accessToken = response.data["access-token"];
          let refreshToken = response.data["refresh-token"];
          commit("SET_IS_LOGIN", true);
          commit("SET_USERINFO", user2.userId);
          sessionStorage.setItem("access-token", accessToken);
          sessionStorage.setItem("refresh-token", refreshToken);
          return true;
        } else {
          commit("SET_IS_LOGIN", false);
          commit("SET_USERINFO", "");
          return false;
        }
      } catch (error) {
        console.log("로그인 오류: ", error);
        throw error;
      }
    },
    async logout({ commit }) {
      try {
        const response = await user.get("/logout/" + this.userInfo);

        if (response.data.message === "success") {
          console.log("로그아웃 성공");
          commit("SET_IS_LOGIN", false);
          commit("SET_USERINFO", "");
          sessionStorage.removeItem("access-token", "");
          sessionStorage.removeItem("refresh-token", "");
          alert("로그아웃 성공");
          return true;
        } else {
          commit("SET_IS_LOGIN", false);
          commit("SET_USERINFO", "");
          return false;
        }
      } catch (error) {
        console.log("로그인 오류: ", error);
        throw error;
      }
    },
    async deleteUser({ commit }, userId) {
      try {
        let accessToken = sessionStorage.getItem("access-token");
        const response = await user.post("/delete/" + userId, {
          headers: {
            "auth-token": accessToken,
          },
        });

        if (response.data.message === "success") {
          console.log("탈퇴 성공");
          commit("SET_IS_LOGIN", false);
          commit("SET_USERINFO", "");
          sessionStorage.removeItem("access-token", "");
          sessionStorage.removeItem("refresh-token", "");
          alert("탈퇴 성공");
          return true;
        } else {
          commit("SET_IS_LOGIN", false);
          commit("SET_USERINFO", "");
          return false;
        }
      } catch (error) {
        console.log("탈퇴 오류: ", error);
        throw error;
      }
    },
  },
  modules: {},

  plugins: [createPersistedState()],
});
