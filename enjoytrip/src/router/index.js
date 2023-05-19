import Vue from "vue";
import VueRouter from "vue-router";
import MainView from "../views/MainView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    component: MainView,
  },
  {
    path: "/map",
    name: "map",
    component: () => import("@/views/MapView"),
  },
  {
    path: "/board",
    name: "board1",
    component: () => import("@/views/BoardView"),
    props: true,
  },
  {
    path: "/board",
    name: "board2",
    component: () => import("@/views/BoardView"),
    props: true,
  },
  {
    path: "/detail",
    name: "boardDetail",
    component: () => import("@/components/board/Detail"),
    props: true,
  },
  {
    path: "/write",
    name: "boardWrite",
    component: () => import("@/components/board/BoardWrite"),
    props: true,
  },
  {
    path: "/login",
    name: "userLogin",
    component: () => import("@/views/LoginView"),
    props: true,
  },
  {
    path: "/signin",
    name: "signIn",
    component: () => import("@/views/SignInView"),
    props: true,
  },
  {
    path: "/findpw",
    name: "findPw",
    component: () => import("@/views/FindPasswordView"),
    props: true,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
