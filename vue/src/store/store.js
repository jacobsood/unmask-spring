import Vue from 'vue'
import Vuex from 'vuex'
import _ from 'lodash'
import axios from '../assets/javascript/articleAPI.js'
Vue.use(Vuex);

let audioIndex = 0;

export const store = new Vuex.Store({
  state: {
    tags: [],
    countries: [],
    articles: [],
    collection: [],
    recordings: [],
  },
  
  getters: {
    getCollection: state  => state.collection.sort(),
    
    getArticlesByTag: state => tag => _.find(state.tags, { tag: clean(tag) }).articles,
    
    getArticlesByCountry: state => country => _.find(state.countries, { country: clean(country) }).articles,
    
    getArticleByName: state => article => _.find(state.articles, { title: clean(article) }),
    
    isTag: state => tag => {
      if (_.find(state.tags, { tag })) {
        return true;
      }
    },
    
    isCountry: state => country => {
      if (_.find(state.countries, { country })) {
        return true;
      }
    },
    
    isArticle: state => article => {
      if (_.find(state.articles, { title: article })) {
        return true;
      }
    },
  },
  
  mutations: {
    setTags(state, axiosResponse) {
      state.tags = axiosResponse;
    },
    
    setCountries(state, axiosResponse) {
      state.countries = axiosResponse;
    },
    
    setArticles(state, axiosResponse) {
      state.articles = axiosResponse;
    },
    
    setCollection(state) {
      state.collection = _.map(_.concat(state.tags, state.countries), item => item.tag || item.country)
    },
  },
  
  actions: {
    async setData({commit}) {
      let tagResponse = await axios().get('/tags/');
      commit('setTags', tagResponse.data);
      
      let countryResponse = await axios().get('/countries/');
      commit('setCountries', countryResponse.data);
      
      commit('setCollection');
      
      let articleResponse = await axios().get('/articles/');
      commit('setArticles', articleResponse.data)
    },
  },
});

function clean(strclean) {
  return strclean.replace('-', ' ');
}