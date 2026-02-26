<template>
  <div class="container mt-4">
    <h3 class="mb-3">Bài viết yêu thích</h3>

    <div v-if="favoritePosts.length === 0" class="text-muted">
      Bạn chưa thích bài viết nào
    </div>

    <div class="row">
      <div class="col-md-4" v-for="post in favoritePosts" :key="post.id">
        <router-link :to="`/post/${post.id}`" class="text-decoration-none text-dark">
          <div class="card h-100 border-0 shadow-sm">
            <img v-if="post.image" :src="post.image" class="img-fluid rounded-start"
              style="height: 200px; object-fit:cover" />

            <div class="card-body">
              <h6>{{ post.title }}</h6>
              <p class="small text-muted">
                {{ post.content }}
              </p>
            </div>
          </div>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed, ref } from 'vue'
import { posts, favorites as favDb } from '@/services/fakeDb'
import { getCurrentUser } from '@/services/auth'

const user = getCurrentUser()
const favorites = ref(favDb)

/* ===== FAVORITE POSTS ===== */
const favoritePosts = computed(() => {
  if (!user) return []

  const likedPostIds = favorites.value
    .filter(f => f.user === user.name)
    .map(f => f.postId)

  return posts.filter(p => likedPostIds.includes(p.id))
})
</script>
