import { createRouter, createWebHistory } from 'vue-router'

// pages
import Home from '@/views/Home.vue'
import Post from '@/views/post/Post.vue'
import PostHistory from '@/views/post/PostHistory.vue'
import PostCreate from '@/views/post/PostCreate.vue'

// account
import AccountLogin from '@/views/account/AccountLogin.vue'
import AccountRegister from '@/views/account/AccountRegister.vue'
import PostDetail from '@/views/post/PostDetail.vue'
import PostFavorite from '@/views/post/PostFavorite.vue'


const routes = [
  {
    path: '/',
    component: Home
  },

  {
    path: '/posts',
    component: Post
  },
  {
    path: '/post/:id',
    component: PostDetail
  },
  {
    path: '/posts/history',
    component: PostHistory,
    meta: { requiresAuth: true }
  },
  {
    path: '/posts/create',
    component: PostCreate,
    meta: { requiresAuth: true }
  },

  {
    path: '/login',
    component: AccountLogin
  },
  {
    path: '/register',
    component: AccountRegister
  },
  {
    path: '/favorites',
    component: PostFavorite
  }

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

/* ===== ROUTER GUARD ===== */
router.beforeEach((to, from, next) => {
  const isLoggedIn = localStorage.getItem('user')

  if (to.meta.requiresAuth && !isLoggedIn) {
    next('/login')
  } else {
    next()
  }
})

export default router
