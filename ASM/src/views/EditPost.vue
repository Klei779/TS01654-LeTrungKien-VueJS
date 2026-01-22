<template>
  <div class="container mt-4">
    <h3 class="mb-4">Chỉnh sửa bài viết</h3>

    <form @submit.prevent="savePost">

      <div class="mb-3">
        <label class="form-label">ID</label>
        <input
          v-model="post.id"
          class="form-control"
          disabled
        />
      </div>

      <div class="mb-3">
        <label class="form-label">Ảnh (URL demo)</label>
        <input
          v-model="post.image"
          class="form-control"
        />
      </div>

      <div class="mb-3">
        <label class="form-label">Tiêu đề</label>
        <input
          v-model="post.title"
          class="form-control"
        />
      </div>

      <div class="mb-3">
        <label class="form-label">Tác giả</label>
        <input
          v-model="post.author"
          class="form-control"
        />
      </div>

      <div class="mb-3">
        <label class="form-label">Thời gian</label>
        <input
          v-model="post.time"
          class="form-control"
        />
      </div>

      <div class="mb-3">
        <label class="form-label">Loại bài viết</label>
        <select
          v-model="post.category"
          class="form-select"
        >
          <option value="quan-an">Quán ăn</option>
          <option value="dia-diem-quay">Địa điểm quay</option>
          <option value="do-thu-cong">Đồ thủ công</option>
        </select>
      </div>

      <div class="mb-3">
        <label class="form-label">Nội dung</label>
        <textarea
          v-model="post.content"
          rows="6"
          class="form-control"
        ></textarea>
      </div>

      <button class="btn btn-success">
        Save
      </button>

      <router-link
        to="/post-history"
        class="btn btn-secondary ms-2"
      >
        Hủy
      </router-link>

    </form>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()

// DATA DEMO – ĐIỀN SẴN
const post = reactive({
  id: route.params.id,
  image: '/images/quan-an/com-tam.jpg',
  title: 'Cơm tấm gần QTSC',
  author: 'Sinh viên QTSC',
  time: 'Hôm nay',
  category: 'quan-an',
  content: 'Ngon – rẻ – tiện'
})

const savePost = () => {
  console.log('SAVE POST:', post)
  alert('Demo: đã lưu chỉnh sửa!')
}
</script>
