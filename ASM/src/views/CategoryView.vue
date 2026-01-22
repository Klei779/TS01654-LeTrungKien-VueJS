<template>
  <div class="container mt-4">
    <h3>Danh sách bài viết</h3>

    <div class="card mb-3" v-for="post in posts" :key="post.id">
      <router-link
        :to="`/post/${post.id}`"
        class="text-decoration-none text-dark"
      >
        <div class="card-body">
          <h5>{{ post.title }}</h5>
          <p>{{ post.content }}</p>
        </div>
      </router-link>
    </div>
  </div>
</template>

<script setup>
const posts = [
  { id: 21, title: 'Demo 1', content: '...' },
  { id: 22, title: 'Demo 2', content: '...' },
  { id: 23, title: 'Demo 3', content: '...' },
  { id: 24, title: 'Demo 4', content: '...' },
  { id: 25, title: 'Demo 5', content: '...' },
  { id: 26, title: 'Demo 6', content: '...' }
]
</script>
