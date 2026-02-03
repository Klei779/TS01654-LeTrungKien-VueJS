<template>
  <div class="p-5">
    <!-- Click vào chữ để hiện alert -->
    <h1 @click="handleClick('Hello World')">slide7</h1>
    
    <p>Nhấn giữ phím <b>Alt + C</b> trong ô input bên dưới:</p>
    <!-- Vue 3 dùng tên phím 'c' thay vì số 67 -->
    <input type="text" @keyup.alt.c="method" placeholder="Nhấn Alt + C tại đây">
  </div>
</template>

<script setup>
import { ref } from 'vue';

const myName = ref('');

const handleClick = (message) => {
  alert(message);
};

const method = () => {
  alert('Bạn vừa nhấn Alt + C');
};
</script>