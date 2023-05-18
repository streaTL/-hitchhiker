<template>
  <tr>
    <td>{{ board.articleNo }}</td>
    <!-- <td v-if="msg == '공지'"> -->
    <td>
      <router-link
        :to="{
          name: `boardDetail`,
          params: { articleNo: board.articleNo, type: type },
        }"
        class="article-title link-dark"
        style="text-decoration: none"
        >{{ board.subject }}
      </router-link>
    </td>

    <td>{{ board.userId }}</td>
    <td>{{ board.hit }}</td>
    <td>{{ board.date }}</td>
  </tr>
</template>

<script>
export default {
  components: {},
  data() {
    return {};
  },
  props: {
    board: Object,
    type: String,
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>