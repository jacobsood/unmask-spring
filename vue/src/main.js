import Vue from 'vue'
import App from './App.vue'
import { router } from './routes.js'
import { store } from './store/store.js'
import vuetify from './plugins/vuetify'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueTypedJs from 'vue-typed-js'
import VueCookies from 'vue-cookies'
import axios from 'axios'

Vue.use(VueTypedJs);
Vue.use(VueCookies);
Vue.use(ElementUI);
Vue.prototype.$axios = axios;
Vue.config.productionTip = false;

new Vue({
    router,
    store,
    vuetify,
    VueTypedJs,
    render: h => h(App)
}).$mount('#app')
