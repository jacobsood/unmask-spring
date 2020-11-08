<template>
  <div id="user-preference">
    <h2>User Preference</h2>
    <form v-if="!submitted">

      <div id="larger-font">
        <label for="switch">Large Font</label>
        <input type="checkbox" id="fl-switch" name="lfont" v-on:click="switchLargeFont"/>
      </div>

      <div id="smaller-font">
        <label for="switch">Small Font</label>
        <input type="checkbox" id="fs-switch" name="sfont" v-on:click="switchSmallFont"/>
      </div>

      <div id="normal-font">
        <label for="switch">Default Font</label>
        <input type="checkbox" id="fn-switch" name="nfont" v-on:click="switchDefaultFont"/>
      </div>

      <!-- Theme -->
      <div id="theme">
        <label for="switch">Theme</label>
        <input type="checkbox" id="switch" name="theme" v-on:click="switchTheme"/>
      </div>


    </form>
    <div v-if="submitted">
      <h3>Submit successfully</h3>
    </div>

    <!-- This creates a button that navigates user back to the home page -->
    <router-link :to="{ name: 'home'}">Go Back</router-link>

  </div>

</template>

<script>
export default {
  name: 'PreferencePage',
  data: function() {
    return {
      submitted: false
    }
  },
  methods: {
    //font switch
    switchLargeFont(){
      let checkbox = document.querySelector('input[name=lfont]');

      checkbox.addEventListener('change', function() {
        if(this.checked) {
          trans()
          document.documentElement.setAttribute('data-theme', 'large');

        } else {
          trans()
          document.documentElement.setAttribute('data-theme', 'normal');
        }
      })

      let trans = () => {
        document.documentElement.classList.add('transition');
        window.setTimeout(() => {
          document.documentElement.classList.remove('transition')
        }, 1000)
      }
    },
    switchSmallFont(){
      let checkbox = document.querySelector('input[name=sfont]');

      checkbox.addEventListener('change', function() {
        if(this.checked) {
          trans()
          document.documentElement.setAttribute('data-theme', 'small');

        } else {
          trans()
          document.documentElement.setAttribute('data-theme', 'normal');
        }
      })

      let trans = () => {
        document.documentElement.classList.add('transition');
        window.setTimeout(() => {
          document.documentElement.classList.remove('transition')
        }, 1000)
      }
    },
    switchDefaultFont(){
      let checkbox = document.querySelector('input[name=nfont]');

      checkbox.addEventListener('change', function() {
        if(this.checked) {
          trans()
          document.documentElement.setAttribute('data-theme', 'normal');

        }
      })

      let trans = () => {
        document.documentElement.classList.add('transition');
        window.setTimeout(() => {
          document.documentElement.classList.remove('transition')
        }, 1000)
      }
    },
    //theme switch
    switchTheme(){
      let checkbox = document.querySelector('input[name=theme]');

      checkbox.addEventListener('change', function() {
        if(this.checked) {
          trans()
          document.documentElement.setAttribute('data-theme', 'dark');
        } else {
          trans()
          document.documentElement.setAttribute('data-theme', 'light');
        }
      })

      let trans = () => {
        document.documentElement.classList.add('transition');
        window.setTimeout(() => {
          document.documentElement.classList.remove('transition')
        }, 1000)
      }
    }

  },
  computed: {
    // values used in the template
    // functions here are reactive - whenever a value is changed,
    // the template will catch it automatically and update
    // methods here must return something
  }
}
</script>

<style scoped lang='scss'>
@import "~@/assets/scss/_typo.scss";
@import url("//unpkg.com/element-ui@2.0.5/lib/theme-chalk/index.css");
// your style goes in here
// alternative you can create your own scss file and import it like above
#user-preference *{
  box-sizing: border-box;
}
#user-preference{
  margin: 20px auto;
  max-width: 500px;
}
h3{
  margin-top: 10px;
}
label{
  display: block;
  margin: 20px 0 10px;
}
#category input{
  display: inline-block;
  margin-right: 10px;
}
#category label{
  display: inline-block;
}
#theme input{
  height: 0;
  width: 0;
  visibility: hidden;
}

#theme label{
  cursor: pointer;
  text-indent: -9999px;
  width: 52px;
  height: 27px;
  background: grey;
  float: right;
  border-radius: 100px;
  position: relative;
}

#theme label:after {
  content: '';
  position: absolute;
  top: 3px;
  left: 3px;
  width: 20px;
  height: 20px;
  background: #fff;
  border-radius: 90px;
  transition: 0.3s;
}

#theme input:checked + label {
  background: dodgerblue;
}

#theme input:checked + label:after {
  left: calc(100% - 5px);
  transform: translateX(-100%);
}

#theme label:active:after {
  width: 45px;
}
html.transition,
html.transition *,
html.transition *:before,
html.transition *:after {
  transition: all 750ms !important;
  transition-delay: 0ms !important;
}
</style>