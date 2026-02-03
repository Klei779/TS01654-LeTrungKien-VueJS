<template>
  <div class="d-flex justify-content-center mt-5">
    <!-- Form Đăng nhập -->
    <div v-if="!isLoggedIn" class="p-5 col-sm-4 border rounded">
      <h3 class="text-center">Form Đăng nhập</h3>
      <form @submit.prevent="login">
        <div class="mb-3 mt-3">
          <label>Email:</label>
          <input type="email"class="form-control"v-model="email" placeholder="Nhập email">
          <p v-if="emailError" style="color: red;">{{ emailError }}</p>
        </div>

        <div class="mb-3">
          <label>Mật khẩu:</label>
          <input type="password" class="form-control" v-model="password" placeholder="Nhập mật khẩu">
          <p v-if="passwordError" style="color: red;">{{ passwordError }}</p>
        </div>
        <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>
      </form>
    </div>
    <!-- Màn hình chào mừng -->
    <div v-else class="p-5 col-sm-5 border rounded text-center">
      <h3>Chào mừng, {{ email }}!</h3>
      <button @click="logout" class="btn btn-primary mt-3">Đăng xuất</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const isLoggedIn = ref(false);
const email = ref('');
const password = ref('');
const emailError = ref('');
const passwordError = ref('');

// Regex kiểm tra email hợp lệ
const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

const login = () => {
  // Reset thông điệp lỗi
  emailError.value = '';
  passwordError.value = '';

  // Validate email
  if (!email.value) {
    emailError.value = 'Email là bắt buộc.';
  } else if (!emailRegex.test(email.value)) {
    emailError.value = 'Vui lòng nhập email hợp lệ.';
  }

  // Validate mật khẩu
  if (!password.value) {
    passwordError.value = 'Mật khẩu là bắt buộc.';
  }

  // Nếu không có lỗi thì login thành công
  if (!emailError.value && !passwordError.value) {
    isLoggedIn.value = true;
  }
};

const logout = () => {
  isLoggedIn.value = false;
  email.value = '';
  password.value = '';
  emailError.value = '';
  passwordError.value = '';
};
</script>