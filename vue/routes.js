import Vue from 'vue'
import VueRouter from 'vue-router'
import LandingPage from './components/LandingPage.vue'
import HomePage from './components/HomePage.vue'
import IndividualArticle from './components/IndividualArticle.vue'
import FavouritePage from './components/FavouritePage.vue'
import PreferencePage from './components/PreferencePage.vue'
import LoginPage from './components/LoginPage.vue'

Vue.use(VueRouter)

export const router = new VueRouter({
    mode: "history",
    routes: [
      { name: "landing", path: "/", component: LandingPage },
      { name: "home", path: "/articles", component: HomePage },
      { name: "tag", path: "/articles/tag/:tag", component: HomePage },
      { name: "country", path: "/articles/country/:country", component: HomePage },
      { name: "author", path: "/articles/author/:author", component: HomePage },
      { name: "search", path: "/articles/:search", component: HomePage },
      { name: "adminAddedArticles", path: "/articles/:addedBtAdmin", component: HomePage},
      { name: "history", path: "/user/:id/history", component: HistoryPage },
      { name: "newstory", path: "/newstory", component: NewStory },
      {
        name: "preference",
        path: "/user/:id/preference",
        component: PreferencePage,
      },
      { name: "login", path: "/login", component: LoginPage },
      { path: "*", redirect: { name: "landing" } },
    ],
  });