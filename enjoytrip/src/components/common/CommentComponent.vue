<template>
  <div class="card">
    <div class="card-body">
      <div class="row" style="width: 100%">
        <h5 class="card-title col-md-3" style="float: left">{{ comment.userId }}</h5>
        <p class="card-text col-md-5" style="float: left">{{ comment.content }}</p>
        <p class="card-text col-md-3" style="float: left">
          {{ comment.createTime }}
        </p>
        <button
          v-if="userInfo == comment.userId"
          type="button"
          class="btn btn-primary col-md-1"
          @click="deleteComment"
        >
          삭제
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import http from "@/api/http";
export default {
  name: "CommentComponent",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  props: {
    comment: [],
  },
  computed: {
    ...mapState(["userInfo"]),
  },
  created() {},
  methods: {
    async deleteComment() {
      let accessToken = sessionStorage.getItem("access-token");
      console.log(this.comment.commentId);
      console.log(typeof this.comment.commentId);

      await http.post("/board/comment/delete", this.comment.commentId, {
        headers: { "auth-token": accessToken },
      });
      this.$emit("refresh");
      // const thisRoute = this.$route;
      // this.$router.push({ name: thisRoute.name, params: { articleNo: this.comment.articleNo } });
    },
  },
};
</script>

<style scoped></style>
