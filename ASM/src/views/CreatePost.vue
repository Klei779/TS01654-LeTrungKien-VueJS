<template>
  <div class="container mt-4">
    <h3 class="mb-4">Đăng bài viết mới</h3>

    <div class="row">
      <!-- FORM BÊN TRÁI -->
      <div class="col-md-8">
        <form @submit.prevent="submitPost">

          <div class="mb-3">
            <label class="form-label">ID</label>
            <input v-model="post.id" class="form-control" />
          </div>

          <!-- CHỌN ẢNH -->
          <div class="mb-3">
            <label class="form-label">Ảnh bài viết</label>
            <input
              type="file"
              class="form-control"
              accept="image/*"
              @change="handleImage"
            />
          </div>

          <!-- PREVIEW ẢNH -->
          <div v-if="post.imagePreview" class="mb-3">
            <img
              :src="post.imagePreview"
              class="img-fluid rounded"
              alt="Preview ảnh"
            />
          </div>

          <div class="mb-3">
            <label class="form-label">Tiêu đề</label>
            <input v-model="post.title" class="form-control" />
          </div>

          <div class="mb-3">
            <label class="form-label">Tác giả</label>
            <input v-model="post.author" class="form-control" />
          </div>

          <div class="mb-3">
            <label class="form-label">Thời gian</label>
            <input v-model="post.time" class="form-control" />
          </div>

          <div class="mb-3">
            <label class="form-label">Loại bài viết</label>
            <select v-model="post.category" class="form-select">
              <option value="">-- Chọn loại --</option>
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

          <button class="btn btn-primary">
            Post bài
          </button>
        </form>
      </div>

      <!-- CỘT PHẢI: GỢI Ý -->
      <div class="col-md-4">
        <h5 class="mb-3">Bài viết mới</h5>

        <div
          class="card mb-3"
          v-for="item in recentPosts"
          :key="item.id"
        >
          <div class="row g-0">
            <div class="col-4">
              <img
                :src="item.image"
                class="img-fluid rounded-start"
              />
            </div>
            <div class="col-8">
              <div class="card-body p-2">
                <h6 class="mb-1">{{ item.title }}</h6>
                <small class="text-muted">{{ item.time }}</small>
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue'

const post = reactive({
  id: '',
  imageFile: null,
  imagePreview: '',
  title: '',
  author: '',
  time: '',
  category: '',
  content: ''
})

const handleImage = (e) => {
  const file = e.target.files[0]
  if (!file) return

  post.imageFile = file
  post.imagePreview = URL.createObjectURL(file)
}

const submitPost = () => {
  console.log('POST DATA:', post)
  alert('Demo: bài viết đã được post!')
}

/* BÀI GỢI Ý */
const recentPosts = [
  {
    id: 1,
    title: 'Quán ăn dưới 30k gần QTSC',
    time: '2 giờ trước',
    image: '/images/quan-an/com-tam.jpg'
  },
  {
    id: 2,
    title: 'Địa điểm quay yên tĩnh',
    time: 'Hôm qua',
    image: '/images/dia-diem-quay/khu-a.jpg'
  }
]
</script>
