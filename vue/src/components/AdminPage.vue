<template>
  <div class="admin-page">
    <header>
      <nav>
        <h1>Unmask Administration</h1>
      </nav>
    </header>
    <div class="listArticle" v-if="showArticle == true">
      <div class="card" v-for="article in articles" :key="article.id">
        <div class="container">
          <div>
            <h5 class="card-title">
              <b>{{ article.id }} - {{ article.title }}</b>
            </h5>
            <p class="card-text">{{ article.text }}</p>
            <p class="card-text">{{ article.tags }}</p>
          </div>
        </div>
      </div>
      <button v-on:click="showArticle = false">Go Back</button>
      <button v-on:click="createArticle = true">Create Article</button>
    </div>

    <div v-else>
      <button v-on:click="listOut">Article</button>
    </div>

    <div class="createArticle" v-if="createArticle == true">
      <form @submit.prevent="Confirm">
        <lable for="input-fields">Article ID</lable>
        <textarea
          v-bind:class="{
            'input-fields': !idrror,
            'error-input-fields': idError,
          }"
          rows="10"
          v-model="idInput"
        />
        <lable for="input-fields">Title</lable>
        <textarea
          v-bind:class="{
            'input-fields': !titleError,
            'error-input-fields': titleError,
          }"
          rows="10"
          v-model="titleInput"
        />
        <lable for="input-fields">Source</lable>
        <textarea
          v-bind:class="{
            'input-fields': !sourceError,
            'error-input-fields': sourceError,
          }"
          rows="10"
          v-model="sourceInput"
        />
        <lable for="input-fields">Country</lable>
        <textarea
          v-bind:class="{
            'input-fields': !countryError,
            'error-input-fields': CountryError,
          }"
          rows="10"
          v-model="countryInput"
        />
        <lable for="new-text">Text</lable>
        <textarea
          v-bind:class="{
            'input-fields': !textError,
            'error-input-fields': textError,
          }"
          rows="10"
          v-model="contentInput"
        />
        <lable for="input-fields">is This a Story?</lable>
        <textarea
          v-bind:class="{
            'input-fields': !storyError,
            'error-input-fields': storyError,
          }"
          rows="10"
          v-model="booleanInput"
        />
        <button v-on:click="createArticle = false">Confirm</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "AdminPage",
  components: {},
  data: function () {
    return {
      articles: null,
      //button control

      showArticle: false,
      createArticle: false,
      //input fields
      id: "",
      title: "",
      source: "",
      content: "",
      country: "",
      isStory: "",

      //Errorm
      titleError: false,
      sourceError: false,
      textError: false,
      storyError: false,
      idError: false,
      countryError: false,
    };
  },

  methods: {
    listOut() {
      this.showArticle = true;
      this.$axios
      .get("https://unmask.hrithviksood.me/articles/")
      .then(function(response){
      console.log(response);
      });
    },

    addNewArticle() {
      //confirm creation
    },
  },
};
</script>


<style scoped lang='scss'>
@import "~@/assets/scss/_typo.scss";

button {
  font-family: "Courier New", sans-serif;
  font-weight: 500;
  font-size: 16px;
  color: #6c7986;
  text-decoration: none;
}

h2,
h5 {
  color: #747272;
}

button {
  padding: 9px 25px;
  background-color: #d7e3e6;
  border: none;
  border-radius: 50px;
  cursor: pointer;
  transition: all 0.3s ease 0s;
}

.card {
  box-shadow: 0 4px 8px 0 rgba(160, 153, 153, 0.2);
  transition: 0.3s;
  padding: 10% 10%;
}

.card:hover {
  box-shadow: 0 8px 16px 0 rgba(153, 34, 34, 0.884);
}

.container {
  padding: 2px 16px;
}

button:hover {
  background-color: #93b7d6;
}
.editbox {
  background: #262829;
}

.input-fields {
  background-color: rgb(41, 40, 40);
  width: 40vw;
  max-height: 2vw;
  font-size: 1rem;
  display: block;
}
.selection_box {
  background-color: #6c7986;
}
</style>