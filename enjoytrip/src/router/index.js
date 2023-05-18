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
    path: "detail",
    name: "boardDetail",
    component: () => import("@/components/board/Detail"),
    props: true,
  },
  {
    path: "write",
    name: "boardWrite",
    component: () => import("@/components/board/BoardWrite"),
    props: true,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
