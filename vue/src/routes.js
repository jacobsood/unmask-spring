import Vue from 'vue'
import VueRouter from 'vue-router'
import LandingPage from './components/LandingPage.vue'
import HomePage from './components/HomePage.vue'
import IndividualArticle from './components/IndividualArticle.vue'
import HistoryPage from './components/HistoryPage.vue'
import PreferencePage from './components/PreferencePage.vue'
import LoginPage from './components/LoginPage.vue'

Vue.use(VueRouter)

export const router = new VueRouter({
    mode: 'history',
    routes: [
        { name: 'landing', path: '/', component: LandingPage },
        { name: 'home', path: '/home', component: HomePage },
        { name: 'tag', path: '/home/:tag', component: HomePage },
        { name: 'country', path: '/home/:country', component: HomePage },
        { name: 'article', path: '/home/:tag/:article', component: IndividualArticle },
        { name: 'history', path: '/user/:id/history', component: HistoryPage },
        { name: 'preference', path: '/user/:id/preference', component: PreferencePage },
        { name: 'login', path: '/login', component: LoginPage },
        { path: '*', redirect: { name: 'landing' } },
    ]
});