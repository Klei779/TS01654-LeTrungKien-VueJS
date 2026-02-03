<template>
  <div class="col-sm-6 card p-4 mx-auto mt-5">
    <h2>Bình luận bài viết</h2>
    <div class="card mb-3">
       
        <img src="../assets/raucu1.jpg" class="card-img-top" alt="Hình ảnh">
        <div class="card-body">
            <h5 class="card-title">8 loại rau củ quả giàu canxi</h5>
            <p class="card-text">Canxi là khoáng chất cần thiết đối với cơ thể người. Có nhiều cách để bổ sung canxi, trong đó bổ sung qua đường ăn uống là cách tốt nhất.</p>
        </div>
    </div>

    <form @submit.prevent="submitComment">
      <div class="mt-3">
        <textarea 
          id="commentText" 
          cols="60" 
          rows="3"
          v-model="commentText" 
          class="form-control"
          placeholder="Nhập bình luận của bạn"
        ></textarea>
      </div>
      <button type="submit" class="btn btn-success mt-2">Gửi bình luận</button>
    </form>

    <div v-if="comments.length" class="mt-3">
      <h5>Danh sách các bình luận:</h5>
      <ul style="list-style-type: circle;">
        <li v-for="(comment, index) in comments" :key="index">
          <p><strong>{{ comment.name }}:</strong> {{ comment.text }}</p>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

// Nhận props từ component cha
const props = defineProps(['username']);

const commentText = ref('');
// Mảng chứa các bình luận đã gửi
const comments = ref([]);

const submitComment = () => {
  if (commentText.value) {
    // Thêm bình luận mới vào mảng
    comments.value.push({
      name: props.username, // Sử dụng tên từ props
      text: commentText.value
    });

    // Xóa dữ liệu trong form sau khi gửi
    commentText.value = '';
  }
};
</script>