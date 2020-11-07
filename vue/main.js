import Vue from 'vue'
import App from './App.vue'
import { router } from './routes.js'
import { store } from './store/store.js'
import vuetify from './plugins/vuetify';
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'

import VueCookies from 'vue-cookies'

Vue.use(VueCookies)
Vue.use(ElementUI)
Vue.prototype.$axios = axios
Vue.config.productionTip = false;

new Vue({
    router,
    store,
    vuetify,
    render: h => h(App)
}).$mount('#app')
