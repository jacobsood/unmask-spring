<template>
  <div class="article-container">
    <PollyAudio></PollyAudio>
    <h1>{{ article.title }}</h1>
    <p>{{ article.text }}</p>
  </div>
</template>

<script>
import PollyAudio from './PollyAudio';
export default {
  name: 'IndividualArticle',
  components: {
    PollyAudio,
  },
  props: {
    article: {
      type: Object,
    }
  }
}
</script>

<style scoped lang='scss'>
@import "~@/assets/scss/_typo.scss";

.article-page {
  font-family: "CustomY78";
  margin-left: 5%;
  margin-right: 5%;
}

.article-container {
  border: 2px solid red;
  display: flex;
  justify-content: center;
  align-items: center;
  
  h1 {
    font-size: 2em;
  }
}
</style>