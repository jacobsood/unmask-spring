<template>
  <div id="app">
    <div id="article-header">
      <div class="article-header-member">{{ article.title }}</div>
      <div class="article-header-member">{{ article.source }}</div>
      <div>{{ article.tag }}</div>
    </div>
    <div class="article">
      <p>{{ article.text }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "Article",
  props: ["article"],
};
</script>

<style scoped>
.app {
  background-color: white;
}
.article {
  color: black;
  background-color: #dbdbdb;
  min-height: 400px;
  padding-left: 10px;
  padding-top: 1px;
  position: relative;
  border-radius: 1rem;
  margin-top: 10px;
}

#article-header {
  display: flex;
  box-sizing: border-box;
}
.article-header-member {
  position: relative;
  width: 100%;
}
</style>