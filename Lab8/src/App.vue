<template>
  <div>
    <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/blog">Blog</router-link> |
      <router-link to="/profile/info">Profile</router-link> |
      <router-link to="/dashboard">Dashboard</router-link>
    </nav>
    <button @click="$router.push('/blog')">TEST BLOG</button>

    <hr />

    <!-- BẮT BUỘC PHẢI CÓ -->
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style scoped>
nav a {
  margin-right: 10px;
}
</style>
