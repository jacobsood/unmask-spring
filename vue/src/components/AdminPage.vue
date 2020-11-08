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
      <button v-on:click="goBack()">Go Back</button>
      <button v-on:click="createArticle = true">Create Article</button>
    </div>

    <div v-else>
      <button v-on:click="listOut">Article</button>
    </div>

    <div class="createArticle" v-if="createArticle == true">
      <form @submit.prevent="Confirm" action="/articles">
        <label for="input-fields">Article ID</label>
        <textarea
          v-bind:class="{
            'input-fields': !idError,
            'error-input-fields': idError,
          }"
          rows="10"
          v-model="id"
        />
        <label for="input-fields">Title</label>
        <textarea
          v-bind:class="{
            'input-fields': !titleError,
            'error-input-fields': titleError,
          }"
          rows="10"
          v-model="title"
        />
        <label for="input-fields">Source</label>
        <textarea
          v-bind:class="{
            'input-fields': !sourceError,
            'error-input-fields': sourceError,
          }"
          rows="10"
          v-model="source"
        />
        <label for="input-fields">Country</label>
        <textarea
          v-bind:class="{
            'input-fields': !countryError,
            'error-input-fields': countryError,
          }"
          rows="10"
          v-model="country"
        />
        <label for="new-text">Text</label>
        <textarea
          v-bind:class="{
            'input-fields': !textError,
            'error-input-text': textError,
          }"
          rows="10"
          v-model="content"
        />
        <label for="input-fields">Is this a Story?</label>
        <select class="selection_box" v-model="isStory">
          <option disabled value="">Please select one</option>
          <option>Yes</option>
          <option>No</option>
        </select>

        <button v-on:click="createArticle = false">Cancel</button>
        <input
          type="button"
          name="button"
          value="Submit"
          onclick="window.location.replace('/articles')"
        />
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
      articleID: "",
      title: "",
      source: "",
      content: "",
      country: "",
      isStory: "",

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

  methods: {
    goBack() {
      this.showArticle = false;
      this.createArticle = false;
    },

    listOut() {
      this.showArticle = true;
      this.$axios
        .get("https://unmask.hrithviksood.me/articles/")
        .then(function (response) {
          console.log(response.data);
        });
    },

    async submit() {
      //here should be the post() to API
      this.createArticle = false;
      if (this.new_articles != "") {
        this.$axios
          .post("https://unmask.hrithviksood.me/articles/", {
            id: "11",
            article_id: "20",
            title: "New article",
            source: "this.source",
            text: "Who will be the next USA president",
            country: "AU",
            isStory: this.isStory,
          })
          .then(function (response) {
            console.log(response);
          });
      }
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