<template>
  <div class="container mt-5">
    <h3 class="mb-3">Bài 2: Xác định mùa</h3>
    <div class="card p-3" style="max-width: 400px;">
      <label class="form-label fw-bold">Nhập tháng của bạn:</label>
      <input type="number" class="form-control mb-3" v-model.number="month" placeholder="Nhập tháng (1-12)">

      <p v-if="month < 1 || month > 12" style="color: red">
        Vui lòng nhập tháng từ 1 đến 12!
      </p>
      <p v-else-if="month >= 1 && month <= 3">Mùa Xuân</p>
      <p v-else-if="month <= 6">Mùa Hè</p>
      <p v-else-if="month <= 9">Mùa Thu</p>
      <p v-else>Mùa Đông</p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const month = ref(1); 
</script>