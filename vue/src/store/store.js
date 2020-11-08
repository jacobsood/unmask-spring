import Vue from 'vue'
import Vuex from 'vuex'
// import _ from 'lodash'
import axios from '../assets/javascript/articleAPI'
Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    articles: null,
    test_articles: [
      {
        id: 213412,
        title: "Money Laundering in South America",
        tags: ["Tag1", "Tag2", "Tag3"],
        country: "Australia",
        source: "ABC", 
        text: "Hello everybody, how are we?",
        createdByAdmin: true
      },
      {
        id: 412,
        title: "bye",
        tags: ["Tag5", "Tag2", "Tag6"],
        country: "Peru",
        source: "Telemundo",
        text: "Goodbye everyone!",
        createdByAdmin: false
      }
    ],
  },
  
  getters: {
    getArticles: state  => state.test_articles,

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