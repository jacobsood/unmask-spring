<template>
  <div id="list" class="new-story">
    <header>
      <router-link :to="{ name: 'landing'}">
        <h4>Unmask - <span>Stories Untold</span></h4>  
      </router-link>
    </header>
    
    <form class="article-form" @submit.prevent="publish">
        <h1 id="page-title">New Story</h1>

        <label for="input-fields">Title</label>
        <textarea v-bind:class="{ 'input-fields': !titleHasError, 'error-input-fields': titleHasError }" rows="10" v-model="titleData" />
        <label for="input-fields">Auther</label>
        <textarea v-bind:class="{ 'input-fields': !autherHasError, 'error-input-fields': autherHasError }" rows="10" v-model="autherData" />
        <label for="input-fields">Country</label>
        <textarea v-bind:class="{ 'input-fields': !countryHasError, 'error-input-fields': countryHasError }" rows="10" v-model="countryData" />

        <label for="new-text">Text</label>
        <textarea v-bind:class="{ 'new-text': !textHasError, 'error-new-text': textHasError }" rows="10" v-model="textData" />

        <textarea v-bind:class="{ 'new-tag': isTagsOk(), 'error-new-tag': !isTagsOk() }" rows="10" v-for="(tag, index) in tagData" v-model="tag.tagText" :key="index" />
        <!-- Plus button -->
        <svg @click="newTagField" width="2em" height="2em" viewBox="0 0 16 16" class="bi bi-plus-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
            <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
        </svg>
        <button @click="goToPreviousPage" id="publish-button">Publish</button>
    </form>
  </div>
</template>

<script>
import { router } from '../routes'
export default {
  name: 'NewStory',
  data: function() {
    return {
        //variables connected to input fields
      titleData: "",
      textData: "",
      //Array of tags
      tagData: [
          { tagText: "" },
      ],
      autherData: "",
      countryData: "",
      titleHasError: false,
      autherHasError: false,
      countryHasError: false,
      textHasError: false,
      tagHasError: false,
      wasPageSubmitted: false,
      errorsOnPage: false
    }
  },
  methods: {
    publish(){ //method run on button click
      this.wasPageSubmitted = true;//records submit attempt
      this.errorsOnPage = false;
      if(this.titleData.length < 3){
        this.titleHasError = true;
        this.errorsOnPage = true;
      } else {this.titleHasError = false}
      if(this.autherData.length < 3){
        this.autherHasError = true;
        this.errorsOnPage = true;
      } else {this.autherHasError = false}
      if(this.countryData.length < 2){
        this.countryHasError = true;
        this.errorsOnPage = true;
      } else {this.countryHasError = false}
      if(this.textData.length < 5){
        this.textHasError = true;
        this.errorsOnPage = true;
      } else {this.textHasError = false}
      //Array of non-empty tags
      let tagsToSubmit = this.tagData.filter((tag) => tag.tagText.length > 0);
      if(tagsToSubmit.length < 1){
        this.errorsOnPage = true;
      }
      //////// If errorsOnPage is FALSE here then form can be submitted
      if(!this.errorsOnPage){
        console.log("Clear to submit");
      }
    },
    goToPreviousPage: function() {
      router.push({ name: "home" });
    },

    isTagsOk(){
      let tags = this.tagData.filter((tag) => tag.tagText.length > 0);
      if(tags.length < 1 && this.wasPageSubmitted){
        return false;
      } else {return true}
    },

    newTagField(){
        this.tagData.push({ tagText: "" });
    }
  },
  computed: {
    
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

  span {
    color: red;
  }
}

.left-side-bar {
  left: 0;
}

.right-side-bar {
  right: 0;
}

.new-story {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 60vw;
    min-height: 70vh;
    font-family: "Lucida Console", Courier, monospace;
    font-size: 1rem;
}

.article-form {
    width: 40vw;
    align-items: center;
    justify-content: center;
}
.input-fields{
    background-color: rgb(41, 40, 40);
    width: 40vw;
    max-height: 2vw;
    font-size: 1rem;
}
.new-text {
    background-color: rgb(41, 40, 40);
    width: 40vw;
    min-height: 20vw;
    font-size: 1rem;
}
.new-tag {
    background-color: rgb(41, 40, 40);
    max-height: 2vw;
    width: 20vw;
    margin-left: 5vw;
}
.error-new-tag {
    background-color: rgb(70, 0, 0);
    max-height: 2vw;
    width: 20vw;
    margin-left: 5vw;
}

#publish-button {
    background-color: rgb(41, 40, 40);
    margin-left: 35vw;
    width: 5vw;
    height: 2vw;
    font-size: 1rem;
}
#page-title{
    text-align: center;
    font-family: "CustomY78";
}
.error-input-fields{
  background-color: rgb(70, 0, 0);
  width: 40vw;
  max-height: 2vw;
  font-size: 1rem;
}
.error-new-text{
  background-color: rgb(70, 0, 0);
  width: 40vw;
    min-height: 20vw;
    font-size: 1rem;
}
</style>
