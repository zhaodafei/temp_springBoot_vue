import {createRouter, createWebHistory, createWebHashHistory} from 'vue-router'

// 测试页面路由
const testRoutes = [
  {
    path: 'foo',
    name: 'foo',
    component: () => import('@/views/zTest/foo.vue'),
  },
  {
    path: 'bar',
    name: 'bar',
    component: () => import('@/views/zTest/bar.vue'),
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('@/views/zTest/About.vue')
  },
];

// 文件处理路由
const fileRoutes = [
  {
    path: '/file',
    name: 'file',
    component: () => import('@/views/zTest/fileIndex.vue'),
  },
]

// 正式路由
const routes = [
  {
    path: '/login',
    name: 'login',
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
        component: () => import('@/views/Home.vue'),
      },
      {
        path: 'goods-index',
        name: 'goods-index',
        component: () => import('@/views/fGoods/index.vue'),
      },
      {
        path: 'bill-list',
        name: 'bill-list',
        component: () => import('@/views/fGoods/billList.vue')
      },
      {
        path: 'dict-list',
        name: 'dict-list',
        component: () => import('@/views/fSystem/dictIndex')
      },
      {
        path: 'dict-data',
        name: 'dict-data',
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
