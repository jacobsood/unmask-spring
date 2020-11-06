<template>
  <div id="list" class="home-page">
    <template v-for="repeat in infiniteCounter">
    <div :key="repeat">
      <IndividualArticle
        v-for="article in articles"
        v-bind:key="`${article.id}_${repeat}`"
        v-bind:article="article"
      ></IndividualArticle>
    </div>
    </template>
    <observer @intersect="intersecting"></observer>
  </div>
</template>

<script>
import Observer from './Observer';
import IndividualArticle from './IndividualArticle';

export default {
  name: 'HomePage',
  data: function() {
    return {
      infiniteCounter: 1,
    }
  },
  components: {
    Observer,
    IndividualArticle,
  },
  methods: {
    intersecting: function() {
      this.infiniteCounter++;
    },
  },
  computed: {
    articles: function() {
      return this.$store.getters.getArticles;
    },
  },
}
</script>

<style scoped lang='scss'>
@import "~@/assets/scss/_typo.scss";

.home-page {
  font-family: "CustomY78";
  width: 100%;
  margin-left: 5%;
  margin-right: 5%;
}

ul {
  background: none;
  list-style: none;
  font-size: 2em;
  display: flex;
  justify-content: space-around;
  flex-flow: row wrap;
  position: relative;
}

</style>
