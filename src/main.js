import { createApp } from 'vue'
import dayjs from "dayjs";

import '@/assets/style/index.scss'
import {iconInstall, zhCn, ElementPlus} from "./utils/element.js"
import {axiosInstall} from "./utils/axios_instance.js"

import App from './App.vue'
import router from './router'
import store from './store'

const app = createApp(App);

app.config.globalProperties.$dayjs = dayjs

app.use(ElementPlus,{ size: 'default', zIndex: 3000, locale: zhCn});
app.use(iconInstall);

app.use(axiosInstall);

app.use(store);
app.use(router);
app.mount('#app');
