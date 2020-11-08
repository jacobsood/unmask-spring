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
    <!-- Like button -->
    <div v-if="loginStatus">
    <svg v-if="heart" @click="like" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-heart" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
      <path fill-rule="evenodd" d="M8 2.748l-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"></path>
    </svg>
    <svg v-else @click="like" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-heart-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
      <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"></path>
    </svg>
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
  created:async function(){
    if (this.$cookies.get("loginStatus")=="200"){
      this.loginStatus=true
    }
    var params = {
        articleID: this.article.id,
        username: this.$cookies.get("username")
      }
      
     var res = await this.$axios.post(
                "/api/checkLikeStatus",
                qs.stringify(params)
                ).then((response)=>{
                    return response.data
                })
      console.log(res)
      if (res){
        this.heart=true
        console.log(1)
      }else{
        this.heart=false
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
            })
      if (res){
        this.heart=true
      }else{
        this.heart=false;
      }

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
  .bi {
    margin-left: 10px;
  }
}
</style>