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
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>조회수</th>
                <th>날짜</th>
              </tr>
            </thead>
            <tbody>
              <board-list-item
                v-for="(board, index) in boards"
                :key="index"
                :board="board"
                :type="type"
              ></board-list-item>
            </tbody>
          </table>
          <hr />
          <div>
            <ul class="pagination justify-content-center">
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
            </ul>
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
              <input type="text" v-model="keyword" class="mt-3 me-3" />
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

export default {
  name: "BoardView",
  components: { BoardListItem },
  data() {
    return {
      boards: [],
      keyword: "",
    };
  },
  props: {
    msg: String,
    type: String,
  },
  created() {
    console.log("created");
    if (this.type == 1) {
      http.get("/board/announceList").then((response) => {
        this.boards = response.data;
      });
    } else {
      http.get("/board/freeList").then((response) => {
        this.boards = response.data;
      });
    }
  },
  watch: {
    type: function (newVal, oldVal) {
      // props가 변경될 때마다 실행되는 동작
      console.log("myProp이 변경되었습니다.", newVal, oldVal);
      if (this.type == 1) {
        http.get("/board/announceList").then((response) => {
          this.boards = response.data;
        });
      } else {
        http.get("/board/freeList").then((response) => {
          this.boards = response.data;
        });
      }
    },
  },
  methods: {
    serch() {
      http
        .get("/board/search/" + this.keyword, {
          headers: {
            type: this.type,
          },
        })
        .then((response) => {
          this.boards = response.data;
        });
    },
  },
};
</script>

<style scoped></style>