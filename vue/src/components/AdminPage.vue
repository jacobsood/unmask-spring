<template>
  <div>
    <header>
      <nav>
        <h1>Unmask Administration</h1>
      </nav>
    </header>
    <div calss="listArticle" v-if="showArticle == true">
      <div class="card" v-for="article in articles" :key="article.id">
        <div class="container">
          <div>
            <h5 class="card-title">
              <b>{{ article.id }} - {{ article.title }}</b>
            </h5>
            <p class="card-text">{{ article.text }}</p>
            <p calss="card-text">{{ article.tags }}</p>
          </div>
        </div>
      </div>
      <button v-on:click="showArticle = false">Go Back</button>
      <button v-on:click="listByTag">Edit</button>
    </div>

    <div v-else>
      <button v-on:click="showArticle = true">Article</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "AdminPage",
  components: {},
  data: function () {
    return {
      showArticle: false,
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