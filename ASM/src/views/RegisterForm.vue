<template>
  <div>
    <div class="mb-3">
      <label>Họ tên</label>
      <input class="form-control" v-model="name">
    </div>

    <div class="mb-3">
      <label>Email</label>
      <input type="email" class="form-control" v-model="email">
    </div>

    <div class="mb-3">
      <label>Mật khẩu</label>
      <input type="password" class="form-control" v-model="password">
    </div>

    <button class="btn btn-success w-100">
      Đăng ký
    </button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
const name = ref('')
const email = ref('')
const password = ref('')
</script>
