<template>
  <div class="container mt-5">
    <h1 class="text-center text-primary mb-4">Kiến thức sức khỏe cộng đồng</h1>
    <div class="row">
      <div class="col-sm-4 mb-4" v-for="(item, index) in items" :key="index">
        <div class="card h-100">
          <img :src="item.image" class="card-img-top" :alt="item.title" style="height: 200px; object-fit: cover;">
          <div class="card-body">
            <h5 class="card-title">{{ item.title }}</h5>
            <p class="card-text">{{ item.content }}</p>
            <button class="btn btn-info text-white">Xem chi tiết</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const items = ref([
  {
    title: '8 loại rau củ quả giàu canxi',
    content: 'Canxi là khoáng chất cần thiết đối với cơ thể người. Có nhiều cách để bổ sung canxi, trong đó bổ sung qua đường ăn uống là cách tốt nhất.',
    image: 'https://cdn.tgdd.vn/Files/2019/12/23/1227986/8-loai-rau-cu-qua-giau-canxi-tot-cho-xuong-khop-202201041006509650.jpg'
  },
  {
    title: 'Các loại gia vị tốt cho sức khỏe',
    content: 'Một số loại gia vị cung cấp nhiều polyphenol chống oxy hóa cao hơn các loại gia vị quen thuộc khác. Với một lượng nhỏ các món ăn đã thêm hương thơm.',
    image: 'https://cdn.tgdd.vn/Files/2021/07/07/1366185/top-10-loai-gia-vi-thao-moc-tot-cho-suc-khoe-ma-ban-nen-biet-202107071302302834.jpg'
  },
  {
    title: '9 loại đậu bổ dưỡng nên dùng nhiều',
    content: 'Đậu lăng, đậu nành, đậu phộng, đậu Hà Lan giàu chất xơ, protein cùng nhiều vitamin và khoáng chất giúp giảm lượng đường trong máu, tốt cho tim.',
    image: 'https://media.loveitopcdn.com/2539/bai-viet/thumb/cac-loai-dau-do-tot-cho-suc-khoe-khong-nen-bo-qua.jpg'
  }
]);
</script>