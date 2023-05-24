<template>
  <div>
    <section class="page-section masthead" id="contact">
      <div class="container">
        <div class="row justify-content-center">
          <h1>{{ board.articleNo }}. {{ board.subject }}</h1>
          <form action="writerAction" method="post">
            <input
              type="text"
              name="bdUserId"
              class="form-control mt-4 mb-2"
              v-model="board.userId"
              :disabled="!able"
            />
            <div class="form-group">
              <textarea
                class="form-control"
                rows="10"
                name="bdContent"
                v-model="board.content"
                :disabled="!able"
              ></textarea>
            </div>
            <div v-if="userInfo == board.userId" style="display: flex; justify-content: flex-end">
              <button
                type="button"
                v-if="able == false"
                v-on:click="disabledToAble"
                class="btn btn-secondary mt-3 mb-3 me-3"
              >
                수정
              </button>
              <button
                type="button"
                v-if="able == true"
                v-on:click="modifyBoard"
                class="btn btn-secondary mt-3 mb-3 me-3"
              >
                완료
              </button>
              <button type="button" v-on:click="deleteBoard" class="btn btn-secondary mt-3 mb-3">
                삭제
              </button>
            </div>
          </form>
        </div>
      </div>
      <div v-if="!able" class="container" style="justify-content: start">
        <!-- 댓글  -->
        <comment-component
          @refresh="refresh"
          v-for="(comment, index) in comments"
          :key="index"
          :comment="comment"
        ></comment-component>
        <!-- 댓글 입력창 -->
        <h5 class="mt-4">댓글 입력창</h5>
        <input type="text" class="form-control" v-model="newComment" />
        <div class="mt-2" style="display: flex; justify-content: end">
          <button type="button" @click="writeComment" class="btn btn-primary">작성</button>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
import CommentComponent from "../common/CommentComponent.vue";
export default {
  name: "boardDetail",
  components: { CommentComponent },
  data() {
    return {
      board: [],
      able: false,
      comments: [],
      newComment: "",
      // {
      //   commentId:
      //     userId:
      //   content:
      //   createTime:
      // }
    };
  },
  computed: {
    ...mapState(["userInfo"]),
  },
  props: {
    articleNo: Number,
    type: String,
  },
  async created() {
    let accessToken = sessionStorage.getItem("access-token");
    const url = "http://192.168.31.65/board/";
    await axios
      .get(url + this.articleNo, { headers: { "auth-token": accessToken } })
      .then((data) => (this.board = data.data));
    await axios
      .get(url + "comment/" + this.articleNo, { headers: { "auth-token": accessToken } })
      .then((comments) => (this.comments = comments.data));
  },
  methods: {
    refresh() {
      let accessToken = sessionStorage.getItem("access-token");
      axios
        .get("http://192.168.31.65/board/comment/" + this.articleNo, {
          headers: { "auth-token": accessToken },
        })
        .then((comments) => (this.comments = comments.data));
    },
    disabledToAble() {
      console.log("변경!!!");

      console.log(this.able);
      this.able = !this.able;
    },
    async modifyBoard() {
      let accessToken = sessionStorage.getItem("access-token");
      const url = "http://192.168.31.65/board/modify/" + this.articleNo;
      await axios
        .post(url, {
          subject: this.board.subject,
          content: this.board.content,
          headers: { "auth-token": accessToken },
        })
        .then((data) => console.log(data));
      let msg = "";
      let board;
      if (this.type == "1") {
        msg = "공지";
        board = "board1";
      } else {
        msg = "자유";
        board = "board2";
      }
      this.$router.push({
        name: board,
        params: { type: this.type, msg: msg },
      });
    },
    async deleteBoard() {
      let accessToken = sessionStorage.getItem("access-token");
      const url = "http://192.168.31.65/board/delete/" + this.articleNo;
      await axios
        .get(url, { headers: { "auth-token": accessToken } })
        .then((data) => console.log(data));

      let msg = "";
      let board;
      if (this.type == "1") {
        msg = "공지";
        board = "board1";
      } else {
        msg = "자유";
        board = "board2";
      }
      this.$router.push({
        name: board,
        params: { type: this.type, msg: msg },
      });
    },
    async writeComment() {
      let accessToken = sessionStorage.getItem("access-token");
      const url = "http://192.168.31.65/board/comment/write";
      await axios
        .post(
          url,
          {
            userId: this.userInfo,
            content: this.newComment,
            articleNo: this.board.articleNo,
          },
          { headers: { "auth-token": accessToken } }
        )
        .then((data) => console.log(data));
      this.newComment = "";
      await axios
        .get("http://192.168.31.65/board/comment/" + this.articleNo, {
          headers: { "auth-token": accessToken },
        })
        .then((comments) => (this.comments = comments.data));
    },
  },
};
</script>

<style scoped></style>
