<template>
  <div class="article-template">
    <div class="article-container">
      <PollyAudio v-bind:text="article.text"></PollyAudio>
      <div class="info">
        <h1 class=title>{{ article.title }}</h1>
        <p><router-link class="author" :to="{ name: 'author', params: { author: slugify(article.source) } }">{{ article.source }}</router-link></p>
      </div>
      <p class="country"><router-link :to="{ name: 'country', params: { country: slugify(article.country) } }">{{ article.country }}</router-link></p>
      <p class="tags">
        <template v-for="tag in article.tags">
          <router-link :key="tag.id" :to="{ name: 'tag', params: { tag: slugify(tag.tag) } }" >
            <span :key="tag.id">{{ tag.tag }}</span>
          </router-link>
        </template>
      </p>
      <div class="typedjs">
        <vue-typed-js :startDelay="1000" :typeSpeed="50" :strings="[article.text]" :loop="true">
          <p class="typing"></p>
        </vue-typed-js>
      </div>
      <!-- Like button -->
      <div v-if="this.$cookies.get('loginStatus')=='200'">
        <svg v-if="heart" @click="like" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-heart-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
          <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"></path>
        </svg>
        <svg v-else @click="like" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-heart" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
          <path fill-rule="evenodd" d="M8 2.748l-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"></path>
        </svg>
      </div>
    </div>
  </div>
</template>

<script>
import PollyAudio from './PollyAudio';
import qs from 'qs'
export default {
  name: 'IndividualArticle',
  components: {
    PollyAudio,
  },

  data() {
    return {
      heart: false,
      loginStatus:false
    }
  },

  props: {
    article: {
      type: Object,
    }
  },
  methods: {
    async like(){ // method run on like icon click
      var params = {
        articleID: this.article.id,
        username: this.$cookies.get("username")
      }

      var res = await this.$axios.post(
        "/api/likeArticle",
        qs.stringify(params)
      ).then((response)=>{
        return response.data
      });

      if (res==true){
        this.heart=true
      }else{
        this.heart=false;
      }
    },

    slugify: function(keyword) {
      keyword = keyword.replace(" ", "-");
      return keyword;
    }
  }
}
</script>

<style scoped lang='scss'>
@import "~@/assets/scss/_typo.scss";
a {
  text-decoration: none;
}
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
    height: 50vh;
    border: 2px solid red;
    text-align: center;
    h1 {
      font-size: 6vh;
    }
    p {
      font-size: 3.5vh;
    }
    
    .info {
      background: none;
      width: 35vw;
      height: 30vh;
      position: relative;
      left: -17.5vw;
      top: 25%;
      .author {
        color: red;
      }
    }
    .country {
      width: 15vw;
      font-size: 4vh;
      position: relative;
      right: -45vw;
      transform: rotate(-90deg);
      top: -33vh;
      z-index: 2;
    }
    .tags {
      background: none;
      position: relative;
      width: 60vh;
      right: -92vh;
      top: 2vh;
      span {
        margin: 0 1% 0 1%;
      }
    }
    .typedjs {
      background: none;
      z-index: 1;
      position: relative;
      transform: rotate(-20deg);
      left: 26vh;
      top: -23vh;
      width: 40vw;
      height: 20vh;
      overflow: hidden;
      .typing {
        font-family: GTSectraFine;
        font-size: 2.5vh;
      }
    }
  }
}
</style>