<template>
  <div class="home-page">
    <header>
      <router-link :to="{ name: 'landing'}" >
        <h4>Unmask - <span>Stories Untold</span></h4>  
      </router-link>
    </header>
    <div class="left-side-bar side-bar" v-if="login">
      <router-link :to="{ name: 'landing'}">
        Preference
      </router-link>
      <router-link :to="{ name: 'favourite'}"> 
        Favourite
      </router-link>
      <router-link to="" @click.native="logout"> 
        Sign Out
      </router-link>
    </div>
    <div class="left-side-bar side-bar" v-else>
      <router-link :to="{ name: 'login'}"> 
        Login
      </router-link>
    </div>

    <div class="right-side-bar side-bar">
      <router-link :to="{ name: 'adminAddedArticles', params: { 'createdByAdmin': false } }">
        Stories
      </router-link>
      <router-link :to="{ name: 'landing'}">
        Search
      </router-link>
      <router-link :to="{ name: 'adminAddedArticles', params: { 'createdByAdmin': true } }">
        Articles
      </router-link>
      <router-link :to="{ name: 'newstory'}">
        Share
      </router-link>
    </div>

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
import _ from 'lodash'

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

  // created:function() {
  //   let loginStatus = this.$cookies.get("loginStatus")
  //   if (loginStatus=="200"){
  //     this.login=true;
  //   }
  // },
  methods: {
    intersecting: function() {
      this.infiniteCounter++;
    },
    
    reverseSlug: function(keyword) {
      return keyword.split('-').join(' ');
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
      let articles = this.$store.getters.getArticles;
      let articlesToReturn = undefined;

      let tagFilter = this.$route.params.tag;
      let countryFilter = this.$route.params.country;
      let authorFilter = this.$route.params.author;
      let searchFilter = this.$route.params.search;
      let adminAddedFilter = this.$route.params.createdByAdmin;

      // List articles/stories based on tag
      if (typeof tagFilter !== "undefined")
        articlesToReturn = _.filter(articles, (article) => article.tags.includes(this.reverseSlug(tagFilter)));

      // List articles/stories based on country
      else if (typeof countryFilter !== "undefined")
        articlesToReturn = _.filter(articles, { 'country': this.reverseSlug(countryFilter) });

      // List articles/stories based on author/source
      else if (typeof authorFilter !== "undefined") 
        articlesToReturn = _.filter(articles, { 'source': this.reverseSlug(authorFilter) });

      // List articles/stories based on search keyword
      else if (typeof searchFilter !== "undefined") {
        articlesToReturn= _.filter(articles, (article) => {
          searchFilter = this.reverseSlug(searchFilter);
          // check if searched keyword is a country
          if (article.country === searchFilter) return true;
          // check if searched keyword is an author/source
          if (article.source === searchFilter) return true;
          // check if searched keyword is a title
          if (article.title === searchFilter) return true;
          // check if searched keyword is a tag
          if (article.tags.includes(searchFilter)) return true;
          // keyword not found
          return false;
        });
      }

      // list articles added by admins, i.e. not stories added by users
      else if (typeof adminAddedFilter !== "undefined") 
        articlesToReturn = _.filter(articles, { 'createdByAdmin': adminAddedFilter })

      // Return the relevant articles
      if (typeof articlesToReturn !== "undefined" && articlesToReturn.length !== 0) return articlesToReturn;
      return articles;
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
  z-index: 2;

  span {
    color: red;
  }
}

.side-bar {
  height: 100%;
  position: fixed;
  z-index: 2;
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
</style>
