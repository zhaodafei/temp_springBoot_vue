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
  {
    path: '/fei_ele', // element-plus 中常见的UI
    name: 'fei_ele',
    meta: {
      title: "Element"
    },
    component: () => import('@/views/zTest/elementUi.vue')
  },
];

// 文件处理路由
const fileRoutes = [
  {
    path: '/file',
    name: 'file',
    meta: {
      title: "文件处理"
    },
    component: () => import('@/views/zTest/fileIndex.vue'),
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
    path: '',
    component: () => import('@/layout'),
    redirect: 'home',
    children: [
        ...testRoutes,
        ...fileRoutes,
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
        path: 'dict-list',
        name: 'dict-list',
        meta: {
          title: "字典"
        },
        component: () => import('@/views/fSystem/dictIndex')
      },
      {
        path: 'dict-data',
        name: 'dict-data',
        meta: {
          title: "字典详细"
        },
        component: () => import( '@/views/fSystem/dictData')
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
