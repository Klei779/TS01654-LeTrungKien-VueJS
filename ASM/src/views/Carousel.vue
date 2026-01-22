<template>
  <div class="container-fluid p-4">
    <div id="mainCarousel" class="carousel slide" data-bs-ride="carousel">

      <div class="carousel-indicators">
        <button
          v-for="(img, index) in images"
          :key="index"
          type="button"
          data-bs-target="#mainCarousel"
          :data-bs-slide-to="index"
          :class="{ active: index === 0 }"
        ></button>
      </div>

      <div class="carousel-inner">
        <div
          class="carousel-item"
          v-for="(img, index) in images"
          :key="img"
          :class="{ active: index === 0 }"
        >
          <img :src="img" class="d-block w-100" />
        </div>
      </div>

      <button class="carousel-control-prev" type="button" data-bs-target="#mainCarousel" data-bs-slide="prev">
        <span class="carousel-control-prev-icon"></span>
      </button>

      <button class="carousel-control-next" type="button" data-bs-target="#mainCarousel" data-bs-slide="next">
        <span class="carousel-control-next-icon"></span>
      </button>
    </div>
  </div>
</template>

<script setup>
const images = [
  '/images/qtsc-banner1.jpg',
  '/images/qtsc-banner2.jpg',
  '/images/qtsc-banner3.jpg'
]
</script>

<style scoped>
.carousel-item img {
  height: 380px;
  object-fit: cover;
}
</style>
