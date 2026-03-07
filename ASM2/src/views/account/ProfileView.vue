<template>
  <div class="container mt-4">
    <h3>Thông tin cá nhân</h3>

    <div class="card p-3 mt-3" v-if="user">
      <div class="mb-3">
        <label>Email</label>
        <input class="form-control" v-model="user.email" disabled />
      </div>

      <div class="mb-3">
        <label>Họ tên</label>
        <input class="form-control" v-model="user.name" />
      </div>

      <div class="mb-3">
        <label>Vai trò</label>
        <input class="form-control" v-model="user.role" disabled />
      </div>

      <button class="btn btn-primary" @click="saveProfile">
        Lưu thay đổi
      </button>
    </div>
  </div>
</template>

<script>
import { users } from '@/services/fakeDb'

export default {
  data() {
    return {
      user: null
    }
  },
  mounted() {
    // Giả lập user đang đăng nhập
    this.user = { ...users[0] }
  },
  methods: {
    saveProfile() {
      users[0].name = this.user.name
      alert('Lưu thông tin thành công!')
    }
  }
}
</script>
