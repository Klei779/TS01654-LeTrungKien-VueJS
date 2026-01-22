<template>
  <div class="container mt-4">
    <h3 class="mb-3">Bài viết mới</h3>

    <div class="card mb-3" v-for="post in posts" :key="post.id">
      <div class="card-body">
        <h5 class="card-title">{{ post.title }}</h5>
        <p class="card-text">{{ post.content }}</p>
        <small class="text-muted">
          Đăng bởi {{ post.author }} · {{ post.time }}
        </small>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const posts = ref([
  {
    id: 1,
    title: 'Quán ăn dưới 30k gần QTSC',
    content: 'Mình giới thiệu 3 quán ăn sinh viên, đi bộ 5 phút...',
    author: 'Minh',
    time: '2 giờ trước'
  },
  {
    id: 2,
    title: 'Địa điểm quay video thuyết trình yên tĩnh',
    content: 'Tầng 2 khu A buổi chiều khá vắng...',
    author: 'Lan',
    time: '1 ngày trước'
  }
])
</script>
