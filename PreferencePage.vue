<template>
  <div id="user-preference">
    <h2>User Preference</h2>
    <form v-if="!submitted">
      <!--Font-->
      <label>Font:</label>
      <select v-model="preference.font">
        <option v-for="font in fonts" :key="font">{{ font }}</option>
      </select>

      <label>Font size:</label>
      <select v-model="preference.fontsize">
        <option v-for="fontsize in fontsizes" :key="fontsize">{{ fontsize }}</option>
      </select>

      <label>Font Color:</label>
      <select v-model="preference.fontcolor">
        <option v-for="fontcolor in fontcolors" :key="fontcolor">{{ fontcolor }}</option>
      </select>

      <label>Language:</label>
      <select v-model="preference.language">
        <option v-for="language in languages" :key="language">{{ language }}</option>
      </select>

      <!-- Theme -->
      <div id="theme">
        <p>Theme Toggle: </p>
        <input type="checkbox" id="switch" name="theme" /><label for="switch">Toggle</label>
      </div>

      <!--interested categories-->
      <div id="category">
        <p>Interested Categories:</p>
        <label>Category1</label>
        <input type="checkbox" value="category1" v-model="preference.categories"/>
        <label>Category2</label>
        <input type="checkbox" value="category2" v-model="preference.categories"/>
        <label>Category3</label>
        <input type="checkbox" value="category3" v-model="preference.categories"/>
        <label>Category4</label>
        <input type="checkbox" value="category4" v-model="preference.categories"/>
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
    // var checkbox = document.querySelector('input[name=theme]');
    // checkbox.addEventListener('change', function() {
    //   if(this.checked) {
    //     trans()
    //     document.documentElement.setAttribute('data-theme', 'dark')
    //   } else {
    //     trans()
    //     document.documentElement.setAttribute('data-theme', 'light')
    //   }
    // })
    //
    // let trans = () => {
    //   document.documentElement.classList.add('transition');
    //   window.setTimeout(() => {
    //     document.documentElement.classList.remove('transition')
    //   }, 1000)
    // }

    return {
      preference: {
        categories: [],
        font: '',
        fontsize: '',
        fontcolor: '',
        language: ''
      },
      fonts: ['font1', 'font2', 'font3'],
      fontcolors: ['color1', 'color2', 'color3'],
      fontsizes: ['size1', 'size2', 'size3'],
      languages: ['lan1', 'lan2', 'lan3'],
      submitted: false
    }
  },
  methods: {
    // you methods go here
    // which may be needed in the template


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

//#theme input{
//  height: 0;
//  width: 0;
//  visibility: hidden;
//}
//
//#theme label{
//  cursor: pointer;
//  text-indent: -9999px;
//  width: 52px;
//  height: 27px;
//  background: grey;
//  float: right;
//  border-radius: 100px;
//  position: relative;
//}
//
//#theme label:after {
//  content: '';
//  position: absolute;
//  top: 3px;
//  left: 3px;
//  width: 20px;
//  height: 20px;
//  background: #fff;
//  border-radius: 90px;
//  transition: 0.3s;
//}
//
//#theme input:checked + label {
//  background: dodgerblue;
//}
//
//#theme input:checked + label:after {
//  left: calc(100% - 5px);
//  transform: translateX(-100%);
//}
//
//#theme label:active:after {
//  width: 45px;
//}

html.transition,
html.transition *,
html.transition *:before,
html.transition *:after {
  transition: all 750ms !important;
  transition-delay: 0ms !important;
}

</style>