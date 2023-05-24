<template>
  <div>
    <section class="page-section masthead" id="contact">
      <div class="container">
        <!-- Contact Section Heading-->
        <h2
          class="page-section-heading text-center text-uppercase text-secondary mb-0"
        >
          {{ msg }}게시판
        </h2>
        <!-- Icon Divider-->
        <div class="divider-custom">
          <div class="divider-custom-line"></div>
          <div class="divider-custom-icon">
            <i class="fas fa-star"></i>
          </div>
          <div class="divider-custom-line"></div>
        </div>
        <!-- Contact Section Form-->
        <div>
          <table class="table board border-1" id="announce-board">
            <thead>
              <tr>
                <th style="width: 15%">번호</th>
                <th style="width: 30%">제목</th>
                <th style="width: 15%">작성자</th>
                <th style="width: 15%">조회수</th>
                <th style="width: 25%">날짜</th>
              </tr>
            </thead>
            <template id="my-table">
              <tbody>
                <board-list-item
                  v-for="(board, index) in paginatedBoards"
                  :key="index"
                  :board="board"
                  :type="type"
                ></board-list-item>
              </tbody>
            </template>
          </table>
          <hr />
          <div style="justify-content: center; display: flex">
            <b-pagination
              v-model="currentPage"
              :total-rows="rows"
              :per-page="perPage"
              aria-controls="my-table"
            ></b-pagination>
            <!-- <ul class="pagination justify-content-center">
              <li>
                <a href="#" style="margin-right: 5px" class="text-secondary"
                  >◀
                </a>
              </li>
              <li>
                <a href="#" style="margin-right: 5px" class="text-secondary"
                  >1
                </a>
              </li>
              <li>
                <a href="#" style="margin-right: 5px" class="text-secondary"
                  >2
                </a>
              </li>
              <li>
                <a href="#" style="margin-right: 5px" class="text-secondary"
                  >3
                </a>
              </li>
              <li>
                <a href="#" style="margin-right: 5px" class="text-secondary"
                  >▶
                </a>
              </li>
            </ul> -->
          </div>
          <div style="display: flex; justify-content: space-between">
            <router-link
              class="btn btn-dark text-nowrap mt-3 input-sm me-3"
              id="write"
              :to="{
                name: `boardWrite`,
                params: { msg: msg, type: type },
              }"
              >글쓰기</router-link
            >
            <div style="display: flex">
              <input
                type="text"
                v-model="keyword"
                class="mt-3 me-3"
                @keyup.enter="serch"
              />
              <button
                type="button"
                @click="serch"
                class="btn btn-dark text-nowrap mt-3 input-sm me-3 text-right"
              >
                검색
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import BoardListItem from "@/components/board/BoardListItem.vue";
import http from "@/api/http";
import { mapState } from "vuex";
import { BPagination } from "bootstrap-vue";
export default {
  name: "BoardView",
  components: { BoardListItem, BPagination },
  data() {
    return {
      boards: [],
      keyword: "",
      token: "",
      perPage: 10,
      currentPage: 1,
    };
  },
  props: {
    msg: String,
    type: String,
  },
  async created() {
    let accessToken = sessionStorage.getItem("access-token");
    if (this.isLogin == true) {
      console.log("created");
      if (this.type == 1) {
        await http
          .get("/board/announceList", {
            headers: {
              "auth-token": accessToken,
            },
          })
          .then((response) => {
            this.boards = response.data;
          });
      } else {
        await http
          .get("/board/freeList", {
            headers: { "auth-token": accessToken, test: "test" },
          })
          .then((response) => {
            this.boards = response.data;
          });
      }
    }
  },
  computed: {
    ...mapState(["isLogin"]),
    rows() {
      return this.boards.length;
    },
    paginatedBoards() {
      const startIndex = (this.currentPage - 1) * this.perPage;
      const endIndex = startIndex + this.perPage;
      return this.boards.slice(startIndex, endIndex);
    },
  },
  beforeMount() {
    if (this.isLogin == false) {
      alert("로그인이 필요합니다!");
      this.$router.push({ name: "userLogin" });
    }
  },
  watch: {
    type: async function (newVal, oldVal) {
      // props가 변경될 때마다 실행되는 동작
      console.log("myProp이 변경되었습니다.", newVal, oldVal);
      let accessToken = sessionStorage.getItem("access-token");

      if (this.isLogin == true) {
        if (this.type == 1) {
          await http
            .get("/board/announceList", {
              headers: { "auth-token": accessToken, test: "test" },
            })
            .then((response) => {
              this.boards = response.data;
            });
        } else {
          await http
            .get("/board/freeList", {
              headers: { "auth-token": accessToken, test: "test" },
            })
            .then((response) => {
              this.boards = response.data;
            });
        }
      }
    },
  },
  methods: {
    async serch() {
      if (this.isLogin == true) {
        let accessToken = sessionStorage.getItem("access-token");
        await http
          .get("/board/search/" + this.keyword, {
            headers: {
              type: this.type,
              "auth-token": accessToken,
              test: "test",
            },
          })
          .then((response) => {
            this.boards = response.data;
          });
      }
    },
  },
};
</script>

<style scoped></style>
