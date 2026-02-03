<script setup>
import Bai1 from './components/bai1.vue';
import Bai2 from './components/bai2.vue';
import Bai3 from './components/bai3.vue';
import slide7 from './components/slide7.vue';
import { ref } from 'vue';
import LoginComponent from './components/LoginComponent.vue';
import CommentComponent from './components/CommentComponent.vue';

// Quản lý trạng thái đăng nhập và tên người dùng
const isLoggedIn = ref(false);
const loggedInUser = ref('');

// Xử lý khi nhận sự kiện loggedIn từ con
const handleLoginSuccess = (user) => {
  loggedInUser.value = user;
  isLoggedIn.value = true;
};
</script>

<template>
 <!-- <slide7/>
 <Bai1/>
 <Bai2/>
 <Bai3/> -->
<div class="container">
    <!-- Nếu chưa đăng nhập, hiển thị form đăng nhập -->
    <LoginComponent v-if="!isLoggedIn" @loggedIn="handleLoginSuccess" />

    <!-- Nếu đã đăng nhập, hiển thị form bình luận -->
    <CommentComponent v-else :username="loggedInUser" />
  </div>
</template>

<style scoped>

</style>
