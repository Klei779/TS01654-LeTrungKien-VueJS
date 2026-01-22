<template>
  <div>
    <Carousel />

    <!-- BÀI VIẾT MỚI DẠNG SLIDER -->
    <NewPostCarousel />

    <!-- BÀI VIẾT THEO CHỦ ĐỀ -->
    <CategoryAccordion />
  </div>
</template>

<script setup>
import Carousel from './Carousel.vue';
import NewPostCarousel from './NewPostCarousel.vue';
import CategoryAccordion from './CategoryAccordion.vue';
</script>
