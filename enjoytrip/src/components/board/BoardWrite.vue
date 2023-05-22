<template>
  <div>
    <section class="page-section masthead" id="contact">
      <div class="container">
        <div class="row justify-content-center">
          <h1>{{ msg }}게시판 작성</h1>
          <form action="" method="post">
            <input
              type="text"
              name="subject"
              class="form-control mt-4 mb-2"
              placeholder="제목을 입력해주세요."
              v-model="board.subject"
              required
            />
            <div class="form-group">
              <textarea
                class="form-control"
                rows="10"
                name="content"
                placeholder="내용을 입력해주세요"
                v-model="board.content"
                required
              ></textarea>
            </div>
            <div style="display: flex; justify-content: flex-end">
              <button
                type="button"
                class="btn btn-secondary mt-3 mb-3"
                @click="submit"
              >
                제출하기
              </button>
            </div>
          </form>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";

export default {
  name: "BoardWrite",
  components: {},
  data() {
    return {
      board: {
        content: "",
        subject: "",
        type: 0,
      },
    };
  },
  computed: {
    ...mapState(["userInfo"]),
  },
  props: {
    type: String,
    msg: String,
  },
  created() {},
  methods: {
    submit() {
      let accessToken = sessionStorage.getItem("access-token");
      console.log(accessToken);
      this.board.type = this.type;
      console.log(this.board);
      http.post(
        "/board/write",
        {
          content: this.board.content,
          subject: this.board.subject,
          type: this.type,
          userId: this.userInfo,
        },
        {
          headers: {
            "auth-token": accessToken,
          },
        }
      );
      let board;
      if (this.type == 1) {
        board = "board1";
      } else {
        board = "board2";
      }
      this.$router.push({
        name: board,
        params: { type: this.type, msg: this.msg },
      });
    },
  },
};
</script>

<style scoped></style>