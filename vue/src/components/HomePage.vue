<template>

  <div id="list" class="home-page">
    <header>
      <router-link :to="{ name: 'landing'}" >
        <h4>Unmask - Stories Untold</h4>  
      </router-link>
    </header>
    <div class="left-side-bar side-bar">
      <router-link :to="{ name: 'landing'}" v-if="login">
        Preference
      </router-link>
      <router-link :to="{ name: 'favourite'}" v-if="login"> 
        Favourite
      </router-link>
      <router-link to="" v-if="login" @click.native="logout"> 
        LOGOUT
      </router-link>
      <router-link :to="{ name: 'login'}" v-else> 
        LOGIN
      </router-link>
      
    </div>
    <div class="right-side-bar side-bar">
      <router-link :to="{ name: 'landing'}">
        Stories
      </router-link>
      <router-link :to="{ name: 'landing'}">
        Search
      </router-link>
      <router-link :to="{ name: 'landing'}">
        Articles
      </router-link>
    </div>
    <template v-for="repeat in infiniteCounter">
    <div :key="repeat" class="article">
      <IndividualArticle
        v-for="article in articles"
        v-bind:key="`${article.id}_${repeat}`"
        v-bind:article="article"
        class="individual-article"
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
      login:false,
    }
  },
  components: {
    Observer,
    IndividualArticle,

  },
  created:function(){
      let loginStatus = this.$cookies.get("loginStatus")
      if (loginStatus=="200"){
        this.login=true;
      }
    },
  methods: {
    intersecting: function() {
      this.infiniteCounter++;
    },
    logout:function() {
      this.$cookies.remove("loginStatus");
      this.$cookies.remove("username");
      this.$cookies.remove("userEmail");
      this.$cookies.remove("userID");
      this.$router.push("/landing");
    }
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

a {
  text-decoration: none;
}

header {
  display: flex;
  justify-content: center;
  position: fixed;
  padding: 5px 0 5px 0;
  top: 0;
  width: 100%;
}

.side-bar {
  height: 100%;
  position: fixed;
  z-index: 1;
  top: 0;
  overflow: hidden;
  text-orientation: upright;
  writing-mode: vertical-lr;

  display: flex;
  align-content: space-around;
  justify-content: space-around;

  padding: 0 3px 0 3px;

  a {
    color: red;
  }
}

.left-side-bar {
  left: 0;
}

.right-side-bar {
  right: 0;
}

.home-page {
  font-family: "CustomY78";
  width: 100%;
}

.article {
  display: flex;
  justify-content: center;
  align-content: center;
  font-size: 2em;
}

.individual-article {
  min-height: 100vh;
  min-width: 100vw;
}

</style>
