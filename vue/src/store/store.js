import Vue from 'vue'
import Vuex from 'vuex'
import _ from 'lodash'
import axios from '../assets/javascript/articleAPI'
Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    articles: null,
    test_articles: [
      {
        id: 213412,
        title: "hello",
        tags: ["Tag1", "Tag2", "Tag3"],
        country: "Australia",
        source: "ABC", 
        text: "Hello everybody, how are we?"
      },
      {
        id: 412,
        title: "bye",
        tags: ["Tag5", "Tag2", "Tag6"],
        country: "Peru",
        source: "Telemundo",
        text: "Goodbye everyone!",
      }
    ],
  },
  
  getters: {
    getArticles: state  => state.test_articles,
    
    getArticlesByTag: state => tag => _.find(state.tags, { tag: clean(tag) }).articles,
    
    // getArticlesByCountry: state => country => _.find(state.countries, { country: clean(country) }).articles,
    
    // getArticleByName: state => article => _.find(state.articles, { title: clean(article) }),
  },
  
  mutations: {    
    setArticles(state, axiosResponse) {
      state.articles = axiosResponse;
    },
  },
  
  actions: {
    async setData({commit}) {      
      let articleResponse = await axios().get('articles/');
      commit('setArticles', articleResponse.data);
    },
  },
});

function clean(strclean) {
  return strclean.replace('-', ' ');
}