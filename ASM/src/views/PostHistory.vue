<template>
  <div class="container mt-4">
    <h3 class="mb-3">Lịch sử đăng bài</h3>

    <div
      class="card mb-3"
      v-for="post in posts"
      :key="post.id"
    >
      <div class="row g-0">
        <div class="col-md-4">
          <img
            :src="post.image"
            class="img-fluid rounded-start"
          />
        </div>

        <div class="col-md-8">
          <div class="card-body">
            <h5>{{ post.title }}</h5>
            <p class="small text-muted">
              {{ post.author }} · {{ post.time }}
            </p>
            <p class="small">{{ post.content }}</p>

            <!-- ACTION -->
            <div class="mt-2">
              <router-link
                :to="`/edit-post/${post.id}`"
                class="btn btn-sm btn-warning me-2"
              >
                Chỉnh sửa
              </router-link>

              <button
                class="btn btn-sm btn-danger"
                @click="deletePost(post.id)"
              >
                Xóa
              </button>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
const posts = [
  {
    id: 1,
    image: '/images/quan-an/com-tam.jpg',
    title: 'Cơm tấm gần QTSC',
    author: 'Sinh viên QTSC',
    time: 'Hôm nay',
    content: 'Ngon – rẻ – tiện'
  },
  {
    id: 2,
    image: '/images/dia-diem-quay/khu-a.jpg',
    title: 'Địa điểm quay yên tĩnh',
    author: 'Sinh viên QTSC',
    time: 'Hôm qua',
    content: 'Phù hợp quay video'
  }
]

const deletePost = (id) => {
  alert(`Demo: đã xóa bài viết ID = ${id}`)
}
</script>
