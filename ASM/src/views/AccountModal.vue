<template>
  <div
    class="modal fade"
    id="accountModal"
    tabindex="-1"
  >
    <div class="modal-dialog modal-lg modal-dialog-centered">
      <div class="modal-content">

        <!-- Header -->
        <div class="modal-header">
          <h5 class="modal-title">Tài khoản</h5>
          <button class="btn-close" data-bs-dismiss="modal"></button>
        </div>

        <!-- Body -->
        <div class="modal-body">

          <!-- Tabs -->
          <ul class="nav nav-tabs mb-3">
            <li class="nav-item">
              <button
                class="nav-link"
                :class="{ active: tab === 'login' }"
                @click="tab = 'login'"
              >
                Đăng nhập
              </button>
            </li>

            <li class="nav-item">
              <button
                class="nav-link"
                :class="{ active: tab === 'register' }"
                @click="tab = 'register'"
              >
                Đăng ký
              </button>
            </li>

            <li class="nav-item">
              <button
                class="nav-link"
                :class="{ active: tab === 'profile' }"
                @click="tab = 'profile'"
              >
                Thông tin cá nhân
              </button>
            </li>
          </ul>

          <!-- Nội dung -->
          <LoginForm v-if="tab === 'login'" />
          <RegisterForm v-if="tab === 'register'" />
          <ProfileForm v-if="tab === 'profile'" />
        </div>

      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import LoginForm from './LoginForm.vue'
import RegisterForm from './RegisterForm.vue'
import ProfileForm from './ProfileForm.vue'

const tab = ref('login')
</script>
