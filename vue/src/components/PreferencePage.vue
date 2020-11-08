<template>
  <div id="user-preference">
    <h2>User Preference</h2>
    <form v-if="!submitted">

      <div id="larger-font">
        <label for="switch">Large Font</label>
        <input type="checkbox" id="fl-switch" :disabled="box1" name="lfont" @click="switchLargeFont($event)"/>
      </div>

      <div id="smaller-font">
        <label for="switch">Small Font</label>
        <input type="checkbox" id="fs-switch" :disabled="box2" name="sfont" @click="switchSmallFont($event)"/>
      </div>

      <div id="normal-font">
        <label for="switch">Default Font</label>
        <input type="checkbox" id="fn-switch" :disabled="box3" name="nfont"  @click="switchDefaultFont($event)"/>
      </div>

      <!-- Theme -->
      <!-- <div id="theme">
        <label for="switch">Theme</label>
        <input type="checkbox" id="switch" name="theme" v-on:click="switchTheme"/>
      </div> -->



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
      submitted: false,
      box1:false,
      box2:false,
      box3:false,
    }
  },
  created:function(){
    let theme = window.document.documentElement.getAttribute('data-theme');
    console.log(theme)
    if (theme=="large"){
        this.box2 = true;
        this.box3 = true;
    }else if (theme=="small"){
        this.box1 = true;
        this.box3 = true;
    }else{
        this.box1 = true;
        this.box2 = true;
    }
  },
  methods: {
    //font switch
    switchLargeFont(event){
      let status = event.target.checked;
      let trans = () => {
        window.document.documentElement.classList.add('transition');
        window.setTimeout(() => {
          window.document.documentElement.classList.remove('transition')
        }, 1000)
      }

      if (status==true){
        trans()
        window.document.documentElement.setAttribute('data-theme', 'large');
        this.box2 = true;
        this.box3 = true;
      }else{
        trans()
        window.document.documentElement.setAttribute('data-theme', 'normal');
        this.box2 = false;
        this.box3 = false;
      }
      
    },



    switchSmallFont(event){
      let status = event.target.checked;
      let trans = () => {
        window.document.documentElement.classList.add('transition');
        window.setTimeout(() => {
          window.document.documentElement.classList.remove('transition')
        }, 1000)
      }

      if (status==true){
        trans()
        window.document.documentElement.setAttribute('data-theme', 'small');
        this.box1 = true;
        this.box3 = true;
      }else{
        trans()
        window.document.documentElement.setAttribute('data-theme', 'normal');
        this.box1 = false;
        this.box3 = false;
      }
    },
    switchDefaultFont(event){
      let status = event.target.checked;
      let trans = () => {
        window.document.documentElement.classList.add('transition');
        window.setTimeout(() => {
          window.document.documentElement.classList.remove('transition')
        }, 1000)
      }

      if (status==true){
        trans()
        window.document.documentElement.setAttribute('data-theme', 'normal');
        this.box1 = true;
        this.box2 = true;
      }else{
        trans()
        window.document.documentElement.setAttribute('data-theme', 'normal');
        this.box1 = false;
        this.box2 = false;
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
@import "~@/assets/scss/_themes.scss";
//@import url("//unpkg.com/element-ui@2.0.5/lib/theme-chalk/index.css");
// your style goes in here
// alternative you can create your own scss file and import it like above
#user-preference{

  box-sizing: border-box;
}
#user-preference{
  margin: 20px auto;
  max-width: 500px;
}
h2{

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