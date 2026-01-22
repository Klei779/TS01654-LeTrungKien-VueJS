<template>
  <div>
    <div class="mb-3">
      <label>Họ tên</label>
      <input class="form-control" v-model="profile.name">
    </div>

    <div class="mb-3">
      <label>Email</label>
      <input class="form-control" v-model="profile.email" disabled>
    </div>

    <div class="mb-3">
      <label>Ảnh đại diện</label>
      <input type="file" class="form-control">
    </div>

    <div class="mb-3">
      <label>Mật khẩu mới</label>
      <input type="password" class="form-control">
    </div>

    <button class="btn btn-primary w-100">
      Cập nhật
    </button>
  </div>
</template>

<script setup>
import { reactive } from 'vue'

const profile = reactive({
  name: 'Sinh viên QTSC',
  email: 'student@qtsc.edu.vn'
})
</script>
