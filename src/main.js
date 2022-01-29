import { createApp } from 'vue'

import '@/assets/style/index.scss'
// import Antd from 'ant-design-vue';
// import 'ant-design-vue/dist/antd.css'
import 'element-plus/dist/index.css'
import ElementPlus from 'element-plus'
import zhCn  from 'element-plus/lib/locale/lang/zh-cn'

import App from './App.vue'
import router from './router'
import store from './store'


// createApp(App).use(store).use(router).use(ElementPlus).mount('#app')

const app = createApp(App);

app.use(ElementPlus,{ size: 'small', zIndex: 3000, locale: zhCn});
app.use(store);
app.use(router);
app.mount('#app');
