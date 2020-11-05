<template>
  <div class="landing-page">
    <h1 :class="{glitch: this.clicked}" data-text="Unmask" :style="hoverStyle" @mouseover="mouseover" @mouseleave="mouseleave" @click.once="glitchPlay">Unmask</h1>
  </div>
</template>

<script>
import { router } from '../routes.js'

export default {
  name: 'LandingPage',
  data: function() {
    return {
      borderOpacity: 0.5,
      clicked: false,
    }
  },
  methods: {
    mouseover: function() {
      this.borderOpacity = 1;
    },
    mouseleave: function() {
      this.borderOpacity = 0.5;
    },
    glitchPlay: function() {
      var audio = new Audio(require('../assets/audio/glitch.mp3'));
      this.clicked = true;
      audio.play();
      audio.onended = () => {
        this.clicked = false;
        router.push({ name: 'tags' });
      };
    },
  }, 
  computed: {
    hoverStyle: function() {
      return {
        border: `1px solid rgba(255, 255, 255, ${this.borderOpacity})`
      }
    },
  }
}
</script>

<style scoped lang='scss'>
@import "~@/assets/scss/_typo.scss";
@import "~@/assets/scss/_glitch.scss";

h1 {
  line-height: 1.8;
  font-size: 2em;
  font-family: CustomY78;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  
  padding: 0 2rem;
  letter-spacing: .2em;
  cursor: crosshair;
}

.landing-page {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
