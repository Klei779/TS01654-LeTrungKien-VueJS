import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/Home.vue'
import CategoryView from '@/views/CategoryView.vue'
import PostDetail from '@/views/PostDetail.vue'
import CreatePost from '@/views/CreatePost.vue'
import PostHistory from '@/views/PostHistory.vue'
import EditPost from '@/views/EditPost.vue'

const routes = [
  { path: '/', component: Home },
  { path: '/category/:slug', component: CategoryView },
  { path: '/post/:id', component: PostDetail },
  { path: '/create-post', component: CreatePost },
  { path: '/post-history', component: PostHistory },
  { path: '/edit-post/:id', component: EditPost },
  { path: '/', component: Home },
  { path: '/post/:id', component: PostDetail },
  { path: '/create-post', component: CreatePost },
  { path: '/post-history', component: PostHistory },
  { path: '/edit-post/:id', component: EditPost }

]

export default createRouter({
  history: createWebHistory(),
  routes
})
