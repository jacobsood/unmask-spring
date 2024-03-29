import Vue from "vue";
import VueRouter from "vue-router";
import LandingPage from "./components/LandingPage.vue";
import HomePage from "./components/HomePage.vue";
import PreferencePage from "./components/PreferencePage.vue";
import LoginPage from "./components/LoginPage.vue";
import FavouritePage from './components/FavouritePage.vue'
import NewStory from "./components/NewStory.vue";
import Search from "./components/Search.vue";
import Admin from "./components/AdminPage.vue";
Vue.use(VueRouter);

export const router = new VueRouter({
  mode: "history",
  routes: [
    { name: "landing", path: "/", component: LandingPage },
    { name: "home", path: "/articles", component: HomePage },
    { name: "tag", path: "/articles/tag/:tag", component: HomePage },
    { name: "country", path: "/articles/country/:country", component: HomePage },
    { name: "author", path: "/articles/author/:author", component: HomePage },
    { name: "search", path: "/articles/:search", component: Search },
    { name: "isStory", path: "/articles/:isStory", component: HomePage},
    { name: 'favourite', path: '/user/favourite', component: FavouritePage },
    { name: "newstory", path: "/newstory", component: NewStory },
    { name: "admin", path: "/admin", component: Admin },
    {
      name: "preference",
      path: "/user/preference",
      component: PreferencePage,
    },
    { name: "login", path: "/login", component: LoginPage },
    { path: "*", redirect: { name: "landing" } },
  ],
});
