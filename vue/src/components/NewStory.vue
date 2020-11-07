<template>
  <div id="list" class="new-story">
    <header>
      <router-link :to="{ name: 'landing'}">
        <h4>Unmask - <span>Stories Untold</span></h4>  
      </router-link>
    </header>
    
    <form class="article-form">
        <h1 id="page-title">New Story</h1>
        <label for="new-title">Title</label>
        <textarea id="new-title" rows="10" />

        <label for="new-text">Text</label>
        <textarea id="new-text" rows="10" />

        <label for="new-tag">Tag</label>
        <textarea id="new-tag" rows="10" />
        <button id="publish-button">Publish</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'NewStory',
  data: function() {
    return {
      
    }
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
#new-title{
    background-color: rgb(41, 40, 40);
    width: 40vw;
    max-height: 2vw;
    font-size: 1rem;
}
#new-text {
    background-color: rgb(41, 40, 40);
    width: 40vw;
    min-height: 20vw;
    font-size: 1rem;
}
#new-tag {
    background-color: rgb(41, 40, 40);
    max-height: 2vw;
    width: 20vw;
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
</style>
