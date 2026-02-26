<template>
  <nav class="navbar navbar-expand-sm navbar-light bg-light">
    <div class="container">

      <!-- LOGO -->
      <a class="navbar-brand fw-bold" href="#">
        QTSC Student Tips
      </a>

      <ul class="navbar-nav ms-auto align-items-center">

        <!-- TRANG CHỦ -->
        <li class="nav-item">
          <a class="nav-link active" href="#">
            Trang chủ
          </a>
        </li>

        <!-- BÀI VIẾT -->
        <li class="nav-item dropdown">
          <a
            class="nav-link dropdown-toggle"
            data-bs-toggle="dropdown"
          >
            Bài viết
          </a>

          <ul class="dropdown-menu">
            <li>
              <a class="dropdown-item" href="#">
                Tất cả bài viết
              </a>
            </li>
            <li>
              <a class="dropdown-item" href="#">
                Danh mục
              </a>
            </li>
          </ul>
        </li>

        <!-- TÀI KHOẢN -->
        <li class="nav-item dropdown">
          <a
            class="nav-link dropdown-toggle"
            data-bs-toggle="dropdown"
          >
            Tài khoản
          </a>

          <ul class="dropdown-menu dropdown-menu-end">

            <!-- CHƯA ĐĂNG NHẬP -->
            <template v-if="!isLoggedIn">
              <li>
                <a class="dropdown-item" href="#" @click="login">
                  Đăng nhập
                </a>
              </li>
              <li>
                <a class="dropdown-item" href="#">
                  Đăng ký
                </a>
              </li>
            </template>

            <!-- ĐÃ ĐĂNG NHẬP -->
            <template v-else>
              <li>
                <a class="dropdown-item" href="#">
                  Video yêu thích
                </a>
              </li>
              <li>
                <a class="dropdown-item" href="#">
                  Thông tin tài khoản
                </a>
              </li>
              <li><hr class="dropdown-divider"></li>
              <li>
                <a
                  class="dropdown-item text-danger"
                  href="#"
                  @click="logout"
                >
                  Đăng xuất
                </a>
              </li>
            </template>

          </ul>
        </li>

      </ul>
    </div>
  </nav>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const isLoggedIn = ref(false)

// kiểm tra trạng thái khi reload trang
onMounted(() => {
  isLoggedIn.value = localStorage.getItem('isLoggedIn') === 'true'
})

// giả lập đăng nhập
const login = () => {
  isLoggedIn.value = true
  localStorage.setItem('isLoggedIn', 'true')
}

// đăng xuất
const logout = () => {
  isLoggedIn.value = false
  localStorage.removeItem('isLoggedIn')
}
</script>
