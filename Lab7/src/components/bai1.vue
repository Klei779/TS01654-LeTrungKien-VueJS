<template>
  <div class="col-sm-4 p-5 mx-auto">
    <h3 class="text-center">Quản lý công việc</h3>
    
    <!-- Form thêm công việc -->
    <form @submit.prevent="addList">
      <div class="mb-3">
        <label class="form-label">Tên công việc:</label>
        <input 
          type="text" 
          class="form-control" 
          v-model="newTodo" 
          placeholder="Nhập tên công việc"
        >
      </div>
      <button type="submit" class="btn btn-primary">Thêm công việc</button>
    </form>

    <!-- Danh sách công việc -->
    <ul class="list-group mt-4">
      <li 
        class="list-group-item d-flex justify-content-between align-items-center"
        v-for="(job, index) in jobs" 
        :key="index"
      >
        {{ job }}
        <button class="btn btn-danger btn-sm" @click="removeList(index)">Xóa</button>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const newTodo = ref('');
const jobs = ref(['Ăn sáng', 'Đi học', 'Chơi bóng rổ']);

const addList = () => {
  if (newTodo.value.trim()) { //để loại bỏ khoảng trắng ở mỗi chữ
    jobs.value.push(newTodo.value.trim());
    newTodo.value = ''; // Reset ô nhập liệu
  }
};

const removeList = (index) => {
  jobs.value.splice(index, 1);
};
</script>