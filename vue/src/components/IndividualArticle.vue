<template>
  <div>
    <PollyAudio></PollyAudio>
    <h1>{{ articles.title }}</h1>
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
    articles: {
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

div {
  border: 1px solid blue;
  
  h1 {
    font-size: 5em;
  }
}
</style>