<template>
  <div class="col-sm-4 card p-4 mx-auto mt-5">
    <h2 class="text-center">Đăng Nhập</h2>
    <form @submit.prevent="handleLogin">
      <div class="mb-3">
        <label for="username">Tên đăng nhập:</label>
        <input 
          type="text" 
          class="form-control" 
          id="username" 
          v-model="username" 
          placeholder="Nhập tên đăng nhập"
        >
      </div>
      <div class="mb-3">
        <label for="password">Mật khẩu:</label>
        <input 
          type="password" 
          class="form-control" 
          id="password" 
          v-model="password" 
          placeholder="Nhập mật khẩu"
        >
      </div>
      <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';

// Khai báo sự kiện custom
const emit = defineEmits(['loggedIn']);

const username = ref('');
const password = ref('');

const handleLogin = () => {
  if (username.value && password.value) {
    // Phát sự kiện 'loggedIn' và gửi kèm giá trị username
    emit('loggedIn', username.value);
  }
};
</script>