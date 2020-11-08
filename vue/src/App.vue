<template>
  <router-view></router-view>
</template>

<script>
export default {
  name: 'App',
  async created() {
    // await this.$store.dispatch('setData');
  }
}
</script>

<style lang='scss'>
@import "~@/assets/scss/_themes.scss";
* { 
  
  box-sizing: box-border;
  margin: 0;
  padding: 0;
  color: white;
  background: black;
  &::selection {
    color: red;
  }
}
body {
  @include fontsize();
  min-height: 100vh;
  line-height: 1.3;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  
  display: flex;
  justify-content: center;
  align-items: center;
}

</style>
