import Vue from 'vue'
import VueRouter from 'vue-router'
import LandingPage from './components/LandingPage.vue'
import HomePage from './components/HomePage.vue'
import IndividualArticle from './components/IndividualArticle.vue'

Vue.use(VueRouter)

export const router = new VueRouter({
    mode: 'history',
    routes: [
        { name: 'landing', path: '/', component: LandingPage },
        { name: 'home', path: '/home', component: HomePage },
        { name: 'tag', path: '/home/:tag', component: HomePage },
        { name: 'country', path: '/home/:country', component: HomePage },
        { name: 'article', path: '/home/:tag/:article', component: IndividualArticle },
        { path: '*', redirect: { name: 'landing' } },
    ]
});