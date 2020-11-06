import Vue from 'vue'
import Vuex from 'vuex'
import _ from 'lodash'
import axios from '../assets/javascript/articleAPI.js'
Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    articles: [
      {
        id: 1,
        title: "Hello",
        source: "Abc",
        text: "Hello one two three",
        country: "Australia",
        comments: [
          {
            id: 1,
            
          },
          {
            id: 2,

          }
        ],
        tags: [
          {
            id: 1,
            tag: "Money Laundering",
          },
          {
            id: 2,
            tag: "Illegal Gold Mining",
          }
        ]
      },
      {
        id: 2,
        title: "Hi there",
        source: "Abc",
        text: "Hello one two three",
        country: "Australia",
        comments: [
          {
            id: 1,
            
          },
          {
            id: 2,

          }
        ],
        tags: [
          {
            id: 1,
            tag: "Money Laundering",
          },
          {
            id: 2,
            tag: "Illegal Gold Mining",
          }
        ],
      },
      {
        id: 3,
        title: "Wow",
        source: "Abc",
        text: "Hello one two three",
        country: "Australia",
        comments: [
          {
            id: 1,
            
          },
          {
            id: 2,

          }
        ],
        tags: [
          {
            id: 1,
            tag: "Money Laundering",
          },
          {
            id: 2,
            tag: "Illegal Gold Mining",
          }
        ],
      }
    ],
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