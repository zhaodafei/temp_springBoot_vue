import { createApp } from 'vue'
import dayjs from "dayjs";

import '@/assets/style/index.scss'
import {iconInstall, zhCn, ElementPlus} from "./utils/element.js"
import {axiosInstall} from "./utils/axios_instance.js"

import App from './App.vue'
import router from './router'
import store from './store'

// 导入图表库
import ECharts from 'vue-echarts'
import { use } from 'echarts/core'
// 全局导入图表库模块 减少打包体积
// CanvasRenderer 和 GridComponent是每个模块必须的
import { CanvasRenderer } from 'echarts/renderers'
import { GridComponent } from 'echarts/components'
use([CanvasRenderer, GridComponent])


const app = createApp(App);

app.config.globalProperties.$dayjs = dayjs

// 全局挂载图表组件
app.component('VChart', ECharts)


app.use(ElementPlus,{ size: 'default', zIndex: 3000, locale: zhCn});
app.use(iconInstall);

app.use(axiosInstall);

app.use(store);
app.use(router);
app.mount('#app');
