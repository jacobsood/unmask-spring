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
      <button v-on:click="showArticle = true">Article</button>
    </div>

    <div class="createArticle" v-if="createArticle == true">
      <form @submit.prevent="Confirm">
        <lable for="input-fields">Article ID</lable>
        <textarea
          v-bind:class="{
            'input-fields': !idError,
            'error-input-fields': idError,
          }"
          rows="10"
          v-model="id"
        />
        <lable for="input-fields">Title</lable>
        <textarea
          v-bind:class="{
            'input-fields': !titleError,
            'error-input-title': titleError,
          }"
          rows="10"
          v-model="title"
        />
        <lable for="input-fields">Source</lable>
        <textarea
          v-bind:class="{
            'input-fields': !sourceError,
            'error-input-source': sourceError,
          }"
          rows="10"
          v-model="source"
        />
        <lable for="input-fields">Country</lable>
        <textarea
          v-bind:class="{
            'input-fields': !countryError,
            'error-input-country': CountryError,
          }"
          rows="10"
          v-model="country"
        />
        <lable for="new-text">Text</lable>
        <textarea
          v-bind:class="{
            'input-fields': !textError,
            'error-input-text': textError,
          }"
          rows="10"
          v-model="content"
        />
        <lable for="input-fields">Is this a Story?</lable>
        <select class="selection_box" v-model="isStory">
          <option disabled value="">Please select one</option>
          <option>Yes</option>
          <option>No</option>
        </select>

        <button v-on:click="addNewArticle">Check</button>
        <button v-on:click="submit">Confirm</button>
        <button v-on:click="createArticle = flase">Cancle</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  // import axios from 'axios',

  name: "AdminPage",
  components: {},
  data: function () {
    return {
      //button control
      showArticle: false,
      createArticle: false,
      back: false,
      //input fields
      new_articles: {
        id: "",
        title: "",
        source: "",
        text: "",
        country: "",
        isStory: "",
      },

      //Error
      titleError: false,
      sourceError: false,
      textError: false,
      storyError: false,
      idError: false,
      countryError: false,
      errorExists: true,
    };
  },

  computed: {
    articles() {
      return this.$store.state.articles;
    },
  },

  methods: {
    listOut() {
      this.showArticle = true;
    },

    submit() {
      //here should be the post() to API
      this.createArticle = false;

      // alert("put to database");
      // if (this.new_articles != "") {
      //   $axios.post("https://unmask.hrithviksood.me/articles/"),
      //     {
      //       id: this.id,
      //       title: this.title,
      //       source: this.source,
      //       text: this.text,
      //       country: this.country,
      //       isStory: this.isStory,
      //     };
      // }
    },

    addNewArticle() {
      //check creation
      if (this.title.length < 3) {
        this.titleError = true;
      } else {
        this.titleError = false;
        this.errorExists = true;
      }

      if (this.source.length < 3) {
        this.sourceError = true;
      } else {
        this.sourceError = false;
        this.errorExists = true;
      }

      if (this.text.length < 10) {
        this.textError = true;
      } else {
        this.errorExists = true;
        this.textError = false;
      }

      if (this.country.length < 5) {
        this.countryError = true;
      } else {
        this.titleError = false;
        this.errorExists = true;
      }

      if (this.errorExists) {
        alert("There may be some errors in the submit form");
      } else {
        alert("clear to submit");
      }
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
</style>