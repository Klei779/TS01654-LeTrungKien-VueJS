<template>
  <Navbar />
  <main>
    <router-view />
  </main>
  <Footer />
</template>

<script setup>
import Navbar from './views/Navbar.vue';
import Footer from './views/Footer.vue';
import './assets/main.css'
</script>
