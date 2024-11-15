import {createRouter, createWebHistory, createWebHashHistory} from 'vue-router'

// 测试页面路由
const testRoutes = [
  {
    path: 'foo',
    name: 'foo',
    meta: {
      title: "foo"
    },
    component: () => import('@/views/zTest/foo.vue'),
  },
  {
    path: 'bar',
    name: 'bar',
    meta: {
      title: "bar"
    },
    component: () => import('@/views/zTest/bar.vue'),
  },
  {
    path: '/about',
    name: 'about',
    meta: {
      title: "about"
    },
    component: () => import('@/views/zTest/about.vue')
  },
  {
    path: '/fei_h', // 滚动条
    name: 'fei_h',
    meta: {
      title: "滚动条"
    },
    component: () => import('@/views/zTest/fHeight.vue')
  },
  {
    path: '/fei_chart', // 聊天界面
    name: 'fei_chart',
    meta: {
      title: "聊天"
    },
    component: () => import('@/views/zTest/fChart.vue')
  },
];

// 文件处理路由
const fileRoutes = [
  {
    path: '/fei_utils_file',
    name: 'fei_utils_file',
    meta: {
      title: "文件处理"
    },
    component: () => import('@/views/fUtils/fFileIndex.vue'),
  },
]

// element-plus 路由 *********** element-plus 中常见的UI
const eleRoutes = [
  {
    path: '/fei_ele',
    name: 'fei_ele',
    meta: {
      title: "Element"
    },
    component: () => import('@/views/fElementUi/elementUi.vue')
  },
]

// 其他小功能 路由 *********** 一些常用的小功能库
const utilsRoutes = [
  {
    path: '/fei_utils_vCharts',
    name: 'fei_utils_vCharts',
    meta: {
      // 图表
      title: "图表"
    },
    component: () => import('@/views/fUtils/vChart.vue')
  },
  {
    path: '/fei_utils_jsonp',
    name: 'fei_utils_jsonp',
    meta: {
      // jsonp,跨域请求
      title: "utils_jsonp"
    },
    component: () => import('@/views/fUtils/fJsonp.vue')
  },
  {
    path: '/fei_utils_swiper',
    name: 'fei_utils_swiper',
    meta: {
      // swiper 轮播
      title: "轮播"
    },
    component: () => import('@/views/fUtils/fSwiper.vue')
  },
  {
    path: '/fei_utils_calendar',
    name: 'fei_utils_calendar',
    meta: {
      // FullCalendar 日历
      title: "日历"
    },
    component: () => import('@/views/fUtils/fCalendar.vue')
  },
  {
    path: '/fei_utils_calendar2',
    name: 'fei_utils_calendar2',
    meta: {
      // FullCalendar 日历
      title: "日历2"
    },
    component: () => import('@/views/fUtils/fCalendar2.vue')
  },
  {
    path: '/fei_utils_screen',
    name: 'fei_utils_screen',
    meta: {
      title: "全屏"
    },
    component: () => import('@/views/fUtils/fScreen.vue')
  },
]

// 正式路由
const routes = [
  {
    path: '/login',
    name: 'login',
    meta: {
      title: "登录"
    },
    component: () => import('@/views/login'),
  },
  {
    path: '/404',
    name: '404',
    component: () => import('@/views/Error/404.vue')
  },
  // 当什么都没有匹配到的时候，重定向页面到 404 页面
  {
    path: '/:pathMatch(.*)',
    redirect: '/404',
    name: 'notMatch',
  },
  {
    path: '',
    component: () => import('@/layout'),
    redirect: 'home',
    children: [
        ...testRoutes,
        ...fileRoutes,
        ...eleRoutes,
        ...utilsRoutes,
      {
        path: 'home',
        name: 'home',
        meta: {
          title: "首页"
        },
        component: () => import('@/views/home.vue'),
      },
      {
        path: 'goods-index',
        name: 'goods-index',
        meta: {
          title: "商品"
        },
        component: () => import('@/views/fGoods/index.vue'),
      },
      {
        path: 'bill-list',
        name: 'bill-list',
        meta: {
          title: "账单"
        },
        component: () => import('@/views/fGoods/billList.vue')
      },
      {
        path: '/dict-list',
        name: 'dict-list',
        meta: {
          title: "字典"
        },
        component: () => import('@/views/fSystem/dictIndex')
      },
      {
        path: '/dict-data',
        name: 'dict-data',
        meta: {
          title: "字典详细"
        },
        component: () => import( '@/views/fSystem/dictData')
      },
      {
        path: '/subscribe-index',
        name: 'subscribe-index',
        meta: {
          title: "预约登录"
        },
        component: () => import( '@/views/fSubscribe/index')
      },
      {
        path: '/subscribe-appoint',
        name: 'subscribe-appoint',
        meta: {
          title: "预约场馆"
        },
        component: () => import( '@/views/fSubscribe/appoint')
      },
      {
        path: '/subscribe-selectTime',
        name: 'subscribe-selectTime',
        meta: {
          title: "预约时间"
        },
        component: () => import( '@/views/fSubscribe/selectTime')
      },
    ]
  },
]

const router = createRouter({
  history: createWebHistory(),  // history 模式
  // history: createWebHashHistory(), // hash 模式
  routes
})

export default router
