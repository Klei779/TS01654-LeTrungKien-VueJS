import { createRouter, createWebHistory } from 'vue-router'

import Home from '../views/Home.vue'
import BlogList from '../views/BlogList.vue'
import BlogPost from '../views/BlogPost.vue'
import UserProfile from '../views/UserProfile.vue'
import ProfileInfo from '../views/ProfileInfo.vue'
import ProfileSettings from '../views/ProfileSettings.vue'
import Login from '../views/Login.vue'
import Dashboard from '../views/Dashboard.vue'

// xác thực giả lập
let isAuthenticated = false

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/blog',
    name: 'BlogList',
    component: BlogList
  },
  {
    path: '/blog/:id',
    name: 'BlogPost',
    component: BlogPost,
    props: true
  },
  {
    path: '/profile',
    component: UserProfile,
    name: 'UserProfile',
    alias: '/me', // alias route
    children: [
      {
        path: 'info',
        name: 'ProfileInfo',
        component: ProfileInfo
      },
      {
        path: 'settings',
        name: 'ProfileSettings',
        component: ProfileSettings
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: Dashboard,
    beforeEnter: (to, from, next) => {
      if (isAuthenticated) {
        next()
      } else {
        next({ name: 'Login' })
      }
    }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// export để Login.vue dùng
export function login() {
  isAuthenticated = true
}

export default router
