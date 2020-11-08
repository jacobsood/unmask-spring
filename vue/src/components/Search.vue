<template>
  <div v-click-outside="goToPreviousPage" class="search">
    <input v-model="filter" placeholder="Search">
    <p @click="search">Search</p>
  </div>
</template>

<script>
import { router } from '../routes'
export default {
  name: 'PreferencePage',
  data: function() {
    return {
        filter: undefined,
    }
  },
  created:function(){
    
  },
  methods: {
    goToPreviousPage: function() {
        router.push({ name: "home" });
    },
    slugify: function(keyword) {
      keyword = keyword.replace(" ", "-");
      return keyword;
    },
    search: function() {
        router.push({ name: "search", params: { 'search': this.slugify(this.filter) } });
    }
  },
}
</script>

<style scoped lang='scss'>
@import "~@/assets/scss/_typo.scss";
@import "~@/assets/scss/_themes.scss";

a {
    text-decoration: none;
}
p {
    color: red;
    margin: 5%;
    cursor: pointer;
}
.search {
    text-align: center;
    display: flex;
    justify-content: center;
    align-items: center;
    color: white;
    border: white;
    font-size: 24px;
}
</style>