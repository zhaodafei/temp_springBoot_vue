import {createRouter, createWebHistory} from 'vue-router'
import Home from '@/views/Home.vue'
import foo from '@/views/zTest/foo.vue'
import bar from '@/views/zTest/bar.vue'
import goodsIndex from '@/views/fGoods/index.vue'
import billList from '@/views/fGoods/billList.vue'
import login from '@/views/login'
import dict from '@/views/fSystem/dictIndex'

import Layout from '@/layout'

// 测试页面路由
const testRoutes = [
  {
    path: 'foo',
    name: 'foo',
    component: foo
  },
  {
    path: 'bar',
    name: 'bar',
    component: bar
  },
  {
    path: '/about',
    name: 'About',
    component: () => import(/* webpackChunkName: "about" */ '../views/zTest/About.vue')
  },
]

// 正式路由
const routes = [
  {
    path: '/login',
    name: 'login',
    component: login
  },
  {
    path: '',
    component: Layout,
    redirect: 'home',
    children: [
        ...testRoutes,
      {
        path: 'home',
        name: 'home',
        component: Home
      },
      {
        path: 'goods-index',
        name: 'goods-index',
        component: goodsIndex
      },
      {
        path: 'bill-list',
        name: 'bill-list',
        component: billList
      },
      {
        path: 'dict-list',
        name: 'dict-list',
        component: dict
      },
    ]
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
