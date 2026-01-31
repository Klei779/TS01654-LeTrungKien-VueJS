<template>
  <div class="container mt-5">
    <h3 class="mb-3">Bài 1: Xếp loại học lực</h3>
    <div class="card p-3" style="max-width: 400px;">
      <label class="form-label fw-bold">Nhập điểm của bạn:</label>
      <input type="number" class="form-control mb-3" v-model.number="score" placeholder="Nhập điểm trung bình" min="0" max="10">

      <div class="alert alert-info">
        <span class="fw-bold">Xếp loại: </span>
        <span v-if="score < 5.0">Yếu</span>
        <span v-else-if="score >= 5.0 && score < 6.5">Trung bình</span>
        <span v-else-if="score >= 6.5 && score < 8.0">Khá</span>
        <span v-else-if="score >= 8.0 && score < 9.0">Giỏi</span>
        <span v-else-if="score >= 9.0">Xuất sắc</span>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const score = ref(0);
</script>