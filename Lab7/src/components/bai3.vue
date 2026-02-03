<template>
  <div class="container mt-5">
    <div class="row">
      <!-- Form Đăng Ký -->
      <div class="col-md-6">
        <div class="card p-4">
          <h3 class="card-title">Form Đăng Ký</h3>
          <form @submit.prevent="register">
            <div class="mb-3">
              <label>Họ tên:</label>
              <input type="text" class="form-control" v-model="info.name" placeholder="Nhập họ tên">
            </div>
            
            <div class="mb-3">
              <label>Email:</label>
              <input type="email" class="form-control" v-model="info.email" placeholder="Nhập email">
            </div>

            <div class="mb-3">
              <label>Mật khẩu:</label>
              <input type="password" class="form-control" v-model="info.password" placeholder="Nhập mật khẩu">
            </div>

            <div class="mb-3">
              <label>Ngày sinh:</label>
              <input type="date" class="form-control" v-model="info.dob">
            </div>

            <div class="mb-3">
              <label>Giới tính:</label> <br>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" value="Nam" v-model="info.gender">
                <label class="form-check-label">Nam</label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" value="Nữ" v-model="info.gender">
                <label class="form-check-label">Nữ</label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" value="Khác" v-model="info.gender">
                <label class="form-check-label">Khác</label>
              </div>
            </div>

            <div class="mb-3">
              <label>Ngôn ngữ:</label> <br>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" value="Tiếng Việt" v-model="info.languages">
                <label class="form-check-label">Tiếng Việt</label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" value="Tiếng Anh" v-model="info.languages">
                <label class="form-check-label">Tiếng Anh</label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" value="Tiếng Nhật" v-model="info.languages">
                <label class="form-check-label">Tiếng Nhật</label>
              </div>
            </div>

            <button type="submit" class="btn btn-primary">Đăng ký</button>
          </form>
        </div>
      </div>

      <!-- Hiển thị thông tin -->
      <div class="col-md-6" v-if="isRegistered">
        <div class="card p-4 bg-light">
          <h3 class="card-title">Thông tin đã đăng ký:</h3>
          <p><strong>Họ tên:</strong> {{ info.name }}</p>
          <p><strong>Email:</strong> {{ info.email }}</p>
          <p><strong>Ngày sinh:</strong> {{ info.dob }}</p>
          <p><strong>Giới tính:</strong> {{ info.gender }}</p>
          <p><strong>Ngôn ngữ:</strong> {{ info.languages.join(', ') }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';

const isRegistered = ref(false);

const info = reactive({
  name: '',
  email: '',
  password: '',
  dob: '',
  gender: 'Nam', // Giá trị mặc định
  languages: []
});

const register = () => {
  // Ở đây có thể thêm validate nếu cần
  if(info.name && info.email) {
    isRegistered.value = true;
  } else {
    alert("Vui lòng nhập ít nhất Họ tên và Email");
  }
};
</script>