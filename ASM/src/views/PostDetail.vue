<template>
  <div class="container mt-4">
    <div class="row">

      <!-- CỘT TRÁI: BÀI VIẾT -->
      <div class="col-md-8">
        <h2 class="mb-2">{{ post.title }}</h2>

        <img
          :src="post.image"
          class="img-fluid rounded mb-3"
          alt="post image"
        />

        <p class="text-muted">
          {{ post.author }} · {{ post.time }}
        </p>

        <hr>

        <p>{{ post.content }}</p>

        <p class="text-muted">
          Đây là trang chi tiết bài viết (demo).
          Sau này có thể mở rộng thêm bình luận, chỉnh sửa, xoá bài viết.
        </p>

        <router-link to="/" class="btn btn-outline-secondary mt-3">
          ← Quay về trang chủ
        </router-link>
      </div>

      <!-- CỘT PHẢI: BÌNH LUẬN -->
      <div class="col-md-4">
        <h5 class="mb-3">Bình luận</h5>

        <!-- DANH SÁCH COMMENT -->
        <div
          class="border rounded p-3 mb-3"
          style="max-height: 420px; overflow-y: auto;"
        >
          <div
            v-for="cmt in comments"
            :key="cmt.id"
            class="mb-3 pb-2 border-bottom"
          >
            <strong>{{ cmt.author }}</strong>
            <p class="mb-1 small">{{ cmt.content }}</p>
            <small class="text-muted">{{ cmt.time }}</small>
          </div>
        </div>

        <!-- FORM COMMENT -->
        <div>
          <textarea
            class="form-control mb-2"
            rows="3"
            placeholder="Viết bình luận..."
            v-model="newComment"
          ></textarea>

          <button
            class="btn btn-primary btn-sm w-100"
            @click="addComment"
          >
            Gửi bình luận
          </button>
        </div>

      </div>

    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()

// DEMO BÀI VIẾT
const post = {
  id: route.params.id,
  image: '/images/quan-an/com-tam.jpg',
  title: 'Demo chi tiết bài viết',
  author: 'Sinh viên QTSC',
  time: 'Vừa xong',
  content:
    'Đây là nội dung chi tiết bài viết dùng để demo. ' +
    'Tất cả bài viết khi click đều được chuyển về trang này.'
}

// DEMO COMMENT DÀI
const comments = ref([
  { id: 1, author: 'Minh', content: 'Bài viết rất hữu ích', time: '5 phút trước' },
  { id: 2, author: 'Lan', content: 'Mình cũng hay ăn quán này', time: '10 phút trước' },
  { id: 3, author: 'Huy', content: 'Giá sinh viên là chuẩn rồi', time: '15 phút trước' },
  { id: 4, author: 'Trang', content: 'Cảm ơn bạn đã chia sẻ', time: '20 phút trước' },
  { id: 5, author: 'Nam', content: 'Có chỗ gửi xe không?', time: '30 phút trước' },
  { id: 6, author: 'An', content: 'Mình sẽ ghé thử', time: '1 giờ trước' }
])

</script>
