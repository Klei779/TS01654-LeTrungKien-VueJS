<template>
  <nav class="navbar navbar-expand-sm navbar-light bg-light">
    <div class="container">
      <a class="navbar-brand fw-bold">QTSC Student Tips</a>

      <div class="collapse navbar-collapse show">
        <ul class="navbar-nav me-auto">
          <li class="nav-item">
            <router-link class="nav-link" to="/">
              Bài viết
            </router-link>
          </li>

          <li class="nav-item">
            <router-link class="nav-link" to="/create-post">
              Đăng bài
            </router-link>
          </li>

          <li class="nav-item">
            <router-link class="nav-link" to="/post-history">
              Lịch sử đăng bài
            </router-link>
          </li>

          <li class="nav-item">
            <a class="nav-link" data-bs-toggle="modal" data-bs-target="#accountModal">
              Tài khoản
            </a>
          </li>
        </ul>
      </div>
    </div>

    <!-- Popup -->
    <AccountModal />
  </nav>
</template>

<script setup>
import AccountModal from './AccountModal.vue';
</script>
