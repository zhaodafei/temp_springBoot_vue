import {createRouter, createWebHistory} from 'vue-router'
import Home from '../views/Home.vue'
import foo from '../views/foo.vue'
import bar from '../views/bar.vue'

// const routes = [ // <router-link to="/">Home</router-link> |<router-link to="/about">About</router-link>
//   {
//     path: '/',
//     name: 'Home',
//     component: Home
//   },
//   {
//     path: '/about',
//     name: 'About',
//     // route level code-splitting
//     // this generates a separate chunk (about.[hash].js) for this route
//     // which is lazy-loaded when the route is visited.
//     component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
//   }
// ]

const routes = [
  // {
  //   path: '/',
  //   name: 'Layout',
  //   redirect: "/redirect"
  // },
  {
    path: '/',
    name: 'redirect',
    component: Home,
    children: [
      {
        path: '/foo',
        component: foo
      },
      {
        path: '/bar',
        component: bar
      }
    ]
  },
  {
    path: '/about',
    name: 'About',
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
