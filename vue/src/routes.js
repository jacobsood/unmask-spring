import Vue from 'vue'
import VueRouter from 'vue-router'
import LandingPage from './components/LandingPage.vue'
import TagPage from './components/TagPage.vue'
import IndividualArticle from './components/IndividualArticle.vue'

Vue.use(VueRouter)

export const router = new VueRouter({
    mode: 'history',
    routes: [
        { name: 'home', path: '/', component: LandingPage },
        { name: 'tags', path: '/tags', component: TagPage },
        { name: 'tag', path: '/tags/:tag', component: TagPage },
        { name: 'country', path: '/tags/:country', component: TagPage },
        { name: 'article', path: '/tags/:tag/:article', component: IndividualArticle },
        { path: '*', redirect: { name: 'home' } },
    ]
});