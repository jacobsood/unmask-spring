import Vue from 'vue'
import Vuex from 'vuex'
import _ from 'lodash'
import axios from '../assets/javascript/articleAPI.js'
Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    articles: [],
  },
  
  getters: {
    getArticles: state  => state.articles.sort(),
    
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
      let articleResponse = await axios().get('/articles/');
      commit('setArticles', articleResponse.data)
    },
  },
});

function clean(strclean) {
  return strclean.replace('-', ' ');
}