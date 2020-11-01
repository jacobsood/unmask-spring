<template>
  <div id="app">
    <div class="mid-column">
      <div v-bind:key="article.id" v-for="article in articles">
        <Article v-bind:article="article" />
      </div>
    </div>
  </div>
</template>

<script>
import Article from "./components/Article";
export default {
  name: "App",
  components: {
    Article,
  },
  data() {
    return {
      articles: [
        {
          id: 1,
          title: "article 1",
          source: "Auther1 name",
          text: "ej mklfemfew mfkle mfklewm kem lkmw l",
          country: "Australia",
          tag: "covid19",
        },
        {
          id: 2,
          title: "article Two",
          source: "Auther2 name",
          text:
            "fjeif dhiwdjwidhqw ihwjdwqfwqihdhwuid quihuiwqd uqwhduiwf uihwdhweui fewuiheuf ewiu",
          country: "USA",
          tag: "politics",
        },
        {
          id: 3,
          title: "article Three",
          source: "Auther3 name",
          text:
            "fniewjfnewi jewufhewif jiewfjewfj uiewjfqowidjqwioqfjwi jiwedjfqwiod jiqwdjqwiof fjeif dhiwdjwidhqw ihwjdwqfwqihdhwuid quihuiwqd uqwhduiwf uihwdhweui fewuiheuf ewiu",
          country: "USA",
          tag: "covid19",
        },
      ],
    };
  },
};
</script>

<style>
#app {
  color: #2c3e50;
}
.mid-column {
  margin-top: 1.5rem;
  margin-left: 25%;
  margin-right: 25%;
}
</style>
