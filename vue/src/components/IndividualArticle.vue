<template>
  <div class="article-template">
    <div class="article-container">
      <PollyAudio v-bind:text="article.text"></PollyAudio>
      <h1 class=title>{{ article.title }}</h1>
      <p class="author">{{ article.source }}</p>
      <p class="country">{{ article.country }}</p>
      <p class="tags">
        <template v-for="tag in article.tags">
          <span :key="tag">{{ tag }}</span>
        </template>
      </p>
    </div>
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

.article-template {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  min-width: 100%;
  font-size: 2em;
  border: 2px solid red;

  .article-container {
    width: 60vw;
    min-height: 50vh;

    border: 2px solid red;
    text-align: center;

    h1 {
      font-size: 8vh;
    }
    
    .title, .author {
      background: none;
      position: relative;
      left: -50%;
      top: -100%;
    }

    .country {
      background: none;
      position: relative;
      right: -40%;
      transform: rotate(-90deg);
    }


  }
}
</style>