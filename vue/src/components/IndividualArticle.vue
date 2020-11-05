<template>
  <PollyAudio></PollyAudio>
  <div>
    <!-- implement here -->
  </div>
</template>

<script>
import { router } from '../routes.js'
import PollyAudio from './PollyAudio';
export default {
  name: 'IndividualArticle',
  components: {
    PollyAudio,
  },
  methods: {
  },
  computed: {
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