<template>
  <Navbar />

  <div class="container mt-4">
    <router-view />
  </div>

  <Footer></Footer>
</template>

<script setup>
import Navbar from '@/views/Navbar.vue'
import Footer from './views/Footer.vue';
</script>
