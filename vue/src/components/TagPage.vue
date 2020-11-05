<template>
  <div id="list" class="tag-page">
    <PollyAudio></PollyAudio>
    <template v-for="repeat in infiniteCounter">
    <ul :key="repeat">
      <h1 :style="{ fontSize: '4em'}">{{ repeat }}</h1>
      <li v-for="(item, index) in collection" :key="`${repeat} - ${item}`">
        <span @click="getArticles(item)" :class="assignClass(index, repeat)">{{ item }}</span>
      </li>
    </ul>
    </template>
    <observer @intersect="intersecting"></observer>
  </div>
</template>

<script>
import Observer from './Observer';
import PollyAudio from './PollyAudio';
import _ from 'lodash'
import { router } from '../routes.js'

export default {
  name: 'TagPage',
  data: function() {
    return {
      infiniteCounter: 1,
    }
  },
  components: {
    Observer,
    PollyAudio,
  },
  methods: {
    assignClass: function(index, repeat) {
      return {
        evenRepeatEven: index % 2 === 0 && repeat % 2 === 0,
        evenRepeatOdd: index % 2 === 1 && repeat % 2 === 0,
        oddRepeatEven: index % 2 === 0 && repeat % 2 === 1,
        oddRepeatOdd: index % 2 === 1 && repeat % 2 === 1,
      }
    },
    intersecting: function() {
      this.infiniteCounter++;
    },
    getArticles: async function(item) {
      let slugify = item.split(' ').join('-');
      
      if (this.$store.getters.isTag(item)) {
        router.push({ name: 'tag', params: { tag: slugify }});
      }
      else if (this.$store.getters.isCountry(item)) {
        router.push({ name: 'country', params: { country: slugify } });
      }
      else if (this.$store.getters.isArticle(item)) {
        router.push({ name: 'article', params: { tag: this.$route.params.tag, article: slugify }})
      }
    },
  },
  computed: {
    collection: function() {
      const article = this.$route.params.article;
      const tag = this.$route.params.tag;
      const country = this.$route.params.country;
      
      try {
        if (_.isEmpty(this.$route.params)) {
           return this.$store.getters.getCollection;
        }
        else if (article !== undefined) {
          return this.$store.getters.getArticleByName(article);
        }
        else if (tag !== undefined) {
          return this.$store.getters.getArticlesByTag(tag);
        }
        else if (country !== undefined) {
          return this.$store.getters.getArticlesByCountry(country);
        }
      }
      catch (e) {
        console.log(e);
      }
      return '';
    },
  },
}
</script>

<style scoped lang='scss'>
@import "~@/assets/scss/_typo.scss";

.tag-page {
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
  
  h1 {
    font-size: 2em;
    width: 100%;
    text-align: center;
    color: #302f2f;
    z-index: 1;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }
  
  li { 
    margin: 3% 1%;
    z-index: 2;
    background: none;
    
    .oddRepeatEven {
      color: white;
      &::selection {
        color: purple;
      }
    }
    
    .oddRepeatOdd {
      color: yellow;
      &::selection {
        color: red;
      }
    }
    
    .evenRepeatEven {
      color: purple;
      &::selection {
        color: white;
      }
    }
    
    .evenRepeatOdd {
      color: red;
      &::selection {
        color: yellow;
      }
    }
    
    span {
      background: none;
      z-index: 2;
      display: block;
      text-align: center;
      cursor: pointer;
      transition: transform .2s;
      letter-spacing: .25rem;
      
      &:hover {
        position: relative;
        transition: transform .2s;
        transform: scale(1.25);
      }
    }
  }
}

</style>
