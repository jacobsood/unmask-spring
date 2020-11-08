<template>
  <router-view></router-view>
</template>

<script>
export default {
  name: 'App',
  async created() {
    await this.$store.dispatch('setData');
  }
}
</script>

<style lang='scss'>
@import "~@/assets/scss/_themes.scss";

</style>
